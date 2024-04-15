package Backend;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class LoginFrame extends JFrame implements ActionListener {
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton loginButton;
    private JComboBox<String> userTypeComboBox; // ComboBox for user type selection
    
    public LoginFrame() {
        setTitle("Login");
        setSize(350, 250); // Increased height to accommodate the user type selection
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(null);

        JLabel titleLabel = new JLabel("Login");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setBounds(135, 20, 100, 30);
        panel.add(titleLabel);

        JLabel usernameLabel = new JLabel("Username:");
        usernameLabel.setBounds(50, 60, 80, 25);
        panel.add(usernameLabel);

        usernameField = new JTextField();
        usernameField.setBounds(140, 60, 160, 25);
        panel.add(usernameField);

        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setBounds(50, 90, 80, 25);
        panel.add(passwordLabel);

        passwordField = new JPasswordField();
        passwordField.setBounds(140, 90, 160, 25);
        panel.add(passwordField);

        // User Type ComboBox
        JLabel userTypeLabel = new JLabel("User Type:");
        userTypeLabel.setBounds(50, 120, 80, 25);
        panel.add(userTypeLabel);

        String[] userTypes = {"Student", "HR", "College"}; // Available user types
        userTypeComboBox = new JComboBox<>(userTypes);
        userTypeComboBox.setBounds(140, 120, 160, 25);
        panel.add(userTypeComboBox);

        loginButton = new JButton("Login");
        loginButton.setBounds(130, 160, 80, 25);
        loginButton.addActionListener(this);
        panel.add(loginButton);

        add(panel);
    }

    
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == loginButton) {
            String username = usernameField.getText();
            String password = new String(passwordField.getPassword());
            String selectedUserType = (String) userTypeComboBox.getSelectedItem();

            boolean isValid = false;

            // Validate user based on selected user type
            switch (selectedUserType) {
                case "Student":
                    isValid = DatabaseManager.isValidStudent(username, password);
                    break;
                case "HR":
                    isValid = DatabaseManager.isValidHR(username, password);
                    break;
                case "College":
                    isValid = DatabaseManager.isValidCollege(username, password);
                    break;
            }

            if (isValid) {
                JOptionPane.showMessageDialog(this, "Login successful!");
                dispose(); // Close the login frame

                // Open respective home page based on user type
                switch (selectedUserType) {
                    case "Student":
                        StudentHomePage studentHomePage = new StudentHomePage(username);
                        studentHomePage.setVisible(true);
                        break;
                    case "HR":
                        HRHomePage  hrHomePage = new  HRHomePage(username);
                        hrHomePage.setVisible(true);
                        break;
                    case "College":
                    	CollegeHomePage collegeHomePage = new CollegeHomePage(username);
                    	collegeHomePage.setVisible(true);
                        break;
                    // Handle other user types similarly
                }
            } else {
                JOptionPane.showMessageDialog(this, "Invalid username or password", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new LoginFrame().setVisible(true);
        });
    }
}
