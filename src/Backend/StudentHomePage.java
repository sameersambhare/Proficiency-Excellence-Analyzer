package Backend;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class StudentHomePage extends JFrame {
    private JPanel contentPane;
    private Image backgroundImage;
    String username;

    public StudentHomePage(String username) {
    	this.username=username;
        setTitle("Student Homepage");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // Set the frame size
        setSize(800, 600);

        // Center the frame on the screen
        setLocationRelativeTo(null);

        // Use BackgroundPanel for contentPane
        contentPane = new BackgroundPanel();
        contentPane.setLayout(new BorderLayout());

        // Add buttons to the navigation bar
        JPanel navBarPanel = createNavBarPanel();
        contentPane.add(navBarPanel, BorderLayout.NORTH);

        // Add a JLabel with welcome message
        JLabel welcomeLabel = new JLabel("Welcome to the Student Portal");
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 40)); // Set font to bold with size 40
        welcomeLabel.setHorizontalAlignment(JLabel.CENTER); // Center align the text
        contentPane.add(welcomeLabel, BorderLayout.CENTER);

        // Add a panel for the help button
        JPanel helpPanel = createHelpPanel();
        contentPane.add(helpPanel, BorderLayout.SOUTH);

        setContentPane(contentPane);
    }

    // Method to create the navigation bar panel
    private JPanel createNavBarPanel() {
        JPanel navBarPanel = new JPanel();
        navBarPanel.setBackground(Color.DARK_GRAY); // Dark gray color for navigation bar
        navBarPanel.setLayout(new BoxLayout(navBarPanel, BoxLayout.X_AXIS)); // Use BoxLayout for horizontal alignment

        // Create buttons for the navigation bar
        JButton homeButton = createButton("Home", UIManager.getIcon("OptionPane.informationIcon"));
        JButton profileButton = createButton("Profile", UIManager.getIcon("OptionPane.informationIcon"));
        JButton seeJobsButton = createButton("See Jobs", UIManager.getIcon("OptionPane.informationIcon"));
        JButton notificationsButton = createButton("Notifications", UIManager.getIcon("OptionPane.informationIcon"));
        JButton logoutButton = createButton("Logout", UIManager.getIcon("OptionPane.errorIcon"));

        // Add buttons to the navigation bar panel with rigid areas for spacing
        navBarPanel.add(homeButton);
        navBarPanel.add(Box.createRigidArea(new Dimension(10, 0))); // Add space between buttons
        navBarPanel.add(profileButton);
        navBarPanel.add(Box.createRigidArea(new Dimension(10, 0))); // Add space between buttons
        navBarPanel.add(seeJobsButton);
        navBarPanel.add(Box.createRigidArea(new Dimension(10, 0))); // Add space between buttons
        navBarPanel.add(notificationsButton);
        navBarPanel.add(Box.createRigidArea(new Dimension(10, 0))); // Add space between buttons
        navBarPanel.add(Box.createHorizontalGlue()); // Add glue to push buttons to the right
        navBarPanel.add(logoutButton);
        
        logoutButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Close the current StudentHomePage
                dispose();

                // Open a new HomePage
                SwingUtilities.invokeLater(() -> {
                    HomePage homePage = new HomePage();
                    homePage.setVisible(true);
                });
            }
        });
 
        
        
        homeButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Close the current StudentHomePage
                dispose();

                // Open a new HomePage
                SwingUtilities.invokeLater(() -> {
                    StudentHomePage homePage = new StudentHomePage(username);
                    homePage.setVisible(true);
                });
            }
        });
        
        profileButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Close the current StudentHomePage
                dispose();

                // Open MyAccountPage with the appropriate user's details
                // Set the desired username here
                SwingUtilities.invokeLater(() -> {
                    MyAccountPage accountPage = new MyAccountPage(username);
                    accountPage.setVisible(true);
                });
            }
        });
        
        notificationsButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Close the current StudentHomePage
                dispose();

                // Open MyAccountPage with the appropriate user's details
                String username = "desired_username"; // Set the desired username here
                SwingUtilities.invokeLater(() -> {
                	 StudentHomePage studentHomePage = new StudentHomePage("username");
                    NotificationPage notificationPage = new NotificationPage(studentHomePage);
                    notificationPage.setVisible(true);
                });
            }
        });
        
        seeJobsButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Close the current StudentHomePage
                dispose();

                // Open MyAccountPage with the appropriate user's details
                String username = "desired_username"; // Set the desired username here
                SwingUtilities.invokeLater(() -> {
                    JobPostViewer seeJob = new JobPostViewer();
                    seeJob.setVisible(true);
                });
            }
        });
        
        // Add hover effects to buttons
        for (Component c : navBarPanel.getComponents()) {
            if (c instanceof JButton) {
                JButton button = (JButton) c;
                button.setBackground(Color.DARK_GRAY); // Dark gray background
                button.setForeground(Color.WHITE); // White text color
                button.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20)); // Add padding
                button.setFocusPainted(false); // Remove focus border
                button.setCursor(new Cursor(Cursor.HAND_CURSOR)); // Change cursor to hand on hover

                // Add hover effects
                button.addMouseListener(new java.awt.event.MouseAdapter() {
                    public void mouseEntered(java.awt.event.MouseEvent evt) {
                        button.setBackground(Color.GRAY); // Light gray on hover
                    }

                    public void mouseExited(java.awt.event.MouseEvent evt) {
                        button.setBackground(Color.DARK_GRAY); // Restore original color on exit
                    }
                });
            }
        }

        return navBarPanel;
    }

    // Method to create a button with text and icon
    private JButton createButton(String text, Icon icon) {
        JButton button = new JButton(text);
        button.setBackground(Color.DARK_GRAY); // Dark gray color for buttons
        button.setForeground(Color.WHITE); // White text color
        button.setFocusPainted(false);
        button.setPreferredSize(new Dimension(150, 40)); // Set preferred size
        button.setCursor(new Cursor(Cursor.HAND_CURSOR)); // Change cursor to hand on hover

        // Set icon to the button
        button.setIcon(icon);

        // Set padding
        Insets insets = new Insets(10, 20, 10, 20); // Top, left, bottom, right
        button.setMargin(insets);

        
        return button;
    }

    // Method to create a panel containing the Help button
    private JPanel createHelpPanel() {
        JPanel helpPanel = new JPanel();
        helpPanel.setBackground(Color.DARK_GRAY); // Dark gray color for panel background
        helpPanel.setLayout(new BorderLayout());

        // Add Help button to the bottom right
        JButton helpButton = createHelpButton();
        helpPanel.add(helpButton, BorderLayout.EAST);

        // Add padding to the panel
        helpPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        return helpPanel;
    }

    // Method to create the Help button
    private JButton createHelpButton() {
        JButton helpButton = new JButton("Help");
        helpButton.setBackground(Color.WHITE); // White color for button background
        helpButton.setForeground(Color.BLACK); // Black color for button text
        helpButton.setFocusPainted(false);
        helpButton.setPreferredSize(new Dimension(70, 30)); // Set button size
        helpButton.setCursor(new Cursor(Cursor.HAND_CURSOR)); // Change cursor to hand on hover

        // Add hover effects
        helpButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                helpButton.setBackground(Color.GRAY); // Light gray on hover
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                helpButton.setBackground(Color.WHITE); // Restore original color on exit
            }
        });

        // Set border to make it slightly rounded
        helpButton.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));
        helpButton.setBorderPainted(false);
        helpButton.setOpaque(true);

        // Add action listener
        helpButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Open ContactPage
                SwingUtilities.invokeLater(() -> {
                    ContactPage contactPage = new ContactPage();
                    contactPage.setVisible(true);
                });
            }
        });

        return helpButton;
    }
    
    // Custom JPanel class with background image
    private class BackgroundPanel extends JPanel {
        private Image image;

        public BackgroundPanel() {
            // Load the background image
            try {
                image = ImageIO.read(new File("./images/grad1.jpg")); // Replace "path/to/your/image.jpg" with the actual image path
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            // Draw the background image
            if (image != null) {
                g.drawImage(image, 0, 0, this.getWidth(), this.getHeight(), this);
            }
        }
    }

    
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            StudentHomePage studentHomePage = new StudentHomePage("username");
            studentHomePage.setVisible(true);
        });
    }
}
