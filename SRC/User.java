package src;

import java.util.Arrays;
import java.util.List;

/**
 * User.java
 *
 * Handles authentication & logging in, as well as stores users
 *
 */
public class User {
    private String username;
    private String password;

    private static final List<User> VALID_USERS = Arrays.asList(
            new User("seller", "123"),
            new User("customer", "123")
    );

    /**
     * Creates a new User with the specified username and password
     *
     * @param username the username of the user
     * @param password the password of the user
     */
    public User(String username, String password){
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
}
