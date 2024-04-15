package Backend;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class HRProfilePage extends JFrame {
    private JTextField fullNameField;
    private JTextField emailField;
    private JTextField companyNameField;
    private JTextField contactNumberField;
    private JTextField domainField;
    private JTextField locationField;

    public HRProfilePage(String username) {
        setTitle("HR Profile - " + username);
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel contentPane = new JPanel(new BorderLayout());
        contentPane.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20)); // Add padding

        JPanel formPanel = new JPanel(new GridBagLayout()); // Use GridBagLayout for better alignment
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = new Insets(5, 5, 5, 5); // Add insets for spacing

        Font labelFont = new Font("Arial", Font.BOLD, 14);
        Font fieldFont = new Font("Arial", Font.PLAIN, 14);

        addFormField(formPanel, gbc, new JLabel("Full Name:"), labelFont, 0, 0);
        fullNameField = addFormField(formPanel, gbc, new JTextField(20), fieldFont, 1, 0);

        addFormField(formPanel, gbc, new JLabel("Email:"), labelFont, 0, 1);
        emailField = addFormField(formPanel, gbc, new JTextField(20), fieldFont, 1, 1);

        addFormField(formPanel, gbc, new JLabel("Company Name:"), labelFont, 0, 2);
        companyNameField = addFormField(formPanel, gbc, new JTextField(20), fieldFont, 1, 2);

        addFormField(formPanel, gbc, new JLabel("Contact Number:"), labelFont, 0, 3);
        contactNumberField = addFormField(formPanel, gbc, new JTextField(20), fieldFont, 1, 3);

        addFormField(formPanel, gbc, new JLabel("Domain:"), labelFont, 0, 4);
        domainField = addFormField(formPanel, gbc, new JTextField(20), fieldFont, 1, 4);

        addFormField(formPanel, gbc, new JLabel("Location:"), labelFont, 0, 5);
        locationField = addFormField(formPanel, gbc, new JTextField(20), fieldFont, 1, 5);

        JButton updateButton = new JButton("Register");
        updateButton.setFont(labelFont);
        updateButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                updateProfile();
            }
        });
        gbc.gridx = 0;
        gbc.gridy = 6;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        formPanel.add(updateButton, gbc);

        contentPane.add(formPanel, BorderLayout.CENTER);

        setContentPane(contentPane);
    }

    private void updateProfile() {
        String fullName = fullNameField.getText();
        String email = emailField.getText();
        String companyName = companyNameField.getText();
        String contactNumber = contactNumberField.getText();
        String domain = domainField.getText();
        String location = locationField.getText();

        String message = "Full Name: " + fullName + "\n"
                + "Email: " + email + "\n"
                + "Company Name: " + companyName + "\n"
                + "Contact Number: " + contactNumber + "\n"
                + "Domain: " + domain + "\n"
                + "Location: " + location;
        JOptionPane.showMessageDialog(this, message, "Profile Details Updated", JOptionPane.INFORMATION_MESSAGE);
    }

    private JTextField addFormField(JPanel panel, GridBagConstraints gbc, JTextField field, Font font, int x, int y) {
        field.setFont(font);
        gbc.gridx = x;
        gbc.gridy = y;
        gbc.weightx = 1.0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel.add(field, gbc);
        return field;
    }

    private void addFormField(JPanel panel, GridBagConstraints gbc, JLabel label, Font font, int x, int y) {
        label.setFont(font);
        gbc.gridx = x;
        gbc.gridy = y;
        gbc.weightx = 0.0;
        gbc.fill = GridBagConstraints.NONE;
        panel.add(label, gbc);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new HRProfilePage("YourUsername").setVisible(true);
            }
        });
    }
}



