package businessrules.customer.usecases;

import businessrules.customer.inputboundaries.CustomerSignUp;
import businessrules.dai.CustomerRepository;
import businessrules.dai.Hasher;
import businessrules.outputboundaries.CustomerBoundary;
import businessrules.outputboundaries.ObjectBoundary;
import businessrules.outputboundaries.RepositoryBoundary;
import businessrules.outputboundaries.ResponseObject;
import entities.Customer;

/**
 * Use case for signing up a customer and adding it to a repository
 */
public class CustomerSignUpInteractor implements CustomerSignUp {
    CustomerRepository customerRepository;
    RepositoryBoundary repositoryBoundary;
    CustomerBoundary customerBoundary;
    ObjectBoundary<Customer> customerObjectBoundary;
    Hasher hasher;

    /**
     * Instantiates a customer sign up use case
     * @param cR the customer repository
     * @param rB the repository boundary
     * @param cB the customer boundary
     * @param cOB the customer object boundary
     * @param h the hasher
     */
    public CustomerSignUpInteractor(CustomerRepository cR, RepositoryBoundary rB,
                                    CustomerBoundary cB, ObjectBoundary<Customer> cOB, Hasher h) {

        this.customerRepository = cR;
        this.repositoryBoundary = rB;
        this.customerBoundary = cB;
        this.customerObjectBoundary = cOB;
        this.hasher = h;
    }

    /**
     * Method for signing up as a customer
     * @param username the new username
     * @param password the new password
     * @param passwordConf the password confirmation
     * @return a response object
     */
    @Override
    public ResponseObject signUp(String username, String password, String passwordConf) {
        if(!password.equals(passwordConf)){
            return customerBoundary.error("Passwords do not match.");
        }

        Customer customer = customerRepository.findOneByFieldName("username", username);
        if(customer != null){
            return customerBoundary.error("Username is already taken!");
        }

        String cypherText = hasher.hash(password);

        Customer customerNew = new Customer("N/A",username,cypherText);

        String custId = customerRepository.create(customerNew);
        if(custId == null){
            return repositoryBoundary.creationFailed("Unable to create user.");
        }
        customerNew.setId(custId);

        return customerObjectBoundary.showObject(customerNew);
    }
}
