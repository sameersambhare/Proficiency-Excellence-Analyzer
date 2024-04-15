package Backend;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class HomePage extends JFrame {
    private JPanel contentPane;
    private NavBar navBar;

    public HomePage() {
        setTitle("Home");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Center the frame on the screen

        contentPane = new JPanel(new BorderLayout());

        // Create custom panel for background image
        JPanel backgroundPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                ImageIcon backgroundImage = new ImageIcon("./images/banner2.jpg");
                Image image = backgroundImage.getImage();
                g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
            }
        };
        backgroundPanel.setLayout(new BorderLayout());

        // Create navbar
        navBar = new NavBar();
        navBar.addHomeButtonListener(e -> redirectToHomePage());
        navBar.addAboutButtonListener(e -> openAboutPage());
        navBar.addContactButtonListener(e -> openContactPage());
        navBar.addLoginButtonListener(e -> openLoginPage());
        navBar.addSignUpButtonListener(e -> openSignUpPage());

        // Add navbar to the top of the background
        backgroundPanel.add(navBar, BorderLayout.NORTH);

        // Create main content panel
        JPanel mainContentPanel = new JPanel(new BorderLayout());
        mainContentPanel.setOpaque(false); // Make it transparent

        // Add Proficiency Excellence Analyzer text
        JLabel analyzerLabel = new JLabel("Proficiency Excellence Analyzer");
        analyzerLabel.setFont(new Font("Arial", Font.BOLD, 48));
        analyzerLabel.setHorizontalAlignment(JLabel.CENTER);
        analyzerLabel.setForeground(Color.WHITE); // Set font color to white
        analyzerLabel.setBorder(BorderFactory.createEmptyBorder(170, 0, 0, 0));

        mainContentPanel.add(analyzerLabel, BorderLayout.NORTH);

        // Add tagline
        JLabel welcomeLabel = new JLabel("- Connecting Talent with Opportunity!");
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 20));
        welcomeLabel.setHorizontalAlignment(JLabel.CENTER);
        welcomeLabel.setForeground(Color.WHITE); // Set font color to white
        welcomeLabel.setBorder(BorderFactory.createEmptyBorder(0, 0, 100, 0));

        mainContentPanel.add(welcomeLabel, BorderLayout.CENTER);
        JLabel copyrightLabel = new JLabel("© 2024 PCCOE. All rights reserved.");
        copyrightLabel.setFont(new Font("Arial", Font.PLAIN, 12));
        copyrightLabel.setHorizontalAlignment(JLabel.CENTER);
        copyrightLabel.setForeground(Color.WHITE);
        copyrightLabel.setBorder(BorderFactory.createEmptyBorder(20, 0, 50, 0)); // Adjust bottom margin

        mainContentPanel.add(copyrightLabel, BorderLayout.SOUTH);

        // Add main content panel to the center of the background
        backgroundPanel.add(mainContentPanel, BorderLayout.CENTER);

        contentPane.add(backgroundPanel); // Add the background panel to the content pane

        setContentPane(contentPane);
    }
    private void redirectToHomePage() {
        // Here you can define what happens when the "Home" button is clicked
        // For example, if you want to reset the content to the home page,
        // you can recreate the HomePage frame.
        dispose(); // Close the current frame
        SwingUtilities.invokeLater(() -> new HomePage().setVisible(true)); // Create a new instance of HomePage
    }

    private void openLoginPage() {
        LoginFrame loginFrame = new LoginFrame();
        loginFrame.setVisible(true);
    }

    private void openAboutPage() {
        AboutPage aboutPage = new AboutPage();
        aboutPage.setVisible(true);
    }

    private void openContactPage() {
        ContactPage contactPage = new ContactPage();
        contactPage.setVisible(true);
    }

    private void openSignUpPage() {
        SignUpPage signUpPage = new SignUpPage();
        signUpPage.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new HomePage().setVisible(true));
    }
}
