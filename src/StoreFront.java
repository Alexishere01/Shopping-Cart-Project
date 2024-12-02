package src;

import javax.swing.*;
import java.awt.*;
/**
 *
 * @author ausgood
 */
public class StoreFront {
    private ShoppingCart cart;
    
    public StoreFront() {
        this.cart = new ShoppingCart();
        createAndShowCheckoutGUI();
    }
    
    // Creates and displays checkout GUI
    private void createAndShowCheckoutGUI() {
        JFrame frame = new JFrame("Checkout Screen");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);
        frame.setLocationRelativeTo(null);

        JPanel panel = new JPanel(new GridLayout(3, 1, 10, 10));

        // Confirm Checkout Button
        JButton confirmButton = new JButton("Confirm Checkout");
        confirmButton.addActionListener(e -> {
            Command command = new ConfirmCheckoutCommand(cart);
            command.execute();
        });

        // Apply Discount Button
        JButton discountButton = new JButton("Apply Discount");
        discountButton.addActionListener(e -> {
            Command command = new ApplyDiscountCommand();
            command.execute();
        });

        // Cancel Checkout Button
        JButton cancelButton = new JButton("Cancel Checkout");
        cancelButton.addActionListener(e -> {
            Command command = new CancelCheckoutCommand();
            command.execute();
        });

        // Add buttons to the panel
        panel.add(confirmButton);
        panel.add(discountButton);
        panel.add(cancelButton);

        frame.add(panel);
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        new StoreFront();
    }
}

