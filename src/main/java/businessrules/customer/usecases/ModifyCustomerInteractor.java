package businessrules.customer.usecases;

import businessrules.customer.inputboundaries.ModifyCustomer;
import businessrules.dai.CustomerRepository;
import businessrules.dai.Hasher;
import businessrules.outputboundaries.CustomerBoundary;
import businessrules.outputboundaries.ObjectBoundary;
import businessrules.outputboundaries.RepositoryBoundary;
import businessrules.outputboundaries.ResponseObject;
import entities.Customer;

/**
 * Use case for modifying a customer in a repository
 */
public class ModifyCustomerInteractor implements ModifyCustomer {
    CustomerRepository customerRepository;
    ObjectBoundary<Customer> customerObjectBoundary;
    RepositoryBoundary repositoryBoundary;
    CustomerBoundary customerBoundary;
    Hasher hasher;

    /**
     * Instantiates a use case for modifying a customer
     * @param cR the customer repository
     * @param cOB the customer object boundary
     * @param rB the repository boundary
     * @param cB the customer boundary
     * @param h the password hasher
     */
    public ModifyCustomerInteractor(CustomerRepository cR, ObjectBoundary<Customer> cOB, RepositoryBoundary rB,
                                    CustomerBoundary cB, Hasher h) {
        this.customerRepository = cR;
        this.customerObjectBoundary = cOB;
        this.repositoryBoundary = rB;
        this.customerBoundary = cB;
        this.hasher = h;
    }

    /**
     * Method for modifying a customer
     * @param userToken the customer token
     * @param username the customer username
     * @param password the customer password
     * @param passwordConf the password confirmation
     * @return a response object
     */
    @Override
    public ResponseObject modify(String userToken, String username, String password, String passwordConf) {
        Customer customer = (Customer) customerRepository.getUserFromToken(userToken);
        if(customer == null){
            return repositoryBoundary.queryNotFound("Unable to find such a customer.");
        }
        if(!password.equals(passwordConf)){
            return customerBoundary.error("Passwords must match.");
        }
        customer.setUserName(username);
        String cypherText = hasher.hash(password);
        customer.setHashedPassword(cypherText);

        if(!customerRepository.update(customer.getId(), customer)){
            return repositoryBoundary.modificationFailed("Failed to update customer details.");
        }
        return customerObjectBoundary.showObject(customer);
    }
}
