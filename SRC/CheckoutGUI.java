package src;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 *
 * @author ausgood, Ryan
 */
public class CheckoutGUI {
//    private JFrame checkoutFrame;
//
//    public CheckoutGUI(int orderID, User user, ArrayList<Product> purchasedItems, double totalAmount) {
//        super(orderID, user, purchasedItems, totalAmount);
//    }
//
//    public void displayCheckout() {
//        if (checkoutFrame == null) {
//           checkoutFrame = new JFrame("Checkout Screen");
//           checkoutFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//           checkoutFrame.setSize(400, 300);
//           checkoutFrame.setLocationRelativeTo(null);
//
//           JPanel panel = new JPanel(new GridLayout(3, 1, 10, 10));
//
//           JButton confirmButton = new JButton("Confirm Checkout");
//           confirmButton.addActionListener(e -> {
//               Command command = new ConfirmCheckoutCommand(this);
//               command.execute();
//           });
//
//           JButton discountButton = new JButton("Apply Discount");
//           discountButton.addActionListener(e -> {
//               Command command = new ApplyDiscountCommand(this);
//               command.execute();
//           });
//
//           JButton cancelButton = new JButton("Cancel Checkout");
//           cancelButton.addActionListener(e -> {
//               Command command = new CancelCheckoutCommand();
//               command.execute();
//               hideCheckout(); // Hide the GUI after canceling
//           });
//
//            // Add buttons to the panel
//           panel.add(confirmButton);
//           panel.add(discountButton);
//           panel.add(cancelButton);
//
//           checkoutFrame.add(panel);
//        }
//
//        checkoutFrame.setVisible(true);
//    }
//
//    public void hideCheckout() {
//        if (checkoutFrame != null) {
//            checkoutFrame.setVisible(false);
//        }
//    }
}
