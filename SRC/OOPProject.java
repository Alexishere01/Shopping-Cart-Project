package src;

import javax.swing.SwingUtilities;

/**
 * OOPProject.java
 */
public class OOPProject {
    /**
     * The main method to start the shopping cart
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new src.LoginGUI());
    }
}
