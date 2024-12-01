package src;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * SellerGUI.java
 */
public class SellerGUI {
    /**
     * Constructs and displays the seller's GUI
     *
     * @param loginFrame the login frame to hide
     */
    public SellerGUI(JFrame loginFrame) {
        createAndShowSellerGUI(loginFrame);
    }

    /**
     * Creates and displays the seller's GUI
     *
     * @param loginFrame the login frame to hide
     */
    private void createAndShowSellerGUI(JFrame loginFrame) {
        // Hide the login frame
        loginFrame.setVisible(false);

        // Create the seller frame
        JFrame sellerFrame = new JFrame("Seller Screen");
        sellerFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        sellerFrame.setSize(600, 400);
        sellerFrame.setLocationRelativeTo(null);

        JPanel panel = new JPanel(new BorderLayout());

        ProductTable productTable = new ProductTable();
        panel.add(productTable.getScrollPane(), BorderLayout.CENTER);

        // Create buttons panel
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
                System.out.println("Add Product pressed");
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