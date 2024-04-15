package Backend;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class SignUpPage extends JFrame {
    public SignUpPage() {
        setTitle("Sign Up");
        setSize(600, 400); // Increase the size to accommodate the image
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        // Create the content pane with a background image
        JPanel contentPane = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                ImageIcon backgroundImage = new ImageIcon("./images/signup.jpg"); // Change the path to your image file
                Image image = backgroundImage.getImage();
                g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
            }
        };
        contentPane.setLayout(new BorderLayout());

        // Add padding
        contentPane.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Create the title label
        JLabel titleLabel = new JLabel("Choose Account Type:");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 40));
        titleLabel.setForeground(Color.white);
        titleLabel.setHorizontalAlignment(JLabel.CENTER);

        // Create the options panel
        JPanel optionsPanel = new JPanel(new GridLayout(4, 3, 20, 0)); // Use GridLayout with gaps
        optionsPanel.setOpaque(false); // Make the panel transparent

        // Create HR button
        JButton hrButton = createButton("HR");   
        hrButton.addActionListener(e -> openHRRegisterPage());
        optionsPanel.add(hrButton);

        // Create Student button
        JButton studentButton = createButton("Student");
        studentButton.addActionListener(e -> openStudentRegisterPage());
        optionsPanel.add(studentButton);

        // Create College button
        JButton collegeButton = createButton("College");
        collegeButton.addActionListener(e -> openCollegeRegisterPage());
        optionsPanel.add(collegeButton);

        // Add components to the content pane
        contentPane.add(titleLabel, BorderLayout.NORTH);
        contentPane.add(optionsPanel, BorderLayout.CENTER);

        setContentPane(contentPane);
    }

    // Method to create a styled button
    private JButton createButton(String text) {
        JButton button = new JButton(text);
        button.setFont(new Font("Arial", Font.PLAIN, 18));
        button.setBackground(new Color(50, 150, 250));
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        return button;
    }

    // Methods to open register pages
    private void openHRRegisterPage() {
        HRRegisterPage hrPage = new HRRegisterPage();
        hrPage.setVisible(true);
    }

    private void openStudentRegisterPage() {
        StudentRegisterPage studentPage = new StudentRegisterPage();
        studentPage.setVisible(true);
    }

    private void openCollegeRegisterPage() {
        CollegeRegisterPage collegePage = new CollegeRegisterPage();
        collegePage.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new SignUpPage().setVisible(true);
            }
        });
    }
}
