// ApplyDiscountCommand.java
package src;

import javax.swing.*;

public class ApplyDiscountCommand implements Command {
    private double totalAmount;
    private double discountedAmount;
    private String discountCode;

    public ApplyDiscountCommand(double totalAmount, String discountCode) {
        this.totalAmount = totalAmount;
        this.discountCode = discountCode;
        this.discountedAmount = totalAmount;
    }

    @Override
    public void execute() {
        // Currently only have one discount code. HALF OFF for black friday. Maybe we'll do 70% off for christmas.
        if ("BLACKFRIDAY".equalsIgnoreCase(discountCode.trim())) {
            discountedAmount = totalAmount * 0.5;
            JOptionPane.showMessageDialog(null, "Discount applied: 50% off!");
        } else {
            discountedAmount = totalAmount;
            JOptionPane.showMessageDialog(null, "Invalid discount code.");
        }
    }

    public double getDiscountedAmount() {
        return discountedAmount;
    }
}
