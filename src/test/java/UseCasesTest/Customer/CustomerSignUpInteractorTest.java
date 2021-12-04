package UseCasesTest.Customer;

import businessrules.customer.inputboundaries.CustomerSignUp;
import businessrules.dai.CustomerRepository;
import businessrules.dai.Hasher;
import businessrules.outputboundaries.CustomerBoundary;
import businessrules.outputboundaries.ObjectBoundary;
import businessrules.outputboundaries.RepositoryBoundary;
import businessrules.outputboundaries.ResponseObject;
import entities.Customer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CustomerSignUpInteractorTest {
    CustomerRepository customerRepository;
    CustomerSignUp customerSignUp;
    RepositoryBoundary repositoryBoundary;
    CustomerBoundary customerBoundary;
    ObjectBoundary<Customer> customerObjectBoundary;
    Hasher hasher;

    @BeforeEach
    void setUp() {
    }

    @Test
    void login() {
        ResponseObject response = customerSignUp.login("new_username", "new_password",
                "new_password");
    }
}