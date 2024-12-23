package src;

import javax.swing.*;
import java.awt.*;

public class LoginGUITM extends AbstractLogin {
    private JFrame loginFrame;
    private JTextField userField;
    private JPasswordField passField;
    private User authenticatedUser;
    private CreateAccountGUI createAccountGUI;

    public LoginGUITM() {
        createAndShowLoginGUI();
    }

    private void createAndShowLoginGUI() {
        // Main login frame
        loginFrame = new JFrame("Login Screen");
        loginFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        loginFrame.setSize(400, 200);
        loginFrame.setLocationRelativeTo(null);

        // Panel setup
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Username label and text field
        JLabel userLabel = new JLabel("Username:");
        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(userLabel, gbc);

        userField = new JTextField();
        gbc.gridx = 1;
        gbc.gridy = 0;
        panel.add(userField, gbc);

        // Password label and text field
        JLabel passLabel = new JLabel("Password:");
        gbc.gridx = 0;
        gbc.gridy = 1;
        panel.add(passLabel, gbc);

        passField = new JPasswordField();
        gbc.gridx = 1;
        gbc.gridy = 1;
        panel.add(passField, gbc);

        // Login button
        JButton loginButton = new JButton("Login");
        gbc.gridx = 1;
        gbc.gridy = 2;
        panel.add(loginButton, gbc);

        // Create Account Button
        JButton createAccountButton = new JButton("Create Account");
        gbc.gridx = 2;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        panel.add(createAccountButton, gbc);

        // Add action listeners
        loginButton.addActionListener(e -> completeLogin());
        createAccountButton.addActionListener(e -> switchScreen());

        loginFrame.add(panel);
        loginFrame.setVisible(true);
    }

    void switchScreen() {
        loginFrame.setVisible(false);
        if (createAccountGUI == null) {
            createAccountGUI = new CreateAccountGUI(this);
        }
        createAccountGUI.showScreen();
        
    }

    @Override
    void getUserInput() {
        System.out.println("Getting User Input...");
        String enteredUser = userField.getText().trim();
        String enteredPass = new String(passField.getPassword()).trim();
        authenticatedUser = User.validateUser(enteredUser, enteredPass);
    }

    @Override
    void authenticate() {
        System.out.println("Authenticating...");
        if (authenticatedUser == null) {
            JOptionPane.showMessageDialog(loginFrame,
                    "Incorrect username or password.",
                    "Error logging in",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    @Override
    void checkUserType() {
        System.out.println("Checking User Type...");
        if (authenticatedUser != null) {

            if (authenticatedUser instanceof Seller) {

                System.out.println("Hello seller!");
                new SellerGUI(loginFrame, (Seller) authenticatedUser);

            } else if (authenticatedUser instanceof Buyer) {

                System.out.println("Hello customer!");
                new CustomerGUI(loginFrame);

            }
        }
    }


    public void showScreen() {
        loginFrame.setVisible(true);
    }
}
