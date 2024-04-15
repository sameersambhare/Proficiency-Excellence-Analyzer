package Backend;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CollegeProfilePage extends JFrame {
    private JTextField collegeNameField;
    private JTextField instituteCodeField;
    private JTextArea collegeAddressArea;
    private JTextField collegeContactField;
    private JTextField collegeEmailField;
    
    private JButton editButton;
    private boolean editable = false;
    static String username;

    public CollegeProfilePage(String username) {
        setTitle("College Profile - " + username);
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel contentPane = new JPanel(new BorderLayout());
        contentPane.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20)); // Add padding

        JPanel formPanel = new JPanel(new GridLayout(5, 2, 10, 10)); // GridLayout for form fields
        formPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Add padding

        Font labelFont = new Font("Arial", Font.BOLD, 14);
        Font fieldFont = new Font("Arial", Font.PLAIN, 14);

        JLabel collegeNameLabel = new JLabel("College Name:");
        collegeNameLabel.setFont(labelFont);
        formPanel.add(collegeNameLabel);

        collegeNameField = new JTextField();
        collegeNameField.setFont(fieldFont);
       
        formPanel.add(collegeNameField);

        JLabel instituteCodeLabel = new JLabel("Institute Code:");
        instituteCodeLabel.setFont(labelFont);
        formPanel.add(instituteCodeLabel);

        instituteCodeField = new JTextField();
        instituteCodeField.setFont(fieldFont);
        
        formPanel.add(instituteCodeField);

        JLabel collegeAddressLabel = new JLabel("College Address:");
        collegeAddressLabel.setFont(labelFont);
        formPanel.add(collegeAddressLabel);

        collegeAddressArea = new JTextArea(2, 10);
        collegeAddressArea.setFont(fieldFont);
        
        JScrollPane addressScrollPane = new JScrollPane(collegeAddressArea);
        formPanel.add(addressScrollPane);

        JLabel collegeContactLabel = new JLabel("College Contact:");
        collegeContactLabel.setFont(labelFont);
        formPanel.add(collegeContactLabel);

        collegeContactField = new JTextField();
        collegeContactField.setFont(fieldFont);
        
        formPanel.add(collegeContactField);

        JLabel collegeEmailLabel = new JLabel("College Email:");
        collegeEmailLabel.setFont(labelFont);
        formPanel.add(collegeEmailLabel);

        collegeEmailField = new JTextField();
        collegeEmailField.setFont(fieldFont);
        
        formPanel.add(collegeEmailField);
        
        fetchDataAndPopulateFields(username);
       
//        collegeEmailField.setEditable(false);
//        collegeContactField.setEditable(false);
//        instituteCodeField.setEditable(false); 
//        collegeAddressArea.setEditable(false);
//        collegeNameField.setEditable(false);
//        instituteCodeField.setEditable(false); 

        editButton = new JButton("Edit");
        editButton.setFont(labelFont);
        editButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //toggleEditMode();
            }
        });
        contentPane.add(formPanel, BorderLayout.CENTER);
        contentPane.add(editButton, BorderLayout.SOUTH);

        
        JButton saveButton = new JButton("Save");
        saveButton.setFont(labelFont);
        saveButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	
                saveCollegeProfile();
            }
        });
        contentPane.add(formPanel, BorderLayout.CENTER);
        contentPane.add(saveButton, BorderLayout.SOUTH);

        setContentPane(contentPane);
    }
    private void fetchDataAndPopulateFields(String username) {
        String sql = "SELECT collegeName, instituteCode, collegeAddress, collegeContact, collegeEmail FROM ClgProfile WHERE username = ?";

        try (Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system", "krushna123");
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, username);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                collegeNameField.setText(rs.getString("collegeName"));
                instituteCodeField.setText(rs.getString("instituteCode"));
                collegeAddressArea.setText(rs.getString("collegeAddress"));
                collegeContactField.setText(rs.getString("collegeContact"));
                collegeEmailField.setText(rs.getString("collegeEmail"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }


    private void saveCollegeProfile() {
        String collegeName = collegeNameField.getText();
        String instituteCode = instituteCodeField.getText();
        String collegeAddress = collegeAddressArea.getText();
        String collegeContact = collegeContactField.getText();
        String collegeEmail = collegeEmailField.getText();
        String username = CollegeProfilePage.username; 
        

        // Insert into database using DatabaseManager and check if successful
        boolean isInserted = DatabaseManager.insertCollegeProfile(collegeName, instituteCode, collegeAddress,
                                                                  collegeContact, collegeEmail, username);

        // Display confirmation message based on database operation result
        if (isInserted) {
            String message = "Profile updated successfully!";
            JOptionPane.showMessageDialog(this, message, "Success", JOptionPane.INFORMATION_MESSAGE);
        } else {
            String errorMessage = "Failed to update profile. Please try again.";
            JOptionPane.showMessageDialog(this, errorMessage, "Error", JOptionPane.ERROR_MESSAGE);
        }
    }


    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new CollegeProfilePage("Username").setVisible(true);
            }
        });
    }
}

