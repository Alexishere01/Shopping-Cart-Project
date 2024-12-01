package src;

/**
 * Command to confirm the checkout process.
 * Finalizes the user's order and performs necessary actions.
 */
public class ConfirmCheckoutCommand implements Command {
    private ShoppingCart cart; // Shopping cart reference
    
    // Constructor initializing ConfirmCheckoutCommand w/ a cart
    public ConfirmCheckoutCommand(ShoppingCart cart) {
        this.cart = cart;
    }
    
    @Override
    public void execute() {
        // logic needed
    }
}
