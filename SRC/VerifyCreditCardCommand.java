// VerifyCreditCardCommand.java
package src;

import javax.swing.*;

public class VerifyCreditCardCommand implements Command {
    @Override
    public void execute() {
        // No actual validation is done
        // Just say that the card was confirmed
        JOptionPane.showMessageDialog(null, "Credit card confirmed.");
    }
}
