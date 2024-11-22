package SRC;

import java.util.ArrayList;

/**
 *
 * @author alex
 */
public class ProductBundle extends Product{
    private ArrayList<Product> productsInBundle = new ArrayList<>();
    private String bundleName;
    public ProductBundle(String bundleName){
        super();
        this.bundleName = bundleName;
    }
    void add(Product product){
        productsInBundle.add(product);
    }
    String getBundleName(){
        return bundleName;
    }
    void remove(Product product){
        if(productsInBundle.isEmpty()){
            System.out.println("Error");
        }else{
            System.out.println("Removing");
            //tbi
        }
    }
    public double getPrice(){
        double total = 0;
        for(Product current: productsInBundle){
            total += current.getPrice();
        }

        return total;
    }
    
    public String getName(){
        StringBuilder sb = new StringBuilder();
        sb.append(this.getBundleName()+" { ");
        for(Product current: productsInBundle){
            sb.append(current.getName()+", ");
        }
        sb.delete(sb.length()-2, sb.length());
        sb.append("}");
       
        return sb.toString();
    }

    public String getProductInfo() {
        return this.toString();
    }
}
