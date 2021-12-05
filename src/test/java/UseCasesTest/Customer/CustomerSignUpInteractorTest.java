package UseCasesTest.Customer;

import UseCasesTest.daitesters.RAMCustomerRepository;
import UseCasesTest.daitesters.RAMShopRepository;
import businessrules.customer.inputboundaries.CustomerSignUp;
import businessrules.customer.usecases.CustomerSignUpInteractor;
import businessrules.dai.CustomerRepository;
import businessrules.dai.Hasher;
import businessrules.outputboundaries.CustomerBoundary;
import businessrules.outputboundaries.ObjectBoundary;
import businessrules.outputboundaries.RepositoryBoundary;
import businessrules.outputboundaries.ResponseObject;
import entities.Customer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class CustomerSignUpInteractorTest {
    RAMCustomerRepository customerRepository;
    CustomerSignUpInteractor customerSignUpInteractor;
    RepositoryBoundary repositoryBoundary;
    CustomerBoundary customerBoundary;
    ObjectBoundary<Customer> objectBoundary;
    Hasher hasher;
    CustomerSignUp customerSignUp;

    @BeforeEach
    void setUp() {
        Customer start_customer = new Customer("10000", "Username1", "Password1");
        customerRepository = new RAMCustomerRepository(start_customer);
        customerSignUpInteractor = new CustomerSignUpInteractor(customerRepository, repositoryBoundary,
                customerBoundary, objectBoundary, hasher);
    }

    @Test
    void mismatchPassword() {
        assertEquals("Passwords do not match.", customerSignUp.signUp("Username",
                "Password", "Password1").getMessage());
    }

    @Test
    void successfulSignUp() {
        customerSignUpInteractor.signUp("Username", "Password", "Password");
        Customer new_customer = customerRepository.read("N/A");
        assertEquals("Username", new_customer.getUserName());
        assertEquals(hasher.hash("Password"), new_customer.getHashedPassword());
    }
}