package Backend;

import javax.swing.*;
import javax.swing.text.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;

public class PostJobForm extends JFrame {
    private JTextField postIdField;
    private JTextField packageIdField;
    private JTextField positionNameField;
    private JTextField companyField;
    private JComboBox<String> qualificationComboBox;
    private JComboBox<String> experienceComboBox;
    private JComboBox<String> locationComboBox;
    private JTextArea jobDescriptionArea;
    private DefaultListModel<String> skillsListModel;
    private JList<String> skillsList;
    private JList<String> skillsList1; // Box 1
    private JList<String> skillsList2; // Box 2
    private boolean addToBox1 = true;

    public PostJobForm() {
        setTitle("Post Job");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(800, 600);
        setResizable(false); // Prevent resizing

        // Custom panel with background image
        JPanel backgroundPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                try {
                    BufferedImage image = ImageIO.read(new File("./images/banner3.jpg"));
                    g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        };
        
        
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                goToJobPostViewer(); // Redirect to JobPostViewer on window close
            }
        });
        
        backgroundPanel.setLayout(null); // Set layout manager to null

        JPanel contentPane = new JPanel(null); // Use null layout for manual positioning
        contentPane.setOpaque(false); // Make contentPane transparent
        contentPane.setSize(800, 600); // Set size to match the frame

        JPanel formPanel = new JPanel(new GridLayout(11, 2, 10, 10)); // Increased rows for package field
        formPanel.setOpaque(false); // Make formPanel transparent
        formPanel.setBounds(50, 50, 700, 400); // Manually position formPanel
        formPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        Font labelFont = new Font("Arial", Font.BOLD, 16);
        Font fieldFont = new Font("Arial", Font.PLAIN, 16);

        // Post Id
        JLabel postIdLabel = new JLabel("Post Id:");
        postIdLabel.setForeground(Color.white);
        postIdLabel.setFont(labelFont);
        formPanel.add(postIdLabel);
        postIdField = createLimitedTextField(5);
        postIdField.setFont(fieldFont);
        formPanel.add(postIdField);

        //Position Name
        JLabel positionNameLabel = new JLabel("Position Name:");
        positionNameLabel.setForeground(Color.white);
        positionNameLabel.setFont(labelFont);
        formPanel.add(positionNameLabel);
        positionNameField = createLimitedTextField(15);
        positionNameField.setFont(fieldFont);
        formPanel.add(positionNameField);

        // Company
        JLabel companyLabel = new JLabel("Company:");
        companyLabel.setForeground(Color.white);
        companyLabel.setFont(labelFont);
        formPanel.add(companyLabel);
        companyField = createLimitedTextField(15);
        companyField.setFont(fieldFont);
        formPanel.add(companyField);

        // Qualification - ComboBox
        JLabel qualificationLabel = new JLabel("Qualification:");
        qualificationLabel.setForeground(Color.white);
        qualificationLabel.setFont(labelFont);
        formPanel.add(qualificationLabel);
        String[] qualifications = {"Select your Qualification", "Bachelor of Technology (BTech)", "Bachelor of Engineering (BE)", "Bachelor of Science (BSC)", "Diploma", "Master of Technology (MTech)"};
        qualificationComboBox = new JComboBox<>(qualifications);
        qualificationComboBox.setFont(fieldFont);
        formPanel.add(qualificationComboBox);

        // Experience - ComboBox
        JLabel experienceLabel = new JLabel("Experience:");
        experienceLabel.setForeground(Color.white);
        experienceLabel.setFont(labelFont);
        formPanel.add(experienceLabel);
        String[] experiences = {"Select Experience", "Fresher", "1 year", "2 years", "5 years"};
        experienceComboBox = new JComboBox<>(experiences);
        experienceComboBox.setFont(fieldFont);
        formPanel.add(experienceComboBox);

        // Location - ComboBox
        JLabel locationLabel = new JLabel("Location:");
        locationLabel.setForeground(Color.white);
        locationLabel.setFont(labelFont);
        formPanel.add(locationLabel);
        String[] locations = {"Select Location", "Pune", "Mumbai", "Delhi", "Nagpur", "Bangalore", "Chennai", "Kerala", "Greater Noida", "Gurgaon"};
        locationComboBox = new JComboBox<>(locations);
        locationComboBox.setFont(fieldFont);
        formPanel.add(locationComboBox);

        // Job Description
        JLabel jobDescriptionLabel = new JLabel("Job Description:");
        jobDescriptionLabel.setForeground(Color.white);
        jobDescriptionLabel.setFont(labelFont);
        formPanel.add(jobDescriptionLabel);
        jobDescriptionArea = new JTextArea(3, 10);
        jobDescriptionArea.setFont(fieldFont);
        JScrollPane jobDescriptionScrollPane = new JScrollPane(jobDescriptionArea);
        formPanel.add(jobDescriptionScrollPane);

        // Package
        JLabel packageLabel = new JLabel("Package/Salary:");
        packageLabel.setForeground(Color.white);
        packageLabel.setFont(labelFont);
        formPanel.add(packageLabel);
        packageIdField = createLimitedTextField(15);
        packageIdField.setFont(fieldFont);
        formPanel.add(packageIdField);

        // Required Skills
        JLabel requiredSkillsLabel = new JLabel("Required Skills:");
        requiredSkillsLabel.setForeground(Color.white);
        requiredSkillsLabel.setFont(labelFont);
        formPanel.add(requiredSkillsLabel);

        // Create a JComboBox for skills
        String[] skillOptions = {"Select Skill", "React", "Java", "C++", "Python", "JavaScript", "HTML/CSS", "SQL", "Node.js", "Angular", "Ruby", "Swift", "PHP"};
        JComboBox<String> skillsComboBox = new JComboBox<>(skillOptions);
        skillsComboBox.setFont(fieldFont);
        skillsComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addSkill((String) skillsComboBox.getSelectedItem());
            }
        });
        formPanel.add(skillsComboBox);

        // Add form panel to content pane
        contentPane.add(formPanel);

        // Post Job button
        JButton postButton = new JButton("Post");
        postButton.setFont(labelFont);
        postButton.setBounds(300, 480, 200, 40); // Manually position postButton
        postButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                postJob();
            }
        });
        contentPane.add(postButton);

        // Create Box 1 and Box 2
        skillsListModel = new DefaultListModel<>();
        skillsList1 = new JList<>(skillsListModel);
        skillsList2 = new JList<>(new DefaultListModel<>());

        JScrollPane box1ScrollPane = new JScrollPane(skillsList1);
        JScrollPane box2ScrollPane = new JScrollPane(skillsList2);

        JLabel box1Label = new JLabel("Box 1");
        box1Label.setForeground(Color.white);
        box1Label.setFont(new Font("Arial", Font.BOLD, 16));

        JLabel box2Label = new JLabel("Box 2");
        box2Label.setForeground(Color.white);
        box2Label.setFont(new Font("Arial", Font.BOLD, 16));

        // Add Box 1 and Box 2 to the content pane
        box1Label.setBounds(50, contentPane.getHeight() - 150, 100, 20);
        box1ScrollPane.setBounds(50, contentPane.getHeight() - 120, 200, 80);
        box2Label.setBounds(300, contentPane.getHeight() - 150, 100, 0);
        box2ScrollPane.setBounds(300, contentPane.getHeight() - 120, 200, 80);

        contentPane.add(box1Label);
        contentPane.add(box1ScrollPane);
        contentPane.add(box2Label);
        contentPane.add(box2ScrollPane);

        backgroundPanel.add(contentPane);
        setContentPane(backgroundPanel);
        setLocationRelativeTo(null);
    }
    private void goToJobPostViewer() {
        SwingUtilities.invokeLater(() -> {
            HRHomePage hRHomePage = new HRHomePage("username");
            hRHomePage.setVisible(true);
            dispose(); // Close the current window (JObDetails)
        });
    }
    private static JTextField createLimitedTextField(int limit) {
        JTextField textField = new JTextField(10);
        ((AbstractDocument) textField.getDocument()).setDocumentFilter(new DocumentFilter() {
            @Override
            public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs) throws BadLocationException {
                int newLength = fb.getDocument().getLength() + text.length() - length;
                if (newLength <= limit) {
                    super.replace(fb, offset, length, text, attrs);
                } else {
                    Toolkit.getDefaultToolkit().beep();
                }
            }
        });
        return textField;
    }

    private void addSkill(String skill) {
        if (!skill.equals("Select Skill")) {
            DefaultListModel<String> model;
            if (addToBox1) {
                model = (DefaultListModel<String>) skillsList1.getModel();
                addToBox1 = false;
            } else {
                model = (DefaultListModel<String>) skillsList2.getModel();
                addToBox1 = true;
            }
            model.addElement(skill);
        } else {
            JOptionPane.showMessageDialog(this, "Please select a skill.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void postJob() {
        String postId = postIdField.getText();
        String positionName = positionNameField.getText();
        String company = companyField.getText();
        String qualification = (String) qualificationComboBox.getSelectedItem();
        String experience = (String) experienceComboBox.getSelectedItem();
        String location = (String) locationComboBox.getSelectedItem();
        String jobDescription = jobDescriptionArea.getText();

        StringBuilder requiredSkillsBuilder = new StringBuilder();
        for (int i = 0; i < skillsListModel.getSize(); i++) {
            requiredSkillsBuilder.append(skillsListModel.getElementAt(i)).append("\n");
        }
        String requiredSkills = requiredSkillsBuilder.toString().trim();

        // Display confirmation panel
        JPanel confirmationPanel = new JPanel();
        confirmationPanel.setLayout(new GridLayout(0, 2, 10, 5));

        // Populate confirmationPanel with job details...

        int option = JOptionPane.showConfirmDialog(this, confirmationPanel, "Confirm Post", JOptionPane.YES_NO_OPTION);
        if (option == JOptionPane.YES_OPTION) {
            // Save job post data to the database
            boolean savedSuccessfully = saveJobPostToDatabase(postId, positionName, company, qualification, experience, location, jobDescription, requiredSkills);

            if (savedSuccessfully) {
                // Display success message and handle user response
                option = JOptionPane.showConfirmDialog(this, "Job posted successfully! Do you want to post another job?", "Job Posted", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE);
                if (option == JOptionPane.YES_OPTION) {
                    clearForm(); // Clear form fields
                } else {
                    dispose(); // Close the form
                }
            } else {
                // Display error message if job post failed
                JOptionPane.showMessageDialog(this, "Failed to post job. Please try again.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    private boolean saveJobPostToDatabase(String postId, String positionName, String company, String qualification, String experience, String location, String jobDescription, String requiredSkills) {
        try {
            // Call a method from your DatabaseManager class to save job post
            DatabaseManager.saveJobPost(postId, positionName, company, qualification, experience, location, jobDescription, requiredSkills);
            System.out.println("Job posted successfully to database!");
            return true; // Job post successfully saved
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error occurred while posting job. Please try again.", "Error", JOptionPane.ERROR_MESSAGE);
            return false; // Job post failed
        }
    }


    private void clearForm() {
        postIdField.setText("");
        positionNameField.setText("");
        companyField.setText("");
        qualificationComboBox.setSelectedIndex(0);
        experienceComboBox.setSelectedIndex(0);
        locationComboBox.setSelectedIndex(0);
        jobDescriptionArea.setText("");
        packageIdField.setText("");
        skillsListModel.removeAllElements();
        ((DefaultListModel<String>) skillsList1.getModel()).clear();
        ((DefaultListModel<String>) skillsList2.getModel()).clear();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new PostJobForm().setVisible(true);
            }
        });
    }
}
