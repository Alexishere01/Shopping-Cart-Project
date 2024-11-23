package src;

import java.util.HashMap;
import java.util.Map;

public class Inventory {
    private Map<Product, Integer> inventory = new HashMap<>();
    public Inventory(){

    }
    void addProduct(Product product, int quantity){
        inventory.put(product, quantity);
    }
    void updateProduct(Product product ,int quantity ){
        if(inventory.get(product)==null){
            //Does not exisit
        }else if (inventory.get(product) >  0){
            inventory.put(product,quantity);
        }
    }
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("Inventory{ ");
        for (Map.Entry<Product, Integer> entry : inventory.entrySet()) {
            String currentProduct= entry.getKey().getName();
            sb.append("{"+currentProduct+", "+entry.getValue()+"}, ");
        }
        sb.delete(sb.length()-2, sb.length());
        sb.append("}");
        return sb.toString();
    }
    void removeProduct(Product product){

    }

    boolean checkAvailability(Product product){
        return false;
    }
    Product getAvailableProducts(){
        return new Product(null, 0);
    }
    


}
