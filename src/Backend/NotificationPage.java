package Backend;

import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class NotificationPage extends JFrame {
    private JTextArea notificationTextArea;
    private StudentHomePage studentHomePage; 
    
    public NotificationPage(StudentHomePage studentHomePage) {
         	this.studentHomePage = studentHomePage;
        setTitle("Notification Page");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(800, 600);
        setResizable(false);
        setLocationRelativeTo(null);

        // Create a modern JPanel with a gradient background
        JPanel mainPanel = new JPanel(new BorderLayout()) {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g.create();
                int width = getWidth();
                int height = getHeight();
                Color startColor = new Color(204,204,255); // Light blue
                Color endColor = new Color(204,204,255);   // Dark blue
                GradientPaint gradientPaint = new GradientPaint(0, 0, startColor, 0, height, endColor);
                g2d.setPaint(gradientPaint);
                g2d.fillRect(0, 0, width, height);
                g2d.dispose();
            }
        };
        
        JTextArea notificationTextArea = new JTextArea();
        notificationTextArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(notificationTextArea);

        // Retrieve and set notification details
        String applicationDetails = retrieveApplicationDetailsFromDatabase(23);
        notificationTextArea.setText(applicationDetails);

        mainPanel.add(scrollPane, BorderLayout.CENTER);
        
       
        // Create a JTextArea for displaying the notification message
        notificationTextArea = new JTextArea();
        notificationTextArea.setEditable(false);
        notificationTextArea.setFont(new Font("Arial", Font.PLAIN, 16));
        notificationTextArea.setOpaque(false); // Make the background transparent
        notificationTextArea.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20)); // Add padding
        notificationTextArea.setLineWrap(true); // Enable line wrapping
        notificationTextArea.setWrapStyleWord(true); // Wrap at word boundaries

        // Set the notification message
       
        notificationTextArea.setText(applicationDetails);

        // Add the JTextArea to a JScrollPane and add it to the main panel
       
        scrollPane.getViewport().setOpaque(false); // Make the viewport transparent
        scrollPane.setOpaque(false); // Make the scroll pane transparent
        mainPanel.add(scrollPane, BorderLayout.CENTER);

        // Add the main panel to the JFrame
        add(mainPanel);
    }

    // Simulated method to retrieve application details from the database
    private String retrieveApplicationDetailsFromDatabase(int postId) {
        StringBuilder details = new StringBuilder();

        // JDBC connection variables
        String url = "jdbc:oracle:thin:@localhost:1521:xe";
        String username = "system";
        String password = "krushna123";

        // SQL query to retrieve application details by post_id
        String sql = "SELECT * FROM applications WHERE post_id = ?";

        try (Connection connection = DriverManager.getConnection(url, username, password);
             PreparedStatement statement = connection.prepareStatement(sql)) {

            // Set the post_id parameter in the SQL query
            statement.setInt(1, postId);

            // Execute the query
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                // Retrieve application details from the result set
                details.append("Subject: Application Confirmation: ").append(resultSet.getString("job_title")).append("\n\n");
                details.append("Dear [Applicant's Name],\n\n");
                details.append("We are pleased to inform you that your application for the position of ").append(resultSet.getString("job_title")).append(" at ").append(resultSet.getString("company")).append(" has been successfully received and processed.\n\n");
                details.append("Application Details:\n\n");
                details.append("Job Title: ").append(resultSet.getString("job_title")).append("\n");
                details.append("Company: ").append(resultSet.getString("company")).append("\n");
                details.append("Qualification: ").append(resultSet.getString("qualification")).append("\n");
                details.append("Experience: ").append(resultSet.getInt("experience")).append(" years\n");
                details.append("Location: ").append(resultSet.getString("location")).append("\n");
                details.append("Job Description:\n");
                details.append(resultSet.getString("job_description")).append("\n\n");
                details.append("We appreciate your interest in joining our team and will review your application carefully. If your qualifications match our requirements, we will contact you for further steps in the hiring process.\n\n");
                details.append("Thank you for considering employment opportunities with us.\n\n");
                details.append("Best regards,\n");
                details.append("[Your Name]\n");
                details.append("[Your Position]\n");
                details.append(resultSet.getString("company"));
            }

        } catch (SQLException e) {
            e.printStackTrace(); // Handle database errors appropriately
        }

        return details.toString();
    }
    @Override
    public void dispose() {
        super.dispose();
        // Make the existing StudentHomePage instance visible upon closing NotificationPage
        if (studentHomePage != null) {
            studentHomePage.setVisible(true);
        }
    }
    
    public static void main(String[] args) {
        // Create NotificationPage with a reference to StudentHomePage
        SwingUtilities.invokeLater(() -> {
            StudentHomePage studentHomePage = new StudentHomePage("username");
            studentHomePage.setVisible(true);

            NotificationPage notificationPage = new NotificationPage(studentHomePage);
            notificationPage.setVisible(true);
        });
    }
}

