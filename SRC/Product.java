package src;

import java.util.Objects;

/**
 *
 * @author alex
 */
public class Product implements ProductInterface{
    private int productID;
    private double price;
    private String productName;
    private String description;
    public Product(String productName, String description, double price){
        this.productName = productName;
        this.price = price;
        this.description = description;
        //productID is hascode of both name and price
    }
    public Product(){

    }
//    @Override
//    public boolean equals(Object obj) {
//        if (this == obj) return true;
//        if (obj == null || getClass() != obj.getClass()) return false;
//        Product product = (Product) obj;
//        return Double.compare(product.price, price) == 0 &&
//                name.equals(product.name) &&
//                description.equals(product.description);
//    }

//    @Override
//    public int hashCode() {
//        return Objects.hash(name, description, price);
//    }


    @Override
    public String getProductInfo() {
        return this.toString();
    }

    @Override
    public double getPrice() {
        return price;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String getName() {
        return productName;
    }
    
    public String toString(){
        return "{"+productID+price+productName+"}";
    }
    
}
