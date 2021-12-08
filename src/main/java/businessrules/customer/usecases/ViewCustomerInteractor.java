package businessrules.customer.usecases;

import businessrules.customer.inputboundaries.ViewCustomer;
import businessrules.dai.Repository;
import businessrules.outputboundaries.ObjectBoundary;
import businessrules.outputboundaries.ResponseObject;
import entities.Customer;

/**
 * View customer use case
 */
public class ViewCustomerInteractor implements ViewCustomer {
    /**
     * The Customer repository.
     */
    Repository<Customer> customerRepository;
    /**
     * The Customer object boundary.
     */
    ObjectBoundary<Customer> customerObjectBoundary;

    /**
     * Instantiates a new View customer interactor.
     *
     * @param customerRepository the customer repository
     * @param customerObjectBoundary the shop object boundary
     */
    public ViewCustomerInteractor(Repository<Customer> customerRepository, ObjectBoundary<Customer> customerObjectBoundary) {

        this.customerRepository = customerRepository;
        this.customerObjectBoundary = customerObjectBoundary;
    }

    /**
     * Method that returns object containing customer information with given id to display
     * @param customerId id of customer to display
     * @return response object containing customer or error message to display
     */
    @Override
    public ResponseObject viewCustomer(String customerId) {
        Customer customer = customerRepository.read(customerId);
        if(customer == null){
            return customerObjectBoundary.invalidObject("Could not find a customer with that id.");
        }
        return customerObjectBoundary.showObject(customer);
    }
}
