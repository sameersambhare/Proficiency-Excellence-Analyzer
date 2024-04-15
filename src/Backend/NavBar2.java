package Backend;


import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class NavBar2 extends JPanel {
    private JButton homeButton;
    private JButton profileButton;
    private JButton logoutButton;
    private JButton seeJobsButton;
    private JButton notificationsButton;

    public NavBar2() {
        setLayout(new FlowLayout());

        homeButton = new JButton("Home");
        profileButton = new JButton("Profile");
        logoutButton = new JButton("Logout");
        seeJobsButton = new JButton("See Jobs");
        notificationsButton = new JButton("Notifications");

        add(homeButton);
        add(profileButton);
        add(logoutButton);
        add(seeJobsButton);
        add(notificationsButton);
    }

    public void addHomeButtonListener(ActionListener listener) {
        homeButton.addActionListener(listener);
    }

    public void addProfileButtonListener(ActionListener listener) {
        profileButton.addActionListener(listener);
    }

    public void addLogoutButtonListener(ActionListener listener) {
        logoutButton.addActionListener(listener);
    }

    public void addSeeJobsButtonListener(ActionListener listener) {
        seeJobsButton.addActionListener(listener);
    }

    public void addNotificationsButtonListener(ActionListener listener) {
        notificationsButton.addActionListener(listener);
    }
}

