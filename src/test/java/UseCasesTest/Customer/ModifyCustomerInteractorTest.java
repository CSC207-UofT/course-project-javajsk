package UseCasesTest.Customer;

import UseCasesTest.TestBoundaries.RAMCustomerBoundary;
import UseCasesTest.TestBoundaries.RAMCustomerObjectBoundary;
import UseCasesTest.TestBoundaries.RAMRepositoryBoundary;
import UseCasesTest.daitesters.RAMCustomerRepository;
import UseCasesTest.daitesters.RAMShopRepository;
import adapters.dam.SHA512Hasher;
import businessrules.customer.usecases.CustomerLoginInteractor;
import businessrules.customer.usecases.ModifyCustomerInteractor;
import businessrules.dai.CustomerRepository;
import businessrules.dai.Hasher;
import businessrules.outputboundaries.CustomerBoundary;
import businessrules.outputboundaries.ObjectBoundary;
import businessrules.outputboundaries.RepositoryBoundary;
import businessrules.outputboundaries.ResponseObject;
import entities.Cart;
import entities.Customer;
import entities.Order;
import entities.OrderBook;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

class ModifyCustomerInteractorTest {
    RAMCustomerRepository customerRepository;
    ObjectBoundary<Customer> customerObjectBoundary;
    RepositoryBoundary repositoryBoundary;
    CustomerBoundary customerBoundary;
    Hasher hasher;
    ModifyCustomerInteractor modifyCustomerInteractor;
    Customer test_customer;

    @BeforeEach
    void setUp(){
        customerObjectBoundary = new RAMCustomerObjectBoundary();
        customerBoundary = new RAMCustomerBoundary();
        repositoryBoundary = new RAMRepositoryBoundary();
        hasher = new SHA512Hasher();
        test_customer = new Customer("10000", "Username1", "Password1");
        customerRepository = new RAMCustomerRepository(test_customer);
        modifyCustomerInteractor = new ModifyCustomerInteractor(customerRepository, customerObjectBoundary,
                repositoryBoundary, customerBoundary, hasher);
    }

    @Test
    void noCustomer(){
        ResponseObject responseObject = modifyCustomerInteractor.modify("20000", "Username2",
                "Password2", "Password2");
        assertEquals("Unable to find such a customer.", responseObject.getMessage());
    }

    @Test
    void passwordsNoMatch() {
        ResponseObject responseObject = modifyCustomerInteractor.modify("10000", "Username2",
                "Password2", "Password3");
        assertEquals("Passwords must match.", responseObject.getMessage());
    }

    @Test
    void successfulModify() {
        ResponseObject responseObject = modifyCustomerInteractor.modify("10000", "Username2",
                "Password2", "Password2");
        Customer changed_customer = customerRepository.read("10000");
        assertEquals("Username2", changed_customer.getUserName());
        assertEquals(hasher.hash("Password2"), changed_customer.getHashedPassword());
    }

}