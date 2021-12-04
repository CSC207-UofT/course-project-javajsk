package UseCasesTest.Customer;

import businessrules.customer.inputboundaries.CustomerLogin;
import businessrules.dai.CustomerRepository;
import businessrules.outputboundaries.CustomerBoundary;
import businessrules.outputboundaries.RepositoryBoundary;
import businessrules.outputboundaries.ResponseObject;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CustomerLoginInteractorTest {
    CustomerRepository customerRepository;
    CustomerLogin customerLogin;
    CustomerBoundary customerBoundary;
    RepositoryBoundary repositoryBoundary;

    @Test
    void login() {
        ResponseObject response = customerLogin.login("Username", "Password");
        assertEquals("token", response);
    }
}