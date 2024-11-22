package SRC;

/**
 *
 * @author alex
 */
public class OOPProject {

    /**
     * @param args the command line argumentsasdfasdf
     */
    public static void main(String[] args) {
        Product box = new Product("box", 5.53);
        Product box2 = new Product("box2", 9.53);
        ProductBundle boxBundle = new ProductBundle("The Box Bundle"); 
        boxBundle.add(box2);
        boxBundle.add(box);
        System.out.println(boxBundle.getPrice());
        System.out.println(boxBundle.getName());

        Inventory inv = new Inventory();
        inv.addProduct(box2, 4);
        inv.updateProduct(box2, 10);
        inv.addProduct(box, 3);
        inv.addProduct(boxBundle, 2);
        
        System.out.println(inv.toString());
    }
    
}
