package src;


/**
 *
 * @author alex
 */
public class DiscountedProduct extends Product{
    private int productID;
    private double price;
    private String productName;
    private String description;
    private int discount;
    
    public DiscountedProduct(String productName, String description, double price, int discount ){
        this.productName = productName;
        this.price = calculateDiscountedPrice(price, discount);
        this.description = description;
        //productID is hascode of both name and price
    }
    
    double calculateDiscountedPrice(double price, int discount){
        double d = price*(discount/100);
        return price-d;
    }
    
    @Override
    public String getProductInfo() {
        return this.toString();
    }
    @Override
    public double getPrice() {
        return price-discount;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public String getName() {
        return productName;
    }
    
    public String toString(){
        return "{"+productID+description+price+productName+"}";
    }
    
}

