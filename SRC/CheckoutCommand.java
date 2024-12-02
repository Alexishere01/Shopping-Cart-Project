// CheckoutCommand.java
package src;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;

/**
 *
 * @author Ryan, Alex, Austin
 */
public class CheckoutCommand implements Command {
    private ShoppingCart shoppingCart;
    private Inventory inventory;

    public CheckoutCommand(ShoppingCart shoppingCart, Inventory inventory) {
        this.shoppingCart = shoppingCart;
        this.inventory = inventory;
    }

    @Override
    public void execute() {
        // Create the checkout frame
        JFrame checkoutFrame = new JFrame("Checkout Screen");
        checkoutFrame.setSize(500, 400);
        checkoutFrame.setLocationRelativeTo(null);
        checkoutFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBorder(new EmptyBorder(10, 10, 10, 10));

        // Credit Card input
        JPanel creditCardPanel = new JPanel(new GridLayout(4, 2, 5, 5));
        creditCardPanel.setBorder(BorderFactory.createTitledBorder("Credit Card Information"));

        JLabel cardNumberLabel = new JLabel("Card Number:");
        JTextField cardNumberField = new JTextField();

        JLabel cardCVCLabel = new JLabel("CVC:");
        JTextField cardCVCField = new JTextField();

        JLabel cardExpiryLabel = new JLabel("Expiration Date:");
        JTextField cardExpiryField = new JTextField();

        creditCardPanel.add(cardNumberLabel);
        creditCardPanel.add(cardNumberField);
        creditCardPanel.add(cardCVCLabel);
        creditCardPanel.add(cardCVCField);
        creditCardPanel.add(cardExpiryLabel);
        creditCardPanel.add(cardExpiryField);

        // Discount code input
        JPanel discountPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        discountPanel.setBorder(BorderFactory.createTitledBorder("Discount Code"));

        JLabel discountLabel = new JLabel("Enter Discount Code:");
        JTextField discountField = new JTextField(15);
        JButton applyDiscountButton = new JButton("Apply Discount");

        discountPanel.add(discountLabel);
        discountPanel.add(discountField);
        discountPanel.add(applyDiscountButton);

        // Total Label
        JLabel totalLabel = new JLabel("Total: $" + String.format("%.2f", shoppingCart.calculateTotal()));
        totalLabel.setFont(new Font("Arial", Font.BOLD, 16));
        totalLabel.setHorizontalAlignment(SwingConstants.RIGHT);

        // Buttons Panel
        JPanel buttonsPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));

        JButton cancelButton = new JButton("Cancel");
        JButton buyButton = new JButton("Buy");

        buttonsPanel.add(cancelButton);
        buttonsPanel.add(buyButton);

        // Add components to main panel
        mainPanel.add(creditCardPanel, BorderLayout.NORTH);
        mainPanel.add(discountPanel, BorderLayout.CENTER);
        mainPanel.add(totalLabel, BorderLayout.SOUTH);

        // Add mainPanel and buttonsPanel to checkoutFrame
        checkoutFrame.setLayout(new BorderLayout());
        checkoutFrame.add(mainPanel, BorderLayout.CENTER);
        checkoutFrame.add(buttonsPanel, BorderLayout.SOUTH);

        // Apply Discount Button Action
        applyDiscountButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String discountCode = discountField.getText();
                ApplyDiscountCommand applyDiscount = new ApplyDiscountCommand(shoppingCart.calculateTotal(), discountCode);
                applyDiscount.execute();
                double newTotal = applyDiscount.getDiscountedAmount();
                totalLabel.setText("Total: $" + String.format("%.2f", newTotal));
            }
        });

        // Buy Button
        buyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Verify the credit card
                String cardNumber = cardNumberField.getText();
                String cardCVC = cardCVCField.getText();
                String cardExpiry = cardExpiryField.getText();

                if (cardNumber.trim().isEmpty() || cardCVC.trim().isEmpty() || cardExpiry.trim().isEmpty()) {
                    JOptionPane.showMessageDialog(checkoutFrame, "Please fill in all credit card details.");
                    return;
                }

                VerifyCreditCardCommand verifyCreditCard = new VerifyCreditCardCommand();
                verifyCreditCard.execute();

                // Retrieve the updated total
                double totalSpent = Double.parseDouble(totalLabel.getText().replace("Total: $", ""));

                shoppingCart.clearCart();

                JOptionPane.showMessageDialog(checkoutFrame, "Thank you for your purchase, you spent: $" + String.format("%.2f", totalSpent));

                checkoutFrame.dispose();
            }
        });

        // Cancel Button. Don't press this! Please buy from our store!!!
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                checkoutFrame.dispose();
            }
        });

        // Make the frame visible
        checkoutFrame.setVisible(true);
    }
}
