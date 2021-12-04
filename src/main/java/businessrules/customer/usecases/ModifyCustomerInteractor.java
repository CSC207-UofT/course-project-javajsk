package businessrules.customer.usecases;

import businessrules.customer.inputboundaries.ModifyCustomer;
import businessrules.dai.CustomerRepository;
import businessrules.dai.Hasher;
import businessrules.outputboundaries.CustomerBoundary;
import businessrules.outputboundaries.ObjectBoundary;
import businessrules.outputboundaries.RepositoryBoundary;
import businessrules.outputboundaries.ResponseObject;
import EntitiesUnitTest.Customer;

public class ModifyCustomerInteractor implements ModifyCustomer {
    CustomerRepository customerRepository;
    ObjectBoundary<Customer> customerObjectBoundary;
    RepositoryBoundary repositoryBoundary;
    CustomerBoundary customerBoundary;
    Hasher hasher;

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
