package SRC;

/**
 *
 * @author alex
 */
public class Product implements ProductInterface{
    private int productID;
    private double price;
    private String productName;
    public Product(String productName, double price){
        this.productName = productName;
        this.price = price;
        //productID is hascode of both name and price
    }
    @Override
    public String getProductInfo() {
        return this.toString();
    }

    @Override
    public double getPrice() {
        return price;
    }

    @Override
    public String getName() {
        return productName;
    }
    
    public String toString(){
        return "{"+productID+price+productName+"}";
    }
    
}
