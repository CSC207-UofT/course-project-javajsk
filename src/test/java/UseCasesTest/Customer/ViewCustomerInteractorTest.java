package UseCasesTest.Customer;

import UseCasesTest.TestBoundaries.RAMCustomerObjectBoundary;
import UseCasesTest.daitesters.RAMCustomerRepository;
import businessrules.customer.usecases.ViewCustomerInteractor;
import businessrules.dai.Repository;
import businessrules.outputboundaries.ObjectBoundary;
import businessrules.outputboundaries.ResponseObject;
import entities.Customer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ViewCustomerInteractorTest {
    Repository<Customer> customerRepository;
    ObjectBoundary<Customer> customerObjectBoundary;
    ViewCustomerInteractor viewCustomerInteractor;
    Customer start_customer;

    @BeforeEach
    void setUp() {
        start_customer = new Customer("10000", "Username1", "Password1");
        customerRepository = new RAMCustomerRepository(start_customer);
        customerObjectBoundary = new RAMCustomerObjectBoundary();
        viewCustomerInteractor = new ViewCustomerInteractor(customerRepository, customerObjectBoundary);
    }

    @Test
    void viewCustomer() {
        ResponseObject responseObject = viewCustomerInteractor.viewCustomer("10000");
        Customer customer = (Customer) responseObject.getContents();
        assertEquals(start_customer, customer);
    }

    @Test
    void noCustomer() {
        ResponseObject responseObject = viewCustomerInteractor.viewCustomer("20000");
        assertEquals("Could not find a customer with that id.", responseObject.getMessage());
    }
}