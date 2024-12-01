package src;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class Inventory implements Serializable, Iterable<Map.Entry<Product, Integer>> {
      private static final long serialVersionUID = 1L;
    private Map<Product, Integer> inventory = new HashMap<>();
    private transient DefaultTableModel tableModel;
    private transient JTable table;
    private transient JScrollPane scrollPane;

    public Inventory() {
        // Initialize JTable for inventory display
        String[] columnNames = {"Product Name", "Price", "Quantity"};
        tableModel = new DefaultTableModel(columnNames, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Make cells non-editable
            }
        };
        table = new JTable(tableModel);
        scrollPane = new JScrollPane(table);
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
        String[] columnNames = {"Product Name", "Price", "Quantity"};
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
        tableModel.addRow(new Object[]{product.getName(), product.getPrice(), quantity});
    }
    
    private void updateTable(Product product) {
        if (tableModel == null) {
            initTableComponents();
        }
        for (int i = 0; i < tableModel.getRowCount(); i++) {
            String productName = (String) tableModel.getValueAt(i, 0);
            if (productName.equals(product.getName())) {
                tableModel.setValueAt(inventory.get(product), i, 2);
                return;
            }
        }
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
