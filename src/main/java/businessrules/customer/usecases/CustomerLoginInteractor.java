package businessrules.customer.usecases;

import businessrules.customer.inputboundaries.CustomerLogin;
import businessrules.dai.CustomerRepository;
import businessrules.dai.Hasher;
import businessrules.outputboundaries.CustomerBoundary;
import businessrules.outputboundaries.RepositoryBoundary;
import businessrules.outputboundaries.ResponseObject;

public class CustomerLoginInteractor implements CustomerLogin {
    CustomerRepository customerRepository;
    CustomerBoundary customerBoundary;
    RepositoryBoundary repositoryBoundary;
    Hasher hasher;

    public ResponseObject login(String username, String password) {
        String hashedPassword = hasher.hash(password);
        String token = customerRepository.authenticateUser(username, hashedPassword);

        if(token == null){
            return repositoryBoundary.queryNotFound("Unable to locate such user.");
        }

        return customerBoundary.displayToken(token);
    }
}
