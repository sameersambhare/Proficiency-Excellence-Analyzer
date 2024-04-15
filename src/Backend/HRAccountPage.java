package Backend;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class HRAccountPage extends JFrame {
    private JTextField usernameField;
    private JTextField nameField;
    private JTextField emailField;
    private JTextField contactNumberField;
    private JTextField companyField;
    private JTextField linksField;
    private JTextArea experienceArea;
    private JTextField qualificationField;
    private JButton uploadCertificateButton;
    private JTextArea profileDescriptionArea;
    private JButton uploadPhotoButton;

    public HRAccountPage(String username) {
        setTitle("HR Account - " + username);
        setSize(800, 600); // Set frame size
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null); // Center the frame

        JPanel contentPane = new JPanel(new BorderLayout());
        contentPane.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20)); // Add padding
        contentPane.setBackground(new Color(153, 153, 255)); // Set dark purple background color

        JPanel formPanel = new JPanel(new GridBagLayout()); // Use GridBagLayout for more control
        formPanel.setOpaque(false); // Make the form panel transparent

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = new Insets(5, 5, 5, 5); // Padding between components

        Font labelFont = new Font("Arial", Font.BOLD, 14);
        Font fieldFont = new Font("Arial", Font.PLAIN, 12);

        // Username
        JLabel usernameLabel = new JLabel("Username:");
        usernameLabel.setFont(labelFont);
        gbc.gridx = 0;
        gbc.gridy = 0;
        formPanel.add(usernameLabel, gbc);

        usernameField = new JTextField(20);
        usernameField.setFont(fieldFont);
        usernameField.setEditable(false);
        gbc.gridx = 1;
        gbc.gridy = 0;
        formPanel.add(usernameField, gbc);

        // Full Name
        JLabel nameLabel = new JLabel("Name:");
        nameLabel.setFont(labelFont);
        gbc.gridx = 0;
        gbc.gridy = 1;
        formPanel.add(nameLabel, gbc);

        nameField = new JTextField(20);
        nameField.setFont(fieldFont);
        gbc.gridx = 1;
        gbc.gridy = 1;
        formPanel.add(nameField, gbc);

        // Email
        JLabel emailLabel = new JLabel("Email:");
        emailLabel.setFont(labelFont);
        gbc.gridx = 0;
        gbc.gridy = 2;
        formPanel.add(emailLabel, gbc);

        emailField = new JTextField(20);
        emailField.setFont(fieldFont);
        gbc.gridx = 1;
        gbc.gridy = 2;
        formPanel.add(emailField, gbc);

        // Contact Number
        JLabel contactNumberLabel = new JLabel("Contact Number:");
        contactNumberLabel.setFont(labelFont);
        gbc.gridx = 0;
        gbc.gridy = 3;
        formPanel.add(contactNumberLabel, gbc);

        contactNumberField = new JTextField(20);
        contactNumberField.setFont(fieldFont);
        gbc.gridx = 1;
        gbc.gridy = 3;
        formPanel.add(contactNumberField, gbc);

        // Company
        JLabel companyLabel = new JLabel("Company:");
        companyLabel.setFont(labelFont);
        gbc.gridx = 0;
        gbc.gridy = 4;
        formPanel.add(companyLabel, gbc);

        companyField = new JTextField(20);
        companyField.setFont(fieldFont);
        gbc.gridx = 1;
        gbc.gridy = 4;
        formPanel.add(companyField, gbc);

        // Links
        JLabel linksLabel = new JLabel("Links:");
        linksLabel.setFont(labelFont);
        gbc.gridx = 0;
        gbc.gridy = 5;
        formPanel.add(linksLabel, gbc);

        linksField = new JTextField(20);
        linksField.setFont(fieldFont);
        gbc.gridx = 1;
        gbc.gridy = 5;
        formPanel.add(linksField, gbc);

        // Experience
        JLabel experienceLabel = new JLabel("Experience:");
        experienceLabel.setFont(labelFont);
        gbc.gridx = 0;
        gbc.gridy = 6;
        formPanel.add(experienceLabel, gbc);

        experienceArea = new JTextArea(3, 20);
        experienceArea.setFont(fieldFont);
        JScrollPane experienceScrollPane = new JScrollPane(experienceArea);
        gbc.gridx = 1;
        gbc.gridy = 6;
        formPanel.add(experienceScrollPane, gbc);

        // Qualification
        JLabel qualificationLabel = new JLabel("Qualification:");
        qualificationLabel.setFont(labelFont);
        gbc.gridx = 0;
        gbc.gridy = 7;
        formPanel.add(qualificationLabel, gbc);

        qualificationField = new JTextField(20);
        qualificationField.setFont(fieldFont);
        gbc.gridx = 1;
        gbc.gridy = 7;
        formPanel.add(qualificationField, gbc);

        // Upload Certificate
        JLabel uploadCertificateLabel = new JLabel("Upload Certificate:");
        uploadCertificateLabel.setFont(labelFont);
        gbc.gridx = 0;
        gbc.gridy = 8;
        formPanel.add(uploadCertificateLabel, gbc);

        uploadCertificateButton = new JButton("Upload");
        uploadCertificateButton.setFont(labelFont);
        gbc.gridx = 1;
        gbc.gridy = 8;
        formPanel.add(uploadCertificateButton, gbc);

        // Profile Description
        JLabel profileDescriptionLabel = new JLabel("Profile Description:");
        profileDescriptionLabel.setFont(labelFont);
        gbc.gridx = 0;
        gbc.gridy = 9;
        formPanel.add(profileDescriptionLabel, gbc);

        profileDescriptionArea = new JTextArea(3, 20);
        profileDescriptionArea.setFont(fieldFont);
        JScrollPane profileDescriptionScrollPane = new JScrollPane(profileDescriptionArea);
        gbc.gridx = 1;
        gbc.gridy = 9;
        formPanel.add(profileDescriptionScrollPane, gbc);

        // Upload Profile Photo
        JLabel uploadPhotoLabel = new JLabel("Upload Profile Photo:");
        uploadPhotoLabel.setFont(labelFont);
        gbc.gridx = 0;
        gbc.gridy = 10;
        formPanel.add(uploadPhotoLabel, gbc);

        uploadPhotoButton = new JButton("Upload");
        uploadPhotoButton.setFont(labelFont);
        gbc.gridx = 1;
        gbc.gridy = 10;
        formPanel.add(uploadPhotoButton, gbc);

        JButton saveButton = new JButton("Save");
        saveButton.setFont(labelFont);
        saveButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                saveProfile();
            }
        });
        gbc.gridx = 0;
        gbc.gridy = 11;
        gbc.gridwidth = 2;
        formPanel.add(saveButton, gbc);

        contentPane.add(formPanel, BorderLayout.CENTER);

        setContentPane(contentPane);
    }

    // Method to set HR details
    public void setHRDetails(String username, String name, String email, String contactNumber,
                             String company, String links, String experience, String qualification,
                             String profileDescription) {
        usernameField.setText(username);
        nameField.setText(name);
        emailField.setText(email);
        contactNumberField.setText(contactNumber);
        companyField.setText(company);
        linksField.setText(links);
        experienceArea.setText(experience);
        qualificationField.setText(qualification);
        profileDescriptionArea.setText(profileDescription);
    }

    private void saveProfile() {
        String username = usernameField.getText();
        String name = nameField.getText();
        String email = emailField.getText();
        String contactNumber = contactNumberField.getText();
        String company = companyField.getText();
        String links = linksField.getText();
        String experience = experienceArea.getText();
        String qualification = qualificationField.getText();
        String profileDescription = profileDescriptionArea.getText();

        // Here you can perform logic to save the HR profile data
        // For now, let's just display the entered information
        String message = "Username: " + username + "\n" + "Name: " + name + "\n" + "Email: " + email + "\n" +
                "Contact Number: " + contactNumber + "\n" + "Company: " + company + "\n" +
                "Links: " + links + "\n" + "Experience: " + experience + "\n" +
                "Qualification: " + qualification + "\n" + "Profile Description: " + profileDescription;
        JOptionPane.showMessageDialog(this, message, "HR Profile Details", JOptionPane.INFORMATION_MESSAGE);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                HRAccountPage hrAccountPage = new HRAccountPage("HRUsername");
                // Example HR details
                hrAccountPage.setHRDetails("HRUsername", "Jane Smith", "jane.smith@example.com", "9876543210",
                        "ABC Company", "www.linkedin.com/janesmith", "5 years in HR management",
                        "Bachelor of Business Administration", "Experienced HR professional with a passion for talent acquisition.");
                hrAccountPage.setVisible(true);
            }
        });
    }
}
