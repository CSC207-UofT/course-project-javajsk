package UseCasesTest.Customer;

import UseCasesTest.TestBoundaries.RAMCustomerBoundary;
import UseCasesTest.TestBoundaries.RAMRepositoryBoundary;
import UseCasesTest.daitesters.RAMCustomerRepository;
import UseCasesTest.daitesters.RAMShopRepository;
import adapters.dam.SHA512Hasher;
import businessrules.customer.inputboundaries.CustomerLogin;
import businessrules.customer.usecases.CustomerLoginInteractor;
import businessrules.customer.usecases.CustomerSignUpInteractor;
import businessrules.dai.CustomerRepository;
import businessrules.dai.Hasher;
import businessrules.outputboundaries.CustomerBoundary;
import businessrules.outputboundaries.RepositoryBoundary;
import businessrules.outputboundaries.ResponseObject;
import entities.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CustomerLoginInteractorTest {
    RAMCustomerRepository customerRepository;
    CustomerBoundary customerBoundary;
    RepositoryBoundary repositoryBoundary;
    Hasher hasher;
    CustomerLoginInteractor customerLoginInteractor;

    @BeforeEach
    void setUp() {
        customerBoundary = new RAMCustomerBoundary();
        repositoryBoundary = new RAMRepositoryBoundary();
        hasher = new SHA512Hasher();
        Customer start_customer = new Customer("10000", "Username1", "Password1");
        customerRepository = new RAMCustomerRepository(start_customer);
        customerLoginInteractor = new CustomerLoginInteractor(customerRepository, customerBoundary,
                repositoryBoundary, hasher);
    }

    @Test
    void login() {
        ResponseObject responseObject = customerLoginInteractor.login("Username1", "Password1");
        assertEquals("User authenticated", responseObject.getContents());
    }

    @Test
    void unableLogin(){
        ResponseObject responseObject = customerLoginInteractor.login("Username12", "Password12");
        assertEquals("Unable to locate such user.", responseObject.getMessage());
    }
}