package UseCases.DataAccessInterfaces;

import Entities.Interfaces.IAddon;
import Entities.Interfaces.ICustomer;

/**
 * The Customer Interface
 *
 * This is an abstract interface for accessing customers from a repository
 */
public interface CustomerRepository {

    /**
     *  A method for retrieving a user with the given id
     *
     * @param id corresponds to the id of the customer
     * @return a user object that has a matching id
     */
    ICustomer getCustomer(String id);
    /**
     *  A method that changes
     *
     * @param id corresponds to the id of the customer
     * @param customer the new customer object that will replace the existing one
     * @return true if the change is successful and false if the entry did not change
     */
    Boolean setCustomer(String id, ICustomer customer);
    /**
     *  A method that removes a customer from the repository
     *
     * @param id corresponds to the id of the customer
     * @return true if the customer is deleted and false if not
     */
    Boolean deleteCustomer(String id);
    /**
     *  A method for generating an authentication token for the user
     *
     * @param username the username of user
     * @param password  the password for the user
     *
     * @return the generated token in a string
     */
    String getAuthenticationToken(String username, String password);
    /**
     *  A method that finds the user with the given token
     * TODO: UPDATE THIS DOC
     * @param token the token we are using to find a user
     * @return the customer that matched the token
     */
    ICustomer getUserFromToken(String token);

    /**
     * A method for saving a customer to the repository
     *
     * @param customer the customer we want to save
     * @return true if the customer has been saved and false if it has not
     */
    boolean save(ICustomer customer);
}
