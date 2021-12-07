package businessrules.customer.usecases;

import businessrules.customer.inputboundaries.ViewCustomer;
import businessrules.dai.Repository;
import businessrules.outputboundaries.ObjectBoundary;
import businessrules.outputboundaries.ResponseObject;
import entities.Customer;

public class ViewCustomerInteractor implements ViewCustomer {
    Repository<Customer> customerRepository;
    ObjectBoundary<Customer> customerObjectBoundary;

    public ViewCustomerInteractor(Repository<Customer> customerRepository, ObjectBoundary<Customer> shopObjectBoundary) {
        this.customerRepository = customerRepository;
        this.customerObjectBoundary = shopObjectBoundary;
    }

    @Override
    public ResponseObject viewCustomer(String customerId) {
        Customer customer = customerRepository.read(customerId);
        return customerObjectBoundary.showObject(customer);

    }
}
