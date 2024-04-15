package Backend;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MyAccountPage extends JFrame {
    private JTextField usernameField;
    private JTextField fullNameField;
    private JTextField emailField;
    private JTextField contactNumberField;
    private JTextField qualificationField;
    private JTextField linksField;
    private JTextArea profileDescriptionArea;
    private JButton uploadResumeButton;
    private JButton uploadCertificateButton;
    private JButton uploadPhotoButton;
    private File selectedResumeFile;
    static boolean editable=false;
    static String username="Krushna1234";
    
    public MyAccountPage(String username) {
    	MyAccountPage.username=username;
        setTitle("My Account - " + username);
        setSize(800, 600); // Set frame size
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null); // Center the frame
        
        JPanel backgroundPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                ImageIcon backgroundImage = new ImageIcon("./images/profilebgi.jpg");
                Image image = backgroundImage.getImage();
                g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
            }
        };
        
        
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                // Dispose the current MyAccountPage instance
                dispose();

                // Create a new instance of StudentHomePage with the appropriate username
                SwingUtilities.invokeLater(() -> {
                    StudentHomePage studentHomePage = new StudentHomePage(username);
                    studentHomePage.setVisible(true);
                });
            }
        });
        
        
        backgroundPanel.setLayout(new BorderLayout());

//        JPanel formPanel = new JPanel(new GridBagLayout()); 
        JPanel formPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 150, 15)); // FlowLayout with left alignment and gaps
        
        
        // Use GridBagLayout for more control
        formPanel.setOpaque(true); // Make the form panel transparent
         
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = new Insets(5, 5, 5, 5); 

        Font labelFont = new Font("Arial", Font.BOLD, 14);
        Font fieldFont = new Font("Arial", Font.PLAIN, 12);

        // Username

        formPanel.setBackground(Color.black);
        

        
        
        JLabel header = new JLabel("Student Registration");
        header.setFont(new Font("Arial", Font.BOLD, 48));
        header.setForeground(Color.white);
//        EmptyBorder border = new EmptyBorder(0, 21, 10, 21); // top, left, bottom, right
//        header.setBorder(border);
        gbc.gridx = 0;
        gbc.gridy = 0;
        formPanel.add(header);
        
        JLabel usernameLabel = new JLabel("Username             ");
        usernameLabel.setFont(labelFont);
        usernameLabel.setForeground(Color.white);
        gbc.gridx = 0;
        gbc.gridy = 0;
        formPanel.add(usernameLabel);

        usernameField = new JTextField(21);
        usernameField.setFont(fieldFont);
        usernameField.setText(username);
        usernameField.setEditable(MyAccountPage.editable);
