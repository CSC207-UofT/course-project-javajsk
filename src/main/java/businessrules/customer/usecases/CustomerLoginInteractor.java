package businessrules.customer.usecases;

import businessrules.customer.inputboundaries.CustomerLogin;
import businessrules.dai.CustomerRepository;
import businessrules.dai.Hasher;
import businessrules.outputboundaries.CustomerBoundary;
import businessrules.outputboundaries.RepositoryBoundary;
import businessrules.outputboundaries.ResponseObject;

/**
 * Use case for logging in a customer with repository data
 */
public class CustomerLoginInteractor implements CustomerLogin {
    /**
     * The Customer repository.
     */
    CustomerRepository customerRepository;
    /**
     * The Customer boundary.
     */
    CustomerBoundary customerBoundary;
    /**
     * The Repository boundary.
     */
    RepositoryBoundary repositoryBoundary;
    /**
     * The Hasher.
     */
    Hasher hasher;

    /**
     * Instantiates a new Customer login interactor.
     *
     * @param customerRepository the customer repository
     * @param customerBoundary   the customer boundary
     * @param repositoryBoundary the repository boundary
     * @param hasher             the hasher
     */
    public CustomerLoginInteractor(CustomerRepository customerRepository, CustomerBoundary customerBoundary,
                                   RepositoryBoundary repositoryBoundary, Hasher hasher) {
        this.customerRepository = customerRepository;
        this.customerBoundary = customerBoundary;
        this.repositoryBoundary = repositoryBoundary;
        this.hasher = hasher;
    }

    /**
     * Method for logging in customer
     * @param username the customer username
     * @param password the customer password
     * @return a response object
     */
    @Override
    public ResponseObject login(String username, String password) {
        String hashedPassword = hasher.hash(password);
        String token = customerRepository.authenticateUser(username, hashedPassword);

        if(token == null){
            return repositoryBoundary.queryNotFound("Incorrect username or password.");
        }

        return customerBoundary.displayToken(token);
    }
}
