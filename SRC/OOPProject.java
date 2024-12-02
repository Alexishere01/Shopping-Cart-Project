package src;

import javax.swing.SwingUtilities;

/**
 * OOPProject.java
 *
 * @author Ryan, Alex, Austin, Rudra
 */
public class OOPProject {
    /**
     * The main method to start the shopping cart
     */
    public static void main(String[] args) {
        //User.addUser(new Seller("test", "pass"));
        User.printAllUsers();

        SwingUtilities.invokeLater(() -> new src.LoginGUITM());
    }
}
