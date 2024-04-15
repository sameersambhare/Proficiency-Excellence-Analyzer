package Backend;

import javax.swing.*;
import java.awt.*;

public class AboutPage extends JFrame {
    public AboutPage() {
        setTitle("About Us");
        setSize(800, 600); // Adjust size as needed
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Close only this window
        setLocationRelativeTo(null);
        JPanel contentPane = new JPanel(new BorderLayout());
        contentPane.setBackground(new Color(25,0,51));
        
        
        JTextArea aboutTextArea = new JTextArea();
        aboutTextArea.setEditable(false);
        aboutTextArea.setLineWrap(true);
        aboutTextArea.setWrapStyleWord(true);
        aboutTextArea.setFont(new Font("Arial", Font.PLAIN, 15));
        aboutTextArea.setBackground(Color.BLACK);
        aboutTextArea.setForeground(Color.WHITE);
        aboutTextArea.setText(
            "Welcome to Proficiency Excellence Analyzer, your gateway to streamlined recruitment processes and student-professional interactions. " +
            "We specialize in providing an intuitive platform that bridges the gap between students seeking career opportunities and HR professionals looking for talented candidates.\n\n" +
            
            "At Proficiency Excellence Analyzer, we understand the challenges faced by both students and HR personnel in the recruitment process. " +
            "Our platform aims to simplify this process by offering innovative tools and features tailored to the needs of both parties.\n\n" +
            
            "Our Mission\n\n" +
            
            "Our mission is to empower students by providing them with a platform to showcase their skills, experiences, and achievements to potential employers. " +
            "We strive to make the recruitment process more transparent, efficient, and merit-based.\n\n" +
            
            "For HR professionals, we aim to offer a comprehensive solution for candidate assessment, allowing them to identify top talent based on specific criteria and requirements. " +
            "Our goal is to assist HR teams in finding the perfect fit for their organizations quickly and effectively.\n\n" +
            
            "Key Features\n\n" +
            
            "- Student Profiles: Students can create detailed profiles highlighting their academic qualifications, work experience, projects, skills, and achievements.\n" +
            "- Upload Resumes: Students can upload their resumes and other relevant documents for HR professionals to review.\n" +
            "- Job Listings: HR professionals can post job listings specifying their requirements and criteria for potential candidates.\n" +
            "- Advanced Search: HR professionals can use advanced search filters to narrow down candidates based on specific skills, qualifications, and experience.\n" +
            "- Analytics Dashboard: Our platform provides analytics and insights to HR professionals, helping them track recruitment metrics and make data-driven decisions.\n\n" +
            
            "Why Choose Us?\n\n" +
            
            "- User-Friendly Interface: Our platform features an intuitive user interface designed for easy navigation and accessibility.\n" +
            "- Customizable Solutions: We understand that every organization has unique recruitment needs. Therefore, we offer customizable solutions tailored to meet the specific requirements of our clients.\n" +
            "- Dedicated Support: Our team is committed to providing exceptional customer support and assistance to both students and HR professionals throughout their journey on our platform.\n" +
            "- Continuous Innovation: We are constantly innovating and enhancing our platform to adapt to the evolving needs of the recruitment industry and deliver the best possible experience to our users.\n\n"     
        );
        
        JScrollPane scrollPane = new JScrollPane(aboutTextArea);
        aboutTextArea.setBorder(BorderFactory.createEmptyBorder(10, 30, 10, 30));
        contentPane.add(scrollPane, BorderLayout.CENTER);

        // Add title panel 
        JPanel titlePanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JLabel titleLabel = new JLabel("About Us");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titlePanel.setBackground(new Color(51,0,102));
        titleLabel.setForeground(Color.white);
        titlePanel.add(titleLabel);
        contentPane.add(titlePanel, BorderLayout.NORTH);
        
        // Add padding
        contentPane.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        
        setContentPane(contentPane);
    }
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new AboutPage().setVisible(true);
            }
        });
    }
}

