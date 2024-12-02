// ViewShoppingCartCommand.java
package src;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.Map;

/**
 *
 * @author Ryan
 */
public class ViewShoppingCartCommand implements Command {
    private ShoppingCart shoppingCart;

    public ViewShoppingCartCommand(ShoppingCart shoppingCart) {
        this.shoppingCart = shoppingCart;
    }

    @Override
    public void execute() {
        // Create a new JFrame to display the cart
        JFrame cartFrame = new JFrame("Shopping Cart");
        cartFrame.setSize(600, 400);
        cartFrame.setLocationRelativeTo(null);

        // Create a table to display cart items
        String[] columnNames = {"Product Name", "Description", "Price", "Quantity", "Total"};
        DefaultTableModel cartTableModel = new DefaultTableModel(columnNames, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Make cells non-editable
            }
        };
        JTable cartTable = new JTable(cartTableModel);

        // Populate the table with cart items
        for (Map.Entry<Product, Integer> entry : shoppingCart.getCartItemsMap().entrySet()) {
            Product product = entry.getKey();
            int quantity = entry.getValue();
            double total = product.getPrice() * quantity;
            cartTableModel.addRow(new Object[]{
                    product.getName(),
                    product.getDescription(),
                    String.format("$%.2f", product.getPrice()),
                    quantity,
                    String.format("$%.2f", total)
            });
        }

        // Calculate total cost
        double totalCost = shoppingCart.calculateTotal();

        // Create a label to display the total cost
        JLabel totalLabel = new JLabel("Total Cost: $" + String.format("%.2f", totalCost));
        totalLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        totalLabel.setFont(new Font("Arial", Font.BOLD, 14));
        totalLabel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Add components to the frame
        cartFrame.setLayout(new BorderLayout());
        cartFrame.add(new JScrollPane(cartTable), BorderLayout.CENTER);
        cartFrame.add(totalLabel, BorderLayout.SOUTH);

        // Make the frame visible
        cartFrame.setVisible(true);
    }
}