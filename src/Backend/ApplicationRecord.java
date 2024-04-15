package Backend;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.*;

public class ApplicationRecord extends JFrame {

    private DefaultTableModel tableModel;
    private JTable applicationsTable;

    public ApplicationRecord() {
        setTitle("Applications Table");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        
        Color backgroundColor = new Color(240, 240, 240); // Light gray background
        Color tableHeaderColor = new Color(51, 122, 183); // Blue header for table
        Color tableContentColor = Color.BLACK; // Black text for table content
  
        getContentPane().setBackground(backgroundColor);
    
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                goToJobPostViewer(); // Redirect to JobPostViewer on window close
            }
        });
        
        Object[] columnNames = {"Serial Number", "Name", "Position Name"};
        tableModel = new NonEditableTableModel(columnNames, 0); // 0 rows initially

        applicationsTable = new JTable(tableModel);
        
        applicationsTable.getTableHeader().setBackground(tableHeaderColor);
        applicationsTable.getTableHeader().setForeground(Color.WHITE);
        applicationsTable.setForeground(tableContentColor);

        JScrollPane scrollPane = new JScrollPane(applicationsTable);
        scrollPane.setBackground(backgroundColor);
        scrollPane.getViewport().setBackground(backgroundColor);

        getContentPane().add(scrollPane, BorderLayout.CENTER);

        try {
            // Establish JDBC connection
            final String JDBC_URL = "jdbc:oracle:thin:@localhost:1521:xe";
            final String DB_USERNAME = "system";
            final String DB_PASSWORD = "krushna123";
            Connection connection = DriverManager.getConnection(JDBC_URL, DB_USERNAME, DB_PASSWORD);

            // Prepare SQL statement to retrieve data from Applications table
            String sql = "SELECT S.name, P.position_name " +
                         "FROM Applications A " +
                         "left JOIN StudProfile S ON A.username = S.username " +
                         "left JOIN JobPost P ON A.post_id = P.post_id";
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();

            // Populate the table model with retrieved data
            int serialNumber = 1; // Starting serial number
            while (resultSet.next()) {
                String name = resultSet.getString("name");
                String positionName = resultSet.getString("position_name");
                tableModel.addRow(new Object[]{serialNumber, name, positionName});
                serialNumber++; // Increment serial number for the next row
            }
             
            
            // Close JDBC resources
            resultSet.close();
            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Failed to fetch data from database.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    private void goToJobPostViewer() {
        SwingUtilities.invokeLater(() -> {
            HRHomePage hRHomePage = new HRHomePage("username");
            hRHomePage.setVisible(true);
            dispose(); // Close the current window (JObDetails)
        });
    }
    public class NonEditableTableModel extends DefaultTableModel {

        public NonEditableTableModel(Object[] columnNames, int rowCount) {
            super(columnNames, rowCount);
        }

        @Override
        public boolean isCellEditable(int row, int column) {
            return false; // Disable all cells from being editable
        }
    }
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            ApplicationRecord demo = new ApplicationRecord();
            demo.setVisible(true);
        });
    }
}
