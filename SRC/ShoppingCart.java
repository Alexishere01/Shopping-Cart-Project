package src;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 *
 * @author ausgood, Ryan
 */
public class ShoppingCart {
    private Map<Product, Integer> cartItems;

    public ShoppingCart() {
        this.cartItems = new HashMap<>();
    }

    // if product exists in the cart already, quantity increases
    public boolean addProduct(Product product, int quantity) {
        if (quantity <= 0) {
            return false;
        }
        cartItems.put(product, cartItems.getOrDefault(product, 0) + quantity);
        return true;
    }

    // removes a specific quantity of a product from the cart
    public void removeProduct(Product product, int quantity) {
        if (cartItems.containsKey(product)) {
            int updatedQuantity = cartItems.get(product) - quantity;
            if (updatedQuantity > 0) {
                cartItems.put(product, updatedQuantity);
            } else {
                cartItems.remove(product);
            }
        }
    }

    // Clear shopping cart. used after the customer buys something, their cart must be cleared
    public void clearCart() {
        cartItems.clear();
        System.out.println("Shopping cart has been cleared.");
    }


    // retrieves product information
    public String getProductInfo() {
        StringBuilder info = new StringBuilder("Cart Items:\n");
        for (Map.Entry<Product, Integer> entry : cartItems.entrySet()) {
            Product product = entry.getKey();
            int quantity = entry.getValue();
            info.append(product.getProductInfo())
                    .append(", Quantity: ")
                    .append(quantity)
                    .append("\n");
        }
        return info.toString();
    }

    // calculates the total price of all of the items in the cart
    public double calculateTotal() {
        double total = 0;
        for (Map.Entry<Product, Integer> entry : cartItems.entrySet()) {
            Product product = entry.getKey();
            int quantity = entry.getValue();
            total += product.getPrice() * quantity;
        }
        return total;
    }

    // gets all cart items as an array of Products
    public Product[] getCartItems() {
        return cartItems.keySet().toArray(new Product[0]);
    }

    // gets sum of the cart
    public double getPrice() {
        return calculateTotal();
    }

    public String getName() {
        StringBuilder names = new StringBuilder("Products: ");
        for (Product product : cartItems.keySet()) {
            names.append(product.getName()).append(", ");
        }
        if (!cartItems.isEmpty()) {
            // Remove trailing comma and space
            names.delete(names.length() - 2, names.length());
        }
        return names.toString();
    }

    public Map<Product, Integer> getCartItemsMap() {
        return new HashMap<>(cartItems);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ShoppingCart that = (ShoppingCart) o;
        return Objects.equals(cartItems, that.cartItems);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cartItems);
    }
}
