package UseCasesTest.Customer;

import UseCasesTest.TestBoundaries.RAMCustomerBoundary;
import UseCasesTest.TestBoundaries.RAMCustomerObjectBoundary;
import UseCasesTest.TestBoundaries.RAMRepositoryBoundary;
import UseCasesTest.daitesters.RAMCustomerRepository;
import UseCasesTest.daitesters.RAMShopRepository;
import adapters.dam.SHA512Hasher;
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

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CustomerSignUpInteractorTest {
    RAMCustomerRepository customerRepository;
    CustomerSignUpInteractor customerSignUpInteractor;
    RepositoryBoundary repositoryBoundary;
    CustomerBoundary customerBoundary;
    ObjectBoundary<Customer> objectBoundary;
    Hasher hasher;

    @BeforeEach
    void setUp() {
        hasher = new SHA512Hasher();
        customerBoundary = new RAMCustomerBoundary();
        repositoryBoundary = new RAMRepositoryBoundary();
        objectBoundary = new RAMCustomerObjectBoundary();
        Customer start_customer = new Customer("10000", "Username1", "Password1");
        customerRepository = new RAMCustomerRepository(start_customer);
        customerSignUpInteractor = new CustomerSignUpInteractor(customerRepository, repositoryBoundary,
                customerBoundary, objectBoundary, hasher);
    }

    @Test
    void mismatchPassword() {
        ResponseObject responseObject = customerSignUpInteractor.signUp("Username",
                "Password", "Password1");
        assertEquals("Passwords do not match.", responseObject.getMessage());
    }

    @Test
    void usedUsername() {
       ResponseObject responseObject = customerSignUpInteractor.signUp("Username1",
               "Password2", "Password2");
       assertEquals("Username is already taken!", responseObject.getMessage());
    }

    @Test
    void successfulSignUp() {
        ResponseObject responseObject = customerSignUpInteractor.signUp("Username",
                "Password", "Password");
        Customer new_customer = customerRepository.read("N/A");
        assertNotNull(new_customer);
        assertEquals(hasher.hash("Password"), new_customer.getHashedPassword());
    }
}