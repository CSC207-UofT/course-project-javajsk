package businessrules.customer.usecases;

import businessrules.customer.inputboundaries.CustomerLogin;
import businessrules.dai.CustomerRepository;
import businessrules.outputboundaries.CustomerBoundary;
import businessrules.outputboundaries.RepositoryBoundary;
import businessrules.outputboundaries.ResponseObject;

public class CustomerLoginInteractor implements CustomerLogin {
    CustomerRepository customerRepository;
    CustomerBoundary customerBoundary;
    RepositoryBoundary repositoryBoundary;

    public ResponseObject login(String username, String password) {
        String token = customerRepository.authenticateUser(username, password);

        if(token == null){
            return repositoryBoundary.queryNotFound("Unable to locate such user.");
        }

        return customerBoundary.displayToken(token);
    }
}
