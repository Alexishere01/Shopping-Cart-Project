package src;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * User.java
 *
 * Handles authentication & logging in, as well as stores users
 */
public class User implements Serializable {
    private static final long serialVersionUID = 1L;

    private String username;
    private String password;

    private static List<User> VALID_USERS = new ArrayList<>();

    static {
        // Load existing users from file
        loadUsers();
    }

    /**
     * Creates a new User with the specified username and password
     *
     * @param username the username of the user
     * @param password the password of the user
     */
    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    /**
     * Returns the username of the user
     *
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * Returns the password of the user
     *
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Checks if this user is equal to another user.
     *
     * @param obj the object to compare with this user
     * @return {@code true} if the specified object is equal to this user; else {@code false}
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;

        User user = (User) obj;

        if (!username.equals(user.username)) return false;
        return password.equals(user.password);
    }

    /**
     * Validates the user credentials against the list of valid users.
     *
     * @param username the username entered by the user
     * @param password the password entered by the user
     * @return the User object if credentials are valid; else {@code null}
     */
    public static User validateUser(String username, String password) {
        for (User user : VALID_USERS) {
            if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
                return user;
            }
        }
        return null;
    }

    /**
     * Adds a new user to the list of valid users and saves it to the file.
     *
     * @param user the new user to add
     */
    public static void addUser(User user) {
        VALID_USERS.add(user);
        saveUsers();
    }

    /**
     * Loads users from the users.dat file.
     */
    private static void loadUsers() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("users.dat"))) {
            VALID_USERS = (List<User>) ois.readObject();
        } catch (FileNotFoundException e) {
            System.out.println("No existing users file found. Starting with an empty user list.");
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * Saves users to the users.dat file.
     */
    private static void saveUsers() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("users.dat"))) {
            oos.writeObject(VALID_USERS);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void printAllUsers() {
        // Ensure we load the latest users from the file
        loadUsers();

        if (VALID_USERS == null || VALID_USERS.isEmpty()) {
            System.out.println("No users found.");
        } else {
            System.out.println("All Users:");
            for (User user : VALID_USERS) {
                String userType = user instanceof Seller ? "Seller" : "Buyer";
                System.out.println("Username: " + user.getUsername() + ", Type: " + userType);
            }
        }
    }
}
