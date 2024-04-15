package Backend;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class NavBar extends JPanel {
    private JButton homeButton;
    private JButton aboutButton;
    private JButton contactButton;
    private JButton loginButton;
    private JButton signUpButton; // New button for sign up
    
    public NavBar() {
        setLayout(new FlowLayout(FlowLayout.LEFT));
        setBackground(new Color(25,0,51));
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        homeButton = new JButton("Home");
        aboutButton = new JButton("About");
        contactButton = new JButton("Contact");
        loginButton = new JButton("Login");
        signUpButton = new JButton("Sign Up"); // Initialize the sign up button
        
        add(homeButton);
        add(aboutButton);
        add(contactButton);
        add(loginButton);
        add(signUpButton); // Add the sign up button
    }
    
    public void addHomeButtonListener(ActionListener listener) {
        homeButton.addActionListener(listener);
    }
    
    public void addAboutButtonListener(ActionListener listener) {
        aboutButton.addActionListener(listener);
    }
    
    public void addContactButtonListener(ActionListener listener) {
        contactButton.addActionListener(listener);
    }
    
    public void addLoginButtonListener(ActionListener listener) {
        loginButton.addActionListener(listener);
    }
    
    public void addSignUpButtonListener(ActionListener listener) {
        signUpButton.addActionListener(listener); // Add action listener to the sign up button
    }
}

