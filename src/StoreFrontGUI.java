package src;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * CustomerGUI.java
 *
 * Represents the customer's interface within the application.
 * Allows customers to view products, manage their shopping cart, checkout, and logout.
 *
 */
public class StoreFrontGUI {
    private Warehouse warehouse;
    /**
     * Constructs and displays the customer's GUI
     *
     * @param loginFrame the login frame to hide
     */
    public StoreFrontGUI(JFrame loginFrame) {
        this.warehouse = warehouse.getInstance();
        createAndShowCustomerGUI(loginFrame);
    }

    /**
     * Constructs and displays the customer's GUI
     *
     * @param loginFrame the login frame to hide
     */
    private void createAndShowCustomerGUI(JFrame loginFrame) {
        // Hide the login frame
        loginFrame.setVisible(false);

        // Create the main frame
        JFrame customerFrame = new JFrame("Storefront");
        customerFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        customerFrame.setSize(550, 400);
        customerFrame.setLocationRelativeTo(null);

        JPanel panel = new JPanel(new BorderLayout());

        // Inventory
        Inventory combinedInventory = warehouse.getCombinedInventory();
        panel.add(combinedInventory.getScrollPane(), BorderLayout.CENTER);

        // Create Buttons panel
        JPanel buttonsPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));

        // View Shopping Cart button
        JButton viewCartButton = new JButton("View Shopping Cart");
        buttonsPanel.add(viewCartButton);
        viewCartButton.addActionListener(new ActionListener() {
            /**
             * View shopping cart button
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("View Shopping Cart pressed");
            }
        });

        // Checkout button
        JButton checkoutButton = new JButton("Checkout");
        buttonsPanel.add(checkoutButton);
        checkoutButton.addActionListener(new ActionListener() {
            
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Checkout pressed");
                new CheckoutGUI();
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
                customerFrame.dispose();
                loginFrame.setVisible(true);
            }
        });

        panel.add(buttonsPanel, BorderLayout.SOUTH);

        customerFrame.add(panel);
        customerFrame.setVisible(true);
    }
}
