package src;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


/**
 *
 * @author alex
 */
public class Warehouse {
    private List<Inventory> warehouse = new ArrayList<>();

    public void addInventory(Inventory inventory) {
        warehouse.add(inventory);
    }

    public void removeInventory(Inventory inventory) {
        warehouse.remove(inventory);
    }

    public Inventory getCombinedInventory() {
        Inventory combinedInventory = new Inventory();
        for (Inventory inventory : warehouse) {
            for (Map.Entry<Product, Integer> entry : inventory) {
                combinedInventory.addProduct(entry.getKey(), entry.getValue());
            }
        }
        return combinedInventory;
    }
    


    public List<Inventory> getAllInventories() {
        return warehouse;
    }
}