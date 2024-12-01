package src;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

/**
 * ProductTable.java
 *
 * Manages the creation and updating of the product table
 * Used in SellerGUI and CustomerGUI, same table
 */
public class ProductTable {
    private DefaultTableModel tableModel;
    private JTable table;
    private JScrollPane scrollPane;

    /**
     * Create new ProductTable
     */
    public ProductTable() {
        String[] columnNames = {"Name", "Description", "Cost", "Quantity"};

        tableModel = new DefaultTableModel(columnNames, 0) {
            /**
             * Make all cells non editable
             *
             * @param row the row index
             * @param column the column index
             * @return {@code false} to indicate cells are not editable
             */
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        table = new JTable(tableModel);

        // example rows to add onto later with the actual products that we add
        addProduct("Product1", "Description1", 10.0, 5);
        addProduct("Product2", "Description2", 20.0, 3);

        scrollPane = new JScrollPane(table);

        scrollPane.setPreferredSize(new java.awt.Dimension(550, 200));
    }

    /**
     * Returns the JScrollPane
     *
     * @return the JScrollPane
     */
    public JScrollPane getScrollPane() {
        return scrollPane;
    }

    /**
     * Adds new product to the table
     * If the product already exists (same name is provided), it updates the quantity and overrides the description and cost.
     *
     * @param name the name of the product
     * @param description the description of the product
     * @param cost the cost of the product
     * @param quantity the quantity to add to the product
     */
    public void addProduct(String name, String description, double cost, int quantity) {
        boolean productExists = false;
        int rowCount = tableModel.getRowCount();

        for (int i = 0; i < rowCount; i++) {
            String existingName = (String) tableModel.getValueAt(i, 0);
            if (existingName.equalsIgnoreCase(name)) {
                // Found existing product with the given name
                // update the existing product's information
                String newDescription = description;
                double newCost = cost;
                int existingQuantity = (int) tableModel.getValueAt(i, 3);
                int updatedQuantity = existingQuantity + quantity;

                tableModel.setValueAt(newDescription, i, 1);
                tableModel.setValueAt(newCost, i, 2);
                tableModel.setValueAt(updatedQuantity, i, 3);

                productExists = true;
                break;
            }
        }

        if (!productExists) {
            // Couldn't find an existing product with the given name
            // Add new product
            tableModel.addRow(new Object[]{name, description, cost, quantity});
        }
    }

    /**
     * Adds an empty row to the table
     */
    public void addEmptyRow() {
        tableModel.addRow(new Object[]{"", "", "", ""});
    }

    /**
     * Removes a product from the table based on the product name
     *
     * @param productName the name of the product to remove
     * @return {@code true} if the product was found and removed, else {@code false}
     */
    public boolean removeProductName(String productName) {
        for (int i = 0; i < tableModel.getRowCount(); i++) {
            String existingName = (String) tableModel.getValueAt(i, 0);
            if (existingName.equalsIgnoreCase(productName)) {
                tableModel.removeRow(i);
                return true;
            }
        }
        return false;
    }
}
