package UseCases.DataAcessInterfaces;

import Entities.User;

/**
 * The Customer Interface
 *
 * This is an abstract interface for accessing a higher  with customers
 */
public interface CustomerRepository {

    /**
     *  A method for retrieving a user with the given id
     *
     * @param id corresponds to the id of the customer
     * @return a user object that has a matching id
     */
    User getCustomer(String id);
    /**
     *  A method that changes
     *
     * @param id corresponds to the id of the customer
     * @param customer the new customer object that will replace the existing one
     * @return true if the change is successful and false if the entry did not change
     */
    Boolean setCustomer(String id, User customer);
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
     * @return the generated token in a string
     */
    String getAuthenticationToken();
    /**
     *  A method that checks if a token is still valid
     *
     * @param token the token we are checking
     * @return true if the token is valid and false if it isn't
     */
    Boolean isValidAuthToken(String token);
}
