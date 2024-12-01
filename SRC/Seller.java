package src;
/**
 *
 * @author alex
 */
public class Seller extends User {
    private Inventory inventory;
    
    public Seller(String usr, String pwd){
        super(usr,pwd);
        this.inventory = new Inventory();
    }

    public Inventory getInventory() {
        return inventory;
    }
}
