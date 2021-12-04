package businessrules.customer.usecases;

import businessrules.customer.inputboundaries.CustomerSignUp;
import businessrules.dai.CustomerRepository;
import businessrules.dai.Hasher;
import businessrules.outputboundaries.CustomerBoundary;
import businessrules.outputboundaries.ObjectBoundary;
import businessrules.outputboundaries.RepositoryBoundary;
import businessrules.outputboundaries.ResponseObject;
import EntitiesUnitTest.Customer;

public class CustomerSignUpInteractor implements CustomerSignUp {
    CustomerRepository customerRepository;
    RepositoryBoundary repositoryBoundary;
    CustomerBoundary customerBoundary;
    ObjectBoundary<Customer> customerObjectBoundary;
    Hasher hasher;

    public ResponseObject signUp(String username, String password, String passwordConf) {
        if(!password.equals(passwordConf)){
            customerBoundary.error("Passwords do not match.");
        }

        Customer customer = customerRepository.findOneByFieldName("username", username);
        if(customer != null){
            customerBoundary.error("Username is already taken!");
        }

        String cypherText = hasher.hash(password);

        Customer customerNew = new Customer("N/A", username,password);

        String custId = customerRepository.create(customerNew);
        if(custId == null){
            repositoryBoundary.creationFailed("Unable to create user.");
        }
        customerNew.setId(custId);

        return customerObjectBoundary.showObject(customerNew);
    }

}
