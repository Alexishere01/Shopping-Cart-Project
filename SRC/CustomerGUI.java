package src;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * CustomerGUI.java
 *
 *
 * @author Ryan, Alex
 */
public class CustomerGUI {
    private ShoppingCart shoppingCart;

    public CustomerGUI(JFrame loginFrame) {
        this.shoppingCart = new ShoppingCart();
        createAndShowCustomerGUI(loginFrame);
    }

    // Create and show GUI
    private void createAndShowCustomerGUI(JFrame loginFrame) {
        loginFrame.setVisible(false);

        // Create main frame
        JFrame customerFrame = new JFrame("Storefront");
        customerFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        customerFrame.setSize(750, 400);
        customerFrame.setLocationRelativeTo(null);

        JPanel panel = new JPanel(new BorderLayout());

        // Use shared inventory instance
        Inventory inventory = Inventory.getInstance();
        panel.add(inventory.getScrollPane(), BorderLayout.CENTER);

        // Create Buttons panel
        JPanel buttonsPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));

        // Add to Shopping Cart button
        JButton addToCartButton = new JButton("Add to Shopping Cart");
        buttonsPanel.add(addToCartButton);
        AddToShoppingCartCommand addToCartCommand = new AddToShoppingCartCommand(inventory, shoppingCart);
        addToCartButton.addActionListener(new ActionListener() {
            /**
             * Add to Shopping Cart Button
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                addToCartCommand.execute();
            }
        });

        // View Shopping Cart button
        JButton viewCartButton = new JButton("View Shopping Cart");
        buttonsPanel.add(viewCartButton);
        ViewShoppingCartCommand viewCartCommand = new ViewShoppingCartCommand(shoppingCart);
        viewCartButton.addActionListener(new ActionListener() {
            /**
             * View Shopping Cart Button
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                viewCartCommand.execute();
            }
        });

        // Checkout button
        JButton checkoutButton = new JButton("Checkout");
        buttonsPanel.add(checkoutButton);
        CheckoutCommand checkoutCommand = new CheckoutCommand(shoppingCart, inventory);
        checkoutButton.addActionListener(new ActionListener() {
            /**
             * Checkout Button
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                checkoutCommand.execute();
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
