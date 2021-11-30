package UseCases.DataAccessInterfaces;

import Entities.Interfaces.ICustomer;

/**
 * The Customer Interface
 *
 * This is an abstract interface for accessing a higher  with customers
 */
public interface CustomerRepository extends UserRepository {

    /**
     *  A method for retrieving a user with the given id
     *
     * @param id corresponds to the id of the customer
     * @return a user object that has a matching id
     */
    ICustomer getCustomer(String id);

    /**
     *  A method that removes a customer from the repository
     *
     * @param id corresponds to the id of the customer
     * @return true if the customer is deleted and false if not
     */
    Boolean deleteCustomer(String id);

    //TODO: DOC
    boolean save(ICustomer customer);

    boolean createCustomer(ICustomer customer);
}
