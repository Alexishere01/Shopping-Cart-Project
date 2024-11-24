package src;

import java.util.ArrayList;
/**
 *
 * @author alex
 */
public class Warehouse {
    private ArrayList<Inventory> warehouse = new ArrayList<>();
    public Warehouse(){

    }
    void addInventory(Inventory inventory){
        warehouse.add(inventory);
    }
    void removeInventory(){
        
    }
    Inventory getInventoryBySeller(Seller seller){
        return new Inventory();
    }
    void getAllProducts(){

    }
    
}
