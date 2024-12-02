// AddToShoppingCartCommand.java
package src;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class AddToShoppingCartCommand implements Command {
    private Inventory inventory;
    private ShoppingCart shoppingCart;

    public AddToShoppingCartCommand(Inventory inventory, ShoppingCart shoppingCart) {
        this.inventory = inventory;
        this.shoppingCart = shoppingCart;
    }

    @Override
    public void execute() {
        JTable table = inventory.getTable();
        DefaultTableModel tableModel = inventory.getTableModel();

        // Ensure a row is selected
        int selectedRow = table.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(null, "Please select a product to add to the cart.");
            return;
        }

        // Retrieve product details from the selected row
        String productName = (String) tableModel.getValueAt(selectedRow, 0);
        String productDescription = (String) tableModel.getValueAt(selectedRow, 1);
        double productPrice;
        int availableQuantity;

        // Safely parse price and quantity
        try {
            Object priceObj = tableModel.getValueAt(selectedRow, 2);
            if (priceObj instanceof Double) {
                productPrice = (Double) priceObj;
            } else if (priceObj instanceof String) {
                productPrice = Double.parseDouble((String) priceObj);
            } else {
                throw new NumberFormatException("Invalid price format.");
            }

            Object quantityObj = tableModel.getValueAt(selectedRow, 3);
            if (quantityObj instanceof Integer) {
                availableQuantity = (Integer) quantityObj;
            } else if (quantityObj instanceof String) {
                availableQuantity = Integer.parseInt((String) quantityObj);
            } else {
                throw new NumberFormatException("Invalid quantity format.");
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(null, "Invalid data format for price or quantity.");
            return;
        }

        // Prompt for quantity to add
        String quantityStr = JOptionPane.showInputDialog(null, "Enter quantity to add to cart:");
        if (quantityStr == null || quantityStr.trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Quantity cannot be empty.");
            return;
        }

        int quantityToAdd;
        try {
            quantityToAdd = Integer.parseInt(quantityStr);
            if (quantityToAdd <= 0) {
                JOptionPane.showMessageDialog(null, "Quantity must be a positive integer.");
                return;
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(null, "Invalid quantity entered.");
            return;
        }

        // Check if enough quantity is available
        if (quantityToAdd > availableQuantity) {
            JOptionPane.showMessageDialog(null, "Not enough products left in the warehouse.");
            return;
        }

        // Create the Product object
        Product selectedProduct = new Product(productName, productDescription, productPrice);

        // Add to ShoppingCart
        boolean added = shoppingCart.addProduct(selectedProduct, quantityToAdd);
        if (!added) {
            JOptionPane.showMessageDialog(null, "Failed to add product to cart.");
            return;
        }

        // Update Inventory
        inventory.updateProductQuantity(selectedProduct, availableQuantity - quantityToAdd);

        JOptionPane.showMessageDialog(null, "Added " + quantityToAdd + " of " + productName + " to the cart.");
    }
}
