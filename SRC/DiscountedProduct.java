package SRC;


/**
 *
 * @author alex
 */
public class DiscountedProduct implements ProductInterface{
    private int productID;
    private double price;
    private String productName;
    private int discount;
    
    public DiscountedProduct(String productName, double price, int discount ){
        this.productName = productName;
        this.price = calculateDiscountedPrice(price, discount);
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

