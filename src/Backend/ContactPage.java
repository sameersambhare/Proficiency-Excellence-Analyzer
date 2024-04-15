package Backend;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class ContactPage extends JFrame {
    public ContactPage() {
        setTitle("Contact Us");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        
        JPanel contentPane = new JPanel(new BorderLayout());
        contentPane.setBackground(new Color(25,0,51));
        
        NavBar navBar = new NavBar(); // Use the NavBar
        
        navBar.addHomeButtonListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "You clicked Home button.");
            }
        });
        
        navBar.addAboutButtonListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                openAboutPage();
            }
        });
        
        navBar.addContactButtonListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Already on the Contact page
                JOptionPane.showMessageDialog(null, "You are already on the Contact page.");
            }
        });
        
        navBar.addLoginButtonListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                openLoginPage();
            }
        });
        
        contentPane.add(navBar, BorderLayout.NORTH);
        
        JTextArea contactInfoTextArea = new JTextArea();
        contactInfoTextArea.setEditable(false);
        contactInfoTextArea.setLineWrap(true);
        contactInfoTextArea.setWrapStyleWord(true);
        contactInfoTextArea.setFont(new Font("Arial", Font.PLAIN, 15));
        contactInfoTextArea.setBackground(Color.BLACK);
        contactInfoTextArea.setForeground(Color.WHITE);
        contactInfoTextArea.setBorder(BorderFactory.createEmptyBorder(20, 30, 20, 30));
        contactInfoTextArea.setText(
            "Address:\n" +
            "Proficiency Excellence Analyzer\n" +
            "Pimpri Chinchwad College of Engineering, Pune\n" +
            "Pune, Maharashtra, 411035\n" +
            "India\n\n" +
            
            "Phone:\n" +
            "Krushna: +91 9359360896\n" +
            "Sameer: +91 8010982302\n" +
            "Yash: +91 9359360896\n\n" +
            
            "Email:\n" +
            "General Inquiries: info@proficiencyanalyzer.com\n" +
            "Customer Support: support@proficiencyanalyzer.com\n\n" +
            
            "Social Media:\n" +
            "Follow us on social media to stay updated on the latest news, features, and announcements:\n\n" +
            
            "Facebook: [Link]\n" +
            "Twitter: [Link]\n" +
            "LinkedIn: [Link]\n" +
            "Instagram: [Link]\n\n" +
            
            "Business Hours:\n" +
            "Monday - Friday: 9:00 AM - 5:00 PM (Local Time)\n" +
            "Saturday - Sunday: Closed"
        );
        
        JScrollPane scrollPane = new JScrollPane(contactInfoTextArea);
        contentPane.add(scrollPane, BorderLayout.CENTER);

        // Add title panel
        JPanel titlePanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JLabel titleLabel = new JLabel("Contact Us");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titlePanel.setBackground(new Color(51,0,102));
        titleLabel.setForeground(Color.white);
        titlePanel.add(titleLabel);
        contentPane.add(titlePanel, BorderLayout.NORTH);
        
        // Add padding
        contentPane.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        
        setContentPane(contentPane);
    }
    
    private void openLoginPage() {
        LoginFrame loginFrame = new LoginFrame();
        loginFrame.setVisible(true);
    }
    
    private void openAboutPage() {
        AboutPage aboutPage = new AboutPage();
        aboutPage.setVisible(true);
    }
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new ContactPage().setVisible(true);
            }
        });
    }
}

