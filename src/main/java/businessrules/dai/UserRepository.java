package businessrules.dai;

import entities.User;

/**
 * Interface for user repositories to implement
 */
public interface UserRepository {
    /**
     * Method for getting a user entity from a user token
     *
     * @param userToken the user token
     * @return the corresponding user token
     */
    User getUserFromToken(String userToken);

    /**
     * Method for authenticating a user's login information
     *
     * @param username the user's username
     * @param password the user's password
     * @return the authentication token for the user
     */
    String authenticateUser(String username, String password);
}
