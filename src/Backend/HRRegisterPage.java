package Backend;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class HRRegisterPage extends JFrame {
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JPasswordField confirmPasswordField;

    private static final String USERNAME_PLACEHOLDER = "Enter your username";
    private static final String PASSWORD_PLACEHOLDER = "Enter your password";
    private static final String CONFIRM_PASSWORD_PLACEHOLDER = "Confirm your password";

    public HRRegisterPage() {
        setTitle("HR Registration");
        setSize(600, 450);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        // Create a panel with background image
        JPanel backgroundPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                // Load your background image
                ImageIcon image = new ImageIcon("./images/banner3.jpg"); // Replace "background.jpg" with your image file path
                g.drawImage(image.getImage(), 0, 0, getWidth(), getHeight(), null);
            }
        };
        backgroundPanel.setLayout(new BorderLayout());

        // Main content panel
        JPanel contentPane = new JPanel(new BorderLayout());
        contentPane.setOpaque(false); // Make the content panel transparent
        contentPane.setBorder(BorderFactory.createEmptyBorder(30, 50, 30, 50));

        // Form panel
        JPanel formPanel = new JPanel(new GridLayout(4, 1, 10, 10));
        formPanel.setOpaque(false); // Make the form panel transparent

        // Fonts
        Font labelFont = new Font("Segoe UI", Font.BOLD, 16);
        Font fieldFont = new Font("Segoe UI", Font.PLAIN, 16);

        // Username Panel
        JPanel usernamePanel = new JPanel(new FlowLayout(FlowLayout.LEADING));
        usernamePanel.setOpaque(false);
        JLabel usernameLabel = new JLabel("Enter Username:   ");
        usernameLabel.setForeground(Color.white);
        usernameLabel.setFont(labelFont);
        usernamePanel.add(usernameLabel);

        usernameField = new JTextField(20);
        usernameField.setFont(fieldFont);
        usernameField.setText(USERNAME_PLACEHOLDER);
        usernameField.setForeground(Color.GRAY);
        usernameField.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (usernameField.getText().equals(USERNAME_PLACEHOLDER)) {
                    usernameField.setText("");
                    usernameField.setForeground(Color.BLACK);
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (usernameField.getText().isEmpty()) {
                    usernameField.setText(USERNAME_PLACEHOLDER);
                    usernameField.setForeground(Color.GRAY);
                }
            }
        });
        usernamePanel.add(usernameField);
        formPanel.add(usernamePanel);

        // Password Panel
        JPanel passwordPanel = new JPanel(new FlowLayout(FlowLayout.LEADING));
        passwordPanel.setOpaque(false);
        JLabel passwordLabel = new JLabel("Enter Password:    ");
        passwordLabel.setForeground(Color.white);
        passwordLabel.setFont(labelFont);
        passwordPanel.add(passwordLabel);

        passwordField = new JPasswordField(20);
        passwordField.setFont(fieldFont);
        passwordField.setEchoChar('\u2022'); // Bullet character for password masking
        passwordField.setText(PASSWORD_PLACEHOLDER);
        passwordField.setForeground(Color.GRAY);
        passwordField.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (String.valueOf(passwordField.getPassword()).equals(PASSWORD_PLACEHOLDER)) {
                    passwordField.setText("");
                    passwordField.setForeground(Color.BLACK);
                    passwordField.setEchoChar('\u2022');
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (String.valueOf(passwordField.getPassword()).isEmpty()) {
                    passwordField.setText(PASSWORD_PLACEHOLDER);
                    passwordField.setForeground(Color.GRAY);
                    passwordField.setEchoChar((char) 0);
                }
            }
        });
        passwordPanel.add(passwordField);
        formPanel.add(passwordPanel);

        // Confirm Password Panel
        JPanel confirmPasswordPanel = new JPanel(new FlowLayout(FlowLayout.LEADING));
        confirmPasswordPanel.setOpaque(false);
        JLabel confirmPasswordLabel = new JLabel("Confirm Password:");
        confirmPasswordLabel.setForeground(Color.white);
        confirmPasswordLabel.setFont(labelFont);
        confirmPasswordPanel.add(confirmPasswordLabel);

        confirmPasswordField = new JPasswordField(20);
        confirmPasswordField.setFont(fieldFont);
        confirmPasswordField.setEchoChar('\u2022'); // Bullet character for password masking
        confirmPasswordField.setText(CONFIRM_PASSWORD_PLACEHOLDER);
        confirmPasswordField.setForeground(Color.GRAY);
        confirmPasswordField.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (String.valueOf(confirmPasswordField.getPassword()).equals(CONFIRM_PASSWORD_PLACEHOLDER)) {
                    confirmPasswordField.setText("");
                    confirmPasswordField.setForeground(Color.BLACK);
                    confirmPasswordField.setEchoChar('\u2022');
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (String.valueOf(confirmPasswordField.getPassword()).isEmpty()) {
                    confirmPasswordField.setText(CONFIRM_PASSWORD_PLACEHOLDER);
                    confirmPasswordField.setForeground(Color.GRAY);
                    confirmPasswordField.setEchoChar((char) 0);
                }
            }
        });
        confirmPasswordPanel.add(confirmPasswordField);
        formPanel.add(confirmPasswordPanel);

        // Register Button
        JButton registerButton = new JButton("Register");
        registerButton.setFont(labelFont);
        registerButton.setForeground(Color.WHITE);
        registerButton.setBackground(new Color(50, 150, 250)); // Blue color
        registerButton.setFocusPainted(false); // Remove focus border
        registerButton.addActionListener(e -> registerHR());
        formPanel.add(registerButton);

        // Add your existing formPanel to the contentPane
        contentPane.add(formPanel, BorderLayout.CENTER);

        // Add the contentPane to the backgroundPanel
        backgroundPanel.add(contentPane, BorderLayout.CENTER);

        setContentPane(backgroundPanel);
    }

    private void registerHR() {
        String username = usernameField.getText();
        String password = new String(passwordField.getPassword());
        String confirmPassword = new String(confirmPasswordField.getPassword());

        if (!password.equals(confirmPassword)) {
            JOptionPane.showMessageDialog(this, "Passwords do not match!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Perform registration logic here
        boolean success = DatabaseManager.insertHRCredentials(username, password);
        if (success) {
            JOptionPane.showMessageDialog(this, "User registered successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
            dispose(); // Close the registration page
            SwingUtilities.invokeLater(() -> new HRHomePage(username).setVisible(true)); // Open the homepage
        } else {
            JOptionPane.showMessageDialog(this, "Error registering user. Please try again.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new HRRegisterPage().setVisible(true));
    }
}
