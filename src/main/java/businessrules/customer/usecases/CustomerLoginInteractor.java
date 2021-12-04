package businessrules.customer.usecases;

import businessrules.customer.inputboundaries.CustomerLogin;
import businessrules.dai.CustomerRepository;
import businessrules.outputboundaries.CustomerBoundary;
import businessrules.outputboundaries.RepositoryBoundary;
import businessrules.outputboundaries.ResponseObject;

/**
 * Use case for logging in a customer with repository data
 */
public class CustomerLoginInteractor implements CustomerLogin {
    CustomerRepository customerRepository;
    CustomerBoundary customerBoundary;
    RepositoryBoundary repositoryBoundary;

    /**
     * Instantiates the use case for logging in a customer
     * @param cR the customer repository
     * @param cB the customer boundary
     * @param rB the repository boundary
     */
    public CustomerLoginInteractor(CustomerRepository cR, CustomerBoundary cB,
                                   RepositoryBoundary rB) {
        this.customerRepository = cR;
        this.customerBoundary = cB;
        this.repositoryBoundary = rB;
    }

    /**
     * Method for logging in customer
     * @param username the customer username
     * @param password the customer password
     * @return a response object
     */
    @Override
    public ResponseObject login(String username, String password) {
        String token = customerRepository.authenticateUser(username, password);

        if(token == null){
            return repositoryBoundary.queryNotFound("Unable to locate such user.");
        }

        return customerBoundary.displayToken(token);
    }
}
