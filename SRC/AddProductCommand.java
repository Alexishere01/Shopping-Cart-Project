package src;

import javax.swing.*;
/**
 *
 * @author Ryan
 */
public class AddProductCommand implements Command {
    private Inventory inventory;

    public AddProductCommand(Inventory inventory) {
        this.inventory = inventory;
    }

    @Override
    public void execute() {
        // Name
        String name = JOptionPane.showInputDialog(null, "Enter Product Name:");
        if (name == null || name.trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Product name cannot be empty.");
            return;
        }

        // Description
        String description = JOptionPane.showInputDialog(null, "Enter Product Description:");
        if (description == null) {
            description = "";
        }

        // Price
        String priceStr = JOptionPane.showInputDialog(null, "Enter Price:");
        double price;
        try {
            price = Double.parseDouble(priceStr);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Invalid price entered.");
            return;
        }

        // Quantity
        String quantityStr = JOptionPane.showInputDialog(null, "Enter Quantity:");
        int quantity;
        try {
            quantity = Integer.parseInt(quantityStr);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Invalid quantity entered.");
            return;
        }

        // Create product, and add the product to the inventory
        Product product = new Product(name, description, price);
        inventory.addProduct(product, quantity);

        // Product added to inventory.
        JOptionPane.showMessageDialog(null, "Product added successfully.");
    }
}
