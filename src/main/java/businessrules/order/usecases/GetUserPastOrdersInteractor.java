package businessrules.order.usecases;

import businessrules.dai.CustomerRepository;
import businessrules.dai.Repository;
import businessrules.order.inputboundaries.GetUserPastOrders;
import businessrules.outputboundaries.ObjectBoundary;
import businessrules.outputboundaries.RepositoryBoundary;
import businessrules.outputboundaries.ResponseObject;
import entities.Customer;
import entities.Order;

import java.util.List;

/**
 * Use case for getting a customer's past orders from a repository
 */
public class GetUserPastOrdersInteractor implements GetUserPastOrders {
    /**
     * The Customer repository.
     */
    CustomerRepository customerRepository;
    /**
     * The Repository boundary.
     */
    RepositoryBoundary repositoryBoundary;
    /**
     * The Order repository.
     */
    Repository<Order> orderRepository;
    /**
     * The Order object boundary.
     */
    ObjectBoundary<Order> orderObjectBoundary;

    /**
     * Instantiates a use case for getting a customer's past orders from a repository
     *
     * @param cR  the customer repository
     * @param rB  the repository boundary
     * @param oR  the order repository
     * @param oOB the order object boundart
     */
    public GetUserPastOrdersInteractor(CustomerRepository cR, RepositoryBoundary rB, Repository<Order> oR, ObjectBoundary<Order> oOB) {
        this.customerRepository = cR;
        this.repositoryBoundary = rB;
        this.orderRepository = oR;
        this.orderObjectBoundary = oOB;
    }

    /**
     * Method for getting a customer's past orders
     *
     * @param userToken customer token
     * @return a response object
     */
    @Override
    public ResponseObject getUserPastOrders(String userToken) {
        Customer customer = (Customer) customerRepository.getUserFromToken(userToken);
        if (customer == null) {
            return repositoryBoundary.queryNotFound("No such user found.");
        }

        List<Order> orders = orderRepository.readMultiple("customerId", customer.getId());
        return orderObjectBoundary.showObjectList(orders);
    }
}
