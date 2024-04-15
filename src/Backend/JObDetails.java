package Backend;



import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.*;

public class JObDetails extends JFrame {

    private JLabel positionLabel;
    private JLabel companyLabel;
    private JLabel experienceLabel;
    private JLabel locationLabel;
    private JLabel qualificationLabel;
    private JLabel descriptionLabel;
    private JLabel linkLabel;
    private JLabel skillsLabel;
    private JLabel salaryLabel;
    private JButton applyButton;

    private String postId;

    private DatabaseManager databaseManager;

    public JObDetails(String postId) {
        this.postId = postId;
//        this.databaseManager = databaseManager;

        setTitle("Job Details");
        setSize(800, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new GridLayout(6, 2, 10, 10));

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                goToJobPostViewer(); // Redirect to JobPostViewer on window close
            }
        });
        
        
        
        positionLabel = new JLabel("Position:");
        companyLabel = new JLabel("Company:");
        experienceLabel = new JLabel("Experience:");
        locationLabel = new JLabel("Location:");
        qualificationLabel = new JLabel("Qualification:");
        descriptionLabel = new JLabel("Job Description:");
        linkLabel = new JLabel("Links For Reference:");
        skillsLabel = new JLabel("Required Skills:");
        salaryLabel = new JLabel("Salary");
        applyButton = new JButton("Apply");
        
        displayJobPostById(postId);
        
        JPanel backgroundPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                ImageIcon backgroundImage = new ImageIcon("./images/profilebgi.jpg");
                Image image = backgroundImage.getImage();
                g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
            }
        };
        
        Font labelFont = new Font("Arial", Font.PLAIN, 16); // Change the font family and size as needed

        positionLabel.setFont(labelFont);
        companyLabel.setFont(labelFont);
        experienceLabel.setFont(labelFont);
        locationLabel.setFont(labelFont);
        qualificationLabel.setFont(labelFont);
        descriptionLabel.setFont(labelFont);
        linkLabel.setFont(labelFont);
        skillsLabel.setFont(labelFont);
        salaryLabel.setFont(labelFont);
        
        backgroundPanel.setLayout(new GridLayout(6, 2, 10, 10));

        positionLabel.setForeground(Color.WHITE);
        companyLabel.setForeground(Color.WHITE);
        experienceLabel.setForeground(Color.WHITE);
        locationLabel.setForeground(Color.WHITE);
        qualificationLabel.setForeground(Color.WHITE);
        descriptionLabel.setForeground(Color.WHITE);
        linkLabel.setForeground(Color.WHITE);
        skillsLabel.setForeground(Color.WHITE);
        salaryLabel.setForeground(Color.WHITE);

        applyButton.setFont(new Font("Arial", Font.PLAIN, 20)); // Set font size
//        applyButton.setPreferredSize(new Dimension(5, 1)); // Set preferred size
        
        backgroundPanel.add(positionLabel);
        backgroundPanel.add(companyLabel);
        backgroundPanel.add(experienceLabel);
        backgroundPanel.add(locationLabel);
        backgroundPanel.add(qualificationLabel);
        backgroundPanel.add(descriptionLabel);
        backgroundPanel.add(linkLabel);
        backgroundPanel.add(skillsLabel);
        backgroundPanel.add(salaryLabel);
        backgroundPanel.add(new JLabel());
        
        
        backgroundPanel.add(applyButton);

        // Set backgroundPanel as content pane of JFrame
        setContentPane(backgroundPanel);
        applyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                applyForJob();
            }
        });
    }
    private void goToJobPostViewer() {
        SwingUtilities.invokeLater(() -> {
            JobPostViewer jobPostViewer = new JobPostViewer();
            jobPostViewer.setVisible(true);
            dispose(); // Close the current window (JObDetails)
        });
    }

    private void displayJobPostById(String postId) {
        try (Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system", "krushna123");
             PreparedStatement pstmt = conn.prepareStatement("SELECT J.position_name, J.company, J.experience,J.salary, J.location, Q.q_description, P.profile_desc, L.urls, S.skills_set " +
                     "FROM JobPost J " +
                     "LEFT JOIN ProfileDesc P ON J.post_id = P.profile_id " +
                     "LEFT JOIN Qualification Q ON J.post_id = Q.q_id " +
                     "LEFT JOIN Links L ON J.post_id = L.url_id " +
                     "LEFT JOIN Skills S ON J.post_id = S.s_id " +
                     "WHERE J.post_id = ?")) {

            pstmt.setString(1, postId);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                positionLabel.setText("Position: " + rs.getString("position_name"));
                companyLabel.setText("Company: " + rs.getString("company"));
                experienceLabel.setText("Experience: " + rs.getString("experience"));
                locationLabel.setText("Location: " + rs.getString("location"));
                qualificationLabel.setText("Qualification: " + rs.getString("q_description"));
                descriptionLabel.setText("Job Description: " + rs.getString("profile_desc"));
                linkLabel.setText("Links: " + rs.getString("urls"));
                salaryLabel.setText("Salary: " + rs.getString("salary"));
                skillsLabel.setText("Skills: " + rs.getString("skills_set"));
            } else {
                JOptionPane.showMessageDialog(this, "Job Post with ID " + postId + " not found", "Not Found", JOptionPane.INFORMATION_MESSAGE);
            }

            rs.close();

        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error: Failed to retrieve data from database", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void applyForJob() {
        String username = JOptionPane.showInputDialog(this, "Enter your username:");
        if (username != null && !username.isEmpty()) {
        	
        	
            boolean success = databaseManager.applyForJob(username,postId);
            if (success) {
                JOptionPane.showMessageDialog(this, "Application submitted successfully", "Success", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, "Failed to submit application", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Username cannot be empty", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        String jdbcUrl = "jdbc:oracle:thin:@localhost:1521:xe";
        String username = "system";
        String password = "krushna123";
        DatabaseManager databaseManager = new DatabaseManager();

        SwingUtilities.invokeLater(() -> new JObDetails("122").setVisible(true));
    }
}

