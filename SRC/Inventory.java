package SRC;

import java.util.ArrayList;

public class Inventory {
    private ArrayList<Product> products= new ArrayList<>();
    public Inventory(){

    }
    void addProduct(Product product, int quantity){}
    void removeProduct(Product product){}
    void updateProduct(Product product){}

    boolean checkAvailability(Product product){
        return false;
    }
    Product getAvailableProducts(){
        return new Product(null, 0);
    }
    


}
