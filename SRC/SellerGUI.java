package src;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * SellerGUI.java
 *
 * @author Ryan, Alex
 */
public class SellerGUI {
    private Inventory inventory;

    public SellerGUI(JFrame loginFrame, Seller userSeller) {
        this.inventory = Inventory.getInstance();
        createAndShowSellerGUI(loginFrame, userSeller);
    }

    // Construct seller GUI & display
    private void createAndShowSellerGUI(JFrame loginFrame, Seller userSeller) {
        // Hide the login frame
        loginFrame.setVisible(false);

        // Create the seller frame
        JFrame sellerFrame = new JFrame("Seller Screen");
        sellerFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        sellerFrame.setSize(600, 400);
        sellerFrame.setLocationRelativeTo(null);

        JPanel panel = new JPanel(new BorderLayout());

        panel.add(inventory.getScrollPane(), BorderLayout.CENTER);

        // Buttons panel
        JPanel buttonsPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));

        // Add Product button
        JButton addProductButton = new JButton("Add Product");
        buttonsPanel.add(addProductButton);
        addProductButton.addActionListener(new ActionListener() {
            /**
             * Add Product Button
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                Command addProductCommand = new AddProductCommand(inventory);
                addProductCommand.execute();
            }
        });

        // Logout button
        JButton logoutButton = new JButton("Logout");
        buttonsPanel.add(logoutButton);
        logoutButton.addActionListener(new ActionListener() {
            /**
             * Logout Button
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                sellerFrame.dispose();
                loginFrame.setVisible(true);
            }
        });

        panel.add(buttonsPanel, BorderLayout.SOUTH);

        sellerFrame.add(panel);
        sellerFrame.setVisible(true);
    }
}
