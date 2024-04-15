package Backend;


import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import java.sql.*;

public class JobPostViewer extends JFrame {

    private JTable jobTable;
    private int serialNumber = 1; // Counter for generating serial numbers

    public JobPostViewer() {
        setTitle("Job Post Viewer");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(800,600);
        getContentPane().setBackground(Color.decode("#BBDEFB")); // White background for content

        // Create table model with column names
        DefaultTableModel model = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Make all cells non-editable
            }
        };
        
        
        
        model.setColumnIdentifiers(new String[]{"Serial No.", "Post ID", "Position", "Experience", "Company", "Salary"});

        jobTable = new JTable(model);
        jobTable.getTableHeader().setReorderingAllowed(false); // Disable column reordering
        jobTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION); // Allow only single row selection

        JScrollPane scrollPane = new JScrollPane(jobTable);
        getContentPane().setBackground(Color.decode("#F5F5F5")); // Light Gray background
        jobTable.getTableHeader().setBackground(Color.decode("#337AB7")); // Light Blue table header
        jobTable.setSelectionBackground(Color.decode("#1E90FF")); // Dark Blue selection background
        jobTable.setForeground(Color.decode("#000000")); // Black text color

        add(scrollPane);

        displayAllJobPosts(); // Display all job posts in the table

        // Add mouse listener to handle row clicks
        jobTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int row = jobTable.getSelectedRow();
                if (row != -1) { // Check if a row is selected
                    String postId = (String) jobTable.getValueAt(row, 1); // Get post_id from the selected row
                    openJobDetails(postId); // Open JobDetails with selected post_id
                }
            }
        });

        pack(); // Adjust frame size based on components
        setLocationRelativeTo(null); // Center the frame on the screen
        
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                // Perform actions when the window is closing
                goToStudentHomePage();
            }
        });
        
    }
    private void goToStudentHomePage() {
        // Open StudentHomePage
        SwingUtilities.invokeLater(() -> {
            StudentHomePage studentHomePage = new StudentHomePage("username"); // Pass appropriate username here
            studentHomePage.setVisible(true);
        });
    }
    private void displayAllJobPosts() {
        try (Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system", "krushna123");
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT post_id, position_name, experience, company, salary FROM JobPost")) {

            DefaultTableModel model = (DefaultTableModel) jobTable.getModel();

            // Clear existing rows
            model.setRowCount(0);

            // Iterate through the result set and add rows to the table model
            while (rs.next()) {
                String postId = rs.getString("post_id");
                String position = rs.getString("position_name");
                String experience = rs.getString("experience");
                String company = rs.getString("company");
                String salary = rs.getString("salary");

                // Add row with manually generated serial number
                model.addRow(new Object[]{serialNumber, postId, position, experience, company, salary});
                serialNumber++; // Increment serial number for next row
            }

        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error: Failed to retrieve data from database", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void openJobDetails(String postId) {
        SwingUtilities.invokeLater(() -> {
        	
            JObDetails jobDetails = new JObDetails(postId);
            jobDetails.setVisible(true);
        });
    }

    public static void main(String[] args) {
        // Set look and feel to the system's default
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }

        // Create and display the GUI
        SwingUtilities.invokeLater(() -> {
            JobPostViewer jobPostViewer = new JobPostViewer();
            jobPostViewer.setVisible(true);
        });
    }
}

