package src;

import javax.swing.*;
import java.awt.*;

public class CreateAccountGUI extends AbstractLogin {
    private JFrame createAccountFrame;
    private JTextField userField;
    private JPasswordField passField;

    public CreateAccountGUI() {
        createAndShowCreateAccountGUI();
    }

    private void createAndShowCreateAccountGUI() {
        // Main frame setup
        createAccountFrame = new JFrame("Create Account");
        createAccountFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        createAccountFrame.setSize(400, 200);
        createAccountFrame.setLocationRelativeTo(null);

        // Panel setup
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Username label and text field
        JLabel userLabel = new JLabel("New Username:");
        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(userLabel, gbc);

        userField = new JTextField();
        gbc.gridx = 1;
        gbc.gridy = 0;
        panel.add(userField, gbc);

        // Password label and text field
        JLabel passLabel = new JLabel("New Password:");
        gbc.gridx = 0;
        gbc.gridy = 1;
        panel.add(passLabel, gbc);

        passField = new JPasswordField();
        gbc.gridx = 1;
        gbc.gridy = 1;
        panel.add(passField, gbc);

        // Create account button
        JButton createAccountButton = new JButton("Create Account");
        gbc.gridx = 1;
        gbc.gridy = 2;
        panel.add(createAccountButton, gbc);

        createAccountButton.addActionListener(e -> completeLogin());

        createAccountFrame.add(panel);
        createAccountFrame.setVisible(true);
    }

    @Override
    void getUserInput() {
        String username = userField.getText().trim();
        String password = new String(passField.getPassword()).trim();
        if (username.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(createAccountFrame,
                    "Username and password cannot be empty.",
                    "Error creating account",
                    JOptionPane.ERROR_MESSAGE);
        } else {
            System.out.println("Input received: " + username + " " + password);
        }
    }

    @Override
    void authenticate() {
        // Validate if the account can be created (e.g., check for duplicates)
        System.out.println("Authenticating account creation...");
    }

    @Override
    void checkUserType() {
        JOptionPane.showMessageDialog(createAccountFrame,
                "Account created successfully!",
                "Success",
                JOptionPane.INFORMATION_MESSAGE);
    }
}
