package Backend;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class StudentProfilePage extends JFrame {
    private JTextField fullNameField;
    private JTextField emailField;
    private JTextField contactNumberField;
    private JTextField qualificationField;
    private JTextArea addressArea;
    private JComboBox<String> genderComboBox;
    private JButton uploadResumeButton;
    private JTextArea profileDescriptionArea;
    private JButton uploadCertificateButton;
    private JButton uploadPhotoButton;

    public StudentProfilePage(String username) {
        setTitle("Student Profile - " + username);

        // Get the screen dimensions
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int screenWidth = (int) screenSize.getWidth();
        int screenHeight = (int) screenSize.getHeight();

        // Set the size of the JFrame equal to the screen dimensions
        setSize(screenWidth, screenHeight);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel contentPane = new JPanel(new BorderLayout());
        contentPane.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20)); // Add padding

        JPanel formPanel = new JPanel(new GridLayout(10, 2, 10, 5)); // GridLayout for form fields
        formPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Add padding

        Font labelFont = new Font("Arial", Font.BOLD, 14);
        Font fieldFont = new Font("Arial", Font.PLAIN, 12); // Decrease font size for input fields

        JLabel fullNameLabel = new JLabel("Full Name:");
        fullNameLabel.setFont(labelFont);
        formPanel.add(fullNameLabel);

        fullNameField = new JTextField();
        fullNameField.setFont(fieldFont);
        fullNameField.setPreferredSize(new Dimension(150, 20)); // Set preferred size with reduced height
        formPanel.add(fullNameField);

        JLabel emailLabel = new JLabel("Email:");
        emailLabel.setFont(labelFont);
        formPanel.add(emailLabel);

        emailField = new JTextField();
        emailField.setFont(fieldFont);
        emailField.setPreferredSize(new Dimension(150, 20)); // Set preferred size with reduced height
        formPanel.add(emailField);

        JLabel contactNumberLabel = new JLabel("Contact Number:");
        contactNumberLabel.setFont(labelFont);
        formPanel.add(contactNumberLabel);

        contactNumberField = new JTextField();
        contactNumberField.setFont(fieldFont);
        contactNumberField.setPreferredSize(new Dimension(150, 20)); // Set preferred size with reduced height
        formPanel.add(contactNumberField);

        JLabel qualificationLabel = new JLabel("Qualification:");
        qualificationLabel.setFont(labelFont);
        formPanel.add(qualificationLabel);

        qualificationField = new JTextField();
        qualificationField.setFont(fieldFont);
        qualificationField.setPreferredSize(new Dimension(150, 20)); // Set preferred size with reduced height
        formPanel.add(qualificationField);

        JLabel addressLabel = new JLabel("Address:");
        addressLabel.setFont(labelFont);
        formPanel.add(addressLabel);

        addressArea = new JTextArea(2, 10); // Decreased rows
        addressArea.setFont(fieldFont);
        JScrollPane addressScrollPane = new JScrollPane(addressArea);
        formPanel.add(addressScrollPane);

        JLabel genderLabel = new JLabel("Gender:");
        genderLabel.setFont(labelFont);
        formPanel.add(genderLabel);

        String[] genders = {"Male", "Female", "Other"};
        genderComboBox = new JComboBox<>(genders);
        genderComboBox.setFont(fieldFont);
        formPanel.add(genderComboBox);

        JLabel uploadResumeLabel = new JLabel("Upload Resume:");
        uploadResumeLabel.setFont(labelFont);
        formPanel.add(uploadResumeLabel);

        uploadResumeButton = new JButton("Upload");
        uploadResumeButton.setFont(labelFont);
        uploadResumeButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Action to upload resume
                JOptionPane.showMessageDialog(null, "Upload resume action");
            }
        });
        formPanel.add(uploadResumeButton);

        JLabel profileDescriptionLabel = new JLabel("Profile Description:");
        profileDescriptionLabel.setFont(labelFont);
        formPanel.add(profileDescriptionLabel);

        profileDescriptionArea = new JTextArea(3, 10); // Increased rows
        profileDescriptionArea.setFont(fieldFont);
        JScrollPane profileDescriptionScrollPane = new JScrollPane(profileDescriptionArea);
        formPanel.add(profileDescriptionScrollPane);

        JLabel uploadCertificateLabel = new JLabel("Upload Certificate:");
        uploadCertificateLabel.setFont(labelFont);
        formPanel.add(uploadCertificateLabel);

        uploadCertificateButton = new JButton("Upload");
        uploadCertificateButton.setFont(labelFont);
        uploadCertificateButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Action to upload certificate
                JOptionPane.showMessageDialog(null, "Upload certificate action");
            }
        });
        formPanel.add(uploadCertificateButton);

        JLabel uploadPhotoLabel = new JLabel("Upload Profile Photo:");
        uploadPhotoLabel.setFont(labelFont);
        formPanel.add(uploadPhotoLabel);

        uploadPhotoButton = new JButton("Upload");
        uploadPhotoButton.setFont(labelFont);
        uploadPhotoButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Action to upload profile photo
                JOptionPane.showMessageDialog(null, "Upload profile photo action");
            }
        });
        formPanel.add(uploadPhotoButton);

        JButton saveButton = new JButton("Save");
        saveButton.setFont(labelFont);
        saveButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                saveProfile();
            }
        });
        contentPane.add(formPanel, BorderLayout.CENTER);
        contentPane.add(saveButton, BorderLayout.SOUTH);

        setContentPane(contentPane);
    }

    // Method to set student details
    public void setStudentDetails(String fullName, String email, String contactNumber, String qualification,
                                  String address, String gender, String profileDescription) {
        fullNameField.setText(fullName);
        emailField.setText(email);
        contactNumberField.setText(contactNumber);
        qualificationField.setText(qualification);
        addressArea.setText(address);
        genderComboBox.setSelectedItem(gender);
        profileDescriptionArea.setText(profileDescription);
    }

    private void saveProfile() {
        String fullName = fullNameField.getText();
        String email = emailField.getText();
        String contactNumber = contactNumberField.getText();
        String qualification = qualificationField.getText();
        String address = addressArea.getText();
        String gender = (String) genderComboBox.getSelectedItem();
        String profileDescription = profileDescriptionArea.getText();

        DatabaseManager.saveProfile("Krushna", fullName, email, contactNumber, qualification, address, gender,
                profileDescription);
        // Here you can perform logic to save the profile data
        // For now, let's just display the entered information
        String message = "Full Name: " + fullName + "\n" + "Email: " + email + "\n" + "Contact Number: " + contactNumber
                + "\n" + "Qualification: " + qualification + "\n" + "Address: " + address + "\n" + "Gender: " + gender
                + "\n" + "Profile Description: " + profileDescription;
        JOptionPane.showMessageDialog(this, message, "Profile Details", JOptionPane.INFORMATION_MESSAGE);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                StudentProfilePage profilePage = new StudentProfilePage("Username");
                // Example student details
                profilePage.setStudentDetails("John Doe", "john.doe@example.com", "1234567890",
                        "Bachelor of Science", "123 Main St, Cityville", "Male", "A diligent student passionate about learning.");
                profilePage.setVisible(true);
            }
        });
    }
}
