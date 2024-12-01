package src;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * LoginGUI.java
 *
 * Handles the login interface of the application.
 * Users can enter their credentials to log in as either a seller or a customer.
 * Upon successful authentication, users are directed to their respective interfaces.
 *
 */
public class LoginGUI {
    /**
     * Constructs and displays the login GUI
     */
    public LoginGUI() {
        createAndShowLoginGUI();
    }

    /**
     * Creates and displays the login GUI, private
     */
    private void createAndShowLoginGUI() {
        // Main login frame
        JFrame loginFrame = new JFrame("Login Screen");
        loginFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        loginFrame.setSize(400, 200);
        loginFrame.setLocationRelativeTo(null); // Center on screen

        // Create panel
        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5,5,5,5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Username & text field
        JLabel userLabel = new JLabel("Username:");
        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(userLabel, gbc);

        JTextField userField = new JTextField();
        gbc.gridx = 1;
        gbc.gridy = 0;
        panel.add(userField, gbc);

        // Password & text field
        JLabel passLabel = new JLabel("Password:");
        gbc.gridx = 0;
        gbc.gridy = 1;
        panel.add(passLabel, gbc);

        JPasswordField passField = new JPasswordField();
        gbc.gridx = 1;
        gbc.gridy = 1;
        panel.add(passField, gbc);

        // Login button
        JButton loginButton = new JButton("Login");
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        panel.add(loginButton, gbc);
        
        

        // Add action listener to the login button
        loginButton.addActionListener(new ActionListener() {
            /**
             * Handles the login button click event.
             * Depending on if they are the seller or customer, opens seller/customer GUI
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                String enteredUser = userField.getText().trim();
                String enteredPass = new String(passField.getPassword()).trim();

                User authenticatedUser = User.validateUser(enteredUser, enteredPass);
                if (authenticatedUser != null) {
                    if (authenticatedUser.getUsername().equals("seller")) {
                        System.out.println("Hello seller!");
                        new SellerGUI(loginFrame);
                    } else if (authenticatedUser.getUsername().equals("customer")) {
                        System.out.println("Hello customer!");
                        new CustomerGUI(loginFrame);
                    }
                } else {
                    JOptionPane.showMessageDialog(loginFrame,
                            "Incorrect username or password.",
                            "Error logging in",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        loginFrame.add(panel);
        loginFrame.setVisible(true);
    }
}