//        usernameField.setMyAccountPage.editable();
        gbc.gridx = 1;
        gbc.gridy = 0;
        formPanel.add(usernameField);

        // Full Name
        JLabel fullNameLabel = new JLabel("Full Name             ");
        fullNameLabel.setFont(labelFont);
        fullNameLabel.setForeground(Color.white);
        gbc.gridx = 0;
        gbc.gridy = 1;
        formPanel.add(fullNameLabel);

        fullNameField = new JTextField(21);
        fullNameField.setFont(fieldFont);
        fullNameField.setEditable(MyAccountPage.editable);
        gbc.gridx = 1;
        gbc.gridy = 1;
        formPanel.add(fullNameField);

        // Email
        JLabel emailLabel = new JLabel("Email                     ");
        emailLabel.setForeground(Color.white);
        emailLabel.setFont(labelFont);
        gbc.gridx = 0;
        gbc.gridy = 2;
        formPanel.add(emailLabel);

        emailField = new JTextField(21);
        emailField.setFont(fieldFont);
        emailField.setEditable(MyAccountPage.editable);
        gbc.gridx = 1;
        gbc.gridy = 2;
        formPanel.add(emailField);

        // Contact Number
        JLabel contactNumberLabel = new JLabel("Contact Number  ");
        contactNumberLabel.setForeground(Color.white);
        contactNumberLabel.setFont(labelFont);
        gbc.gridx = 0;
        gbc.gridy = 3;
        formPanel.add(contactNumberLabel );

        contactNumberField = new JTextField(21);
        contactNumberField.setFont(fieldFont);
        contactNumberField.setEditable(MyAccountPage.editable);
        gbc.gridx = 1;
        gbc.gridy = 3;
        formPanel.add(contactNumberField );
        
        

        // Qualification
        JLabel qualificationLabel = new JLabel("Qualification         ");
        qualificationLabel.setForeground(Color.white);
        qualificationLabel.setFont(labelFont);
        gbc.gridx = 0;
        gbc.gridy = 4;
        formPanel.add(qualificationLabel );

        qualificationField = new JTextField(21);
        qualificationField.setFont(fieldFont);
        qualificationField.setEditable(MyAccountPage.editable);
        gbc.gridx = 1;
        gbc.gridy = 4;
        formPanel.add(qualificationField );

        // Links
        JLabel linksLabel = new JLabel("Link                        ");
        linksLabel.setForeground(Color.white);
        linksLabel.setFont(labelFont);
        gbc.gridx = 0;
        gbc.gridy = 5;
        formPanel.add(linksLabel );
        formPanel.add(linksLabel );

        linksField = new JTextField(21);
        linksField.setFont(fieldFont);
        linksField.setEditable(MyAccountPage.editable);
        gbc.gridx = 2;
        gbc.gridy = 5;
        formPanel.add(linksField );

        // Profile Description
        JLabel profileDescriptionLabel = new JLabel("Profile Description");
        profileDescriptionLabel.setForeground(Color.white);
        profileDescriptionLabel.setFont(labelFont);
        gbc.gridx = 0;
        gbc.gridy = 6;
        formPanel.add(profileDescriptionLabel );

        profileDescriptionArea = new JTextArea(3, 21);
        profileDescriptionArea.setEditable(MyAccountPage.editable);
        profileDescriptionArea.setFont(fieldFont);
        JScrollPane profileDescriptionScrollPane = new JScrollPane(profileDescriptionArea);
        gbc.gridx = 1;
        gbc.gridy = 6;
        formPanel.add(profileDescriptionScrollPane );

        // Upload Resume
        JLabel uploadResumeLabel = new JLabel("Upload Resume                     ");
        uploadResumeLabel.setForeground(Color.white);
        uploadResumeLabel.setFont(labelFont);
        gbc.gridx = 0;
        gbc.gridy = 7;
        formPanel.add(uploadResumeLabel );

        uploadResumeButton = new JButton("Upload");
        uploadResumeButton.setFont(labelFont);
        gbc.gridx = 1;
        gbc.gridy = 7;
        formPanel.add(uploadResumeButton );

        // Upload Certification
        JLabel uploadCertificateLabel = new JLabel("Upload Certification              ");
        uploadCertificateLabel.setForeground(Color.white);
        uploadCertificateLabel.setFont(labelFont);
        gbc.gridx = 0;
        gbc.gridy = 8;
        formPanel.add(uploadCertificateLabel );

        uploadCertificateButton = new JButton("Upload");
        uploadCertificateButton.setFont(labelFont);
        gbc.gridx = 1;
        gbc.gridy = 8;
        formPanel.add(uploadCertificateButton );

        // Upload Profile Photo
        JLabel uploadPhotoLabel = new JLabel("Upload Profile Photo             ");
        uploadPhotoLabel.setForeground(Color.white);
        uploadPhotoLabel.setFont(labelFont);
        gbc.gridx = 0;
        gbc.gridy = 9;
        formPanel.add(uploadPhotoLabel );

        uploadPhotoButton = new JButton("Upload");
        uploadPhotoButton.setFont(labelFont);
        gbc.gridx = 1;
        gbc.gridy = 9;
        formPanel.add(uploadPhotoButton );

        JButton saveButton = new JButton("Save");
        saveButton.setFont(labelFont);
        saveButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                saveProfile();
            }
        });
        
        JButton editButton = new JButton("Edit");
        editButton.setFont(labelFont);
        editButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                editDetails();
               
            }
        });
        
       
        
        gbc.gridx = 0;
        gbc.gridy = 50;
        gbc.gridwidth = 1;
        
       
        
        formPanel.add(editButton );
        gbc.gridx = 1;
        gbc.gridy = 60;
        System.out.println("hvddsbnv");
        formPanel.add(saveButton );

        backgroundPanel.add(formPanel, BorderLayout.CENTER);

        setContentPane(backgroundPanel);
        
        uploadResumeButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
                fileChooser.setAcceptAllFileFilterUsed(MyAccountPage.editable);
                fileChooser.setFileFilter(new javax.swing.filechooser.FileFilter() {
                    public boolean accept(File f) {
                        return f.getName().toLowerCase().endsWith(".pdf") || f.isDirectory();
                    }

                    public String getDescription() {
                        return "PDF Documents (*.pdf)";
                    }
                });

                int returnVal = fileChooser.showOpenDialog(MyAccountPage.this);
                if (returnVal == JFileChooser.APPROVE_OPTION) {
                    selectedResumeFile = fileChooser.getSelectedFile();
                    // Display the file name in a label or text field (optional)
                    // resumeFileNameLabel.setText(selectedResumeFile.getName());
                    // resumeFileNameLabel.setToolTipText(selectedResumeFile.getAbsolutePath());
                }
            }
        });

    }
    public void editDetails()
    {
    	MyAccountPage.editable=true;
    	
  	profileDescriptionArea.setEditable(true);
  	 usernameField.setEditable(true);
    fullNameField.setEditable(true);
     emailField.setEditable(true);
     contactNumberField.setEditable(true);
    qualificationField.setEditable(true);
   linksField.setEditable(true);
//     profileDescriptionArea.setEditable(true);
    }
    // Method to set student details
    public void setStudentDetails(String username, String fullName, String email, String contactNumber,
                                  String qualification, String links, String profileDescription) {
        usernameField.setText(username);
        fullNameField.setText(fullName);
        emailField.setText(email);
        contactNumberField.setText(contactNumber);
        qualificationField.setText(qualification);
        linksField.setText(links);
        profileDescriptionArea.setText(profileDescription);
    }

    private void saveProfile() {
        String username = usernameField.getText();
        String fullName = fullNameField.getText();
        String email = emailField.getText();
        String contactNumber = contactNumberField.getText();
        String qualification = qualificationField.getText();
        String links = linksField.getText();
        String profileDescription = profileDescriptionArea.getText();
      
       
        DatabaseManager databaseManager = new DatabaseManager();

        boolean insertionSuccessful = databaseManager.addStudentProfile(username, fullName, email, contactNumber, qualification, links, profileDescription);

      

        if (insertionSuccessful) {
            JOptionPane.showMessageDialog(this, "Profile saved successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
        } else {
        	JOptionPane.showMessageDialog(this, "Failed to save profile. Please try again.", "Error", JOptionPane.ERROR_MESSAGE);
            
             }        
        // Here you can perform logic to save the profile data
        // For now, let's just display the entered information
        String message = "Username: " + username + "\n" +
                "Full Name: " + fullName + "\n" +
                "Email: " + email + "\n" +
                "Contact Number: " + contactNumber + "\n" +
                "Qualification: " + qualification + "\n" +
                "Links: " + links + "\n" +
                "Profile Description: " + profileDescription + "\n" ;
        JOptionPane.showMessageDialog(this, message, "Profile Details", JOptionPane.INFORMATION_MESSAGE);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
//            	 
                MyAccountPage myAccountPage = new MyAccountPage("username");
                myAccountPage.setVisible(true);
//                myAccountPage.setStudentDetails(desiredUsername,name,email,contact,qDescription,urls);
                
                
            }
        });
    }
}
