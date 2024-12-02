package src;

import javax.swing.*;
import java.awt.*;

/**
*
* @author Alex
 */
public class CreateAccountGUI extends AbstractLogin {
    private JFrame createAccountFrame;
    private JTextField userField;
    private JPasswordField passField;
    private JCheckBox checkBox; 
    private LoginGUITM loginGUITM;
    private User authenticatedUser; 
    
    public CreateAccountGUI(LoginGUITM loginGUITM) {
        this.loginGUITM = loginGUITM;
        createAndShowCreateAccountGUI();
    }

    private void createAndShowCreateAccountGUI() {
        // Main frame setup
        createAccountFrame = new JFrame("Create Account");
        createAccountFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        createAccountFrame.setSize(400, 250);
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

        // Account Type Checkbox
        checkBox = new JCheckBox("Are you a Seller?");
        gbc.gridx = 1; 
        gbc.gridy = 2; 
        gbc.anchor = GridBagConstraints.WEST; 
        panel.add(checkBox, gbc); 

        // Create account button
        JButton createAccountButton = new JButton("Create Account");
        gbc.gridx = 1;
        gbc.gridy = 3;
        panel.add(createAccountButton, gbc);

        // Login button
        JButton loginButton = new JButton("Login");
        gbc.gridx = 2;
        gbc.gridy = 3;
        panel.add(loginButton, gbc);

        // Add action listeners
        createAccountButton.addActionListener(e -> completeLogin());
        loginButton.addActionListener(e -> switchScreen());

        createAccountFrame.add(panel);
        createAccountFrame.setVisible(true);
    }

    void switchScreen() {
        createAccountFrame.setVisible(false);
        loginGUITM.showScreen();
    }

    @Override
    void getUserInput() {
        System.out.println("Getting User Input...");
        String username = userField.getText().trim();
        String password = new String(passField.getPassword()).trim();
        
        if (username.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(createAccountFrame,
                    "Username and password cannot be empty.",
                    "Error creating account",
                    JOptionPane.ERROR_MESSAGE);
            authenticatedUser = null;
        } else {
            // Get the state of the checkbox and create user accordingly
            boolean isSeller = checkBox.isSelected();
            if (isSeller) {
                authenticatedUser = new Seller(username, password);
            } else {
                authenticatedUser = new Buyer(username, password);
            }
        }
    }

    @Override
    void authenticate() {
        if (authenticatedUser != null) {
            User.addUser(authenticatedUser); // Add user to VALID_USERS and save
            JOptionPane.showMessageDialog(createAccountFrame,
                    "Account created successfully!",
                    "Success",
                    JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(createAccountFrame,
                    "Account creation failed. Please try again.",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    @Override
    void checkUserType() {
        if (authenticatedUser != null) {
            //Inventory sharedInventory = new Inventory();

            if (authenticatedUser instanceof Seller) {
                System.out.println("Hello seller!");
                new SellerGUI(createAccountFrame, (Seller) authenticatedUser);
            } else if (authenticatedUser instanceof Buyer) {
                System.out.println("Hello customer!");
                new StoreFrontGUI(createAccountFrame);
            }
        }
    }


    public void showScreen() {
        createAccountFrame.setVisible(true);
    }
}
