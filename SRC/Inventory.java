package src;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class Inventory implements Serializable, Iterable<Map.Entry<Product, Integer>> {
    private static final long serialVersionUID = 1L;

    // Singleton, beacuse there is only one inventory.
    private static Inventory instance;

    private Map<Product, Integer> inventory = new HashMap<>();
    private transient DefaultTableModel tableModel;
    private transient JTable table;
    private transient JScrollPane scrollPane;

    private Inventory() {
        String[] columnNames = {"Product Name", "Description", "Price", "Quantity"};
        tableModel = new DefaultTableModel(columnNames, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Make cells non-editable
            }
        };
        table = new JTable(tableModel);
        scrollPane = new JScrollPane(table);

        // add some default products for testing
        System.out.println("Adding default products");
        addProduct(new Product("Default Product 1", "Sample description 1", 10.99), 10);
        addProduct(new Product("Default Product 2", "Sample description 2", 20.49), 5);
    }

    // Public method to access the singleton instance
    public static Inventory getInstance() {
        if (instance == null) {
            instance = new Inventory();
        }
        return instance;
    }

    // Methods for managing inventory
    public void addProduct(Product product, int quantity) {
        if (inventory.containsKey(product)) {
            int currentQuantity = inventory.get(product);
            inventory.put(product, currentQuantity + quantity);
            updateTable(product);
        } else {
            inventory.put(product, quantity);
            addProductToTable(product, quantity);
        }
    }

    public void updateProduct(Product product, int quantity) {
        if (inventory.containsKey(product)) {
            inventory.put(product, quantity);
            updateTable(product);
        }
    }

    public void removeProduct(Product product) {
        if (inventory.containsKey(product)) {
            inventory.remove(product);
            removeProductFromTable(product);
        }
    }

    public boolean checkAvailability(Product product) {
        return inventory.getOrDefault(product, 0) > 0;
    }

    public JScrollPane getScrollPane() {
        if (scrollPane == null) {
            initTableComponents();
            reloadTableData();
        }
        return scrollPane;
    }

    private void initTableComponents() {
        String[] columnNames = {"Product Name", "Description", "Price", "Quantity"};
        tableModel = new DefaultTableModel(columnNames, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        table = new JTable(tableModel);
        scrollPane = new JScrollPane(table);
    }

    private void addProductToTable(Product product, int quantity) {
        tableModel.addRow(new Object[]{product.getName(), product.getDescription(), product.getPrice(), quantity});
    }

    private void updateTable(Product product) {
        if (tableModel == null) {
            initTableComponents();
        }
        for (int i = 0; i < tableModel.getRowCount(); i++) {
            String productName = (String) tableModel.getValueAt(i, 0);
            if (productName.equals(product.getName())) {
                tableModel.setValueAt(product.getDescription(), i, 1);
                tableModel.setValueAt(inventory.get(product), i, 3);
                return;
            }
        }
    }

    // Find the actual product instance based on the name
    public Product getProductByName(String name) {
        for (Product p : inventory.keySet()) {
            if (p.getName().equals(name)) {
                return p;
            }
        }
        return null;
    }


    private void removeProductFromTable(Product product) {
        for (int i = 0; i < tableModel.getRowCount(); i++) {
            String productName = (String) tableModel.getValueAt(i, 0);
            if (productName.equals(product.getName())) {
                tableModel.removeRow(i);
                return;
            }
        }
    }

    private void reloadTableData() {
        for (Map.Entry<Product, Integer> entry : inventory.entrySet()) {
            addProductToTable(entry.getKey(), entry.getValue());
        }
    }

    public void updateProductQuantity(Product product, int newQuantity) {
        if (inventory.containsKey(product)) {
            if (newQuantity > 0) {
                inventory.put(product, newQuantity);
                updateTable(product);
                System.out.println("updated quantity");
            } else {
                removeProduct(product);
            }
        }
    }

    public JTable getTable() {
        return table;
    }

    public DefaultTableModel getTableModel() {
        return tableModel;
    }

    @Override
    public java.util.Iterator<Map.Entry<Product, Integer>> iterator() {
        return inventory.entrySet().iterator();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Inventory: ");
        for (Map.Entry<Product, Integer> entry : inventory.entrySet()) {
            sb.append("{").append(entry.getKey().getName()).append(", ").append(entry.getValue()).append("}, ");
        }
        return sb.length() > 2 ? sb.substring(0, sb.length() - 2) : sb.toString();
    }
}
