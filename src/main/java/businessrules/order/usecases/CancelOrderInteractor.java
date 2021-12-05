package businessrules.order.usecases;

import businessrules.dai.CustomerRepository;
import businessrules.dai.Repository;
import businessrules.order.inputboundaries.CancelOrder;
import businessrules.outputboundaries.CustomerBoundary;
import businessrules.outputboundaries.ObjectBoundary;
import businessrules.outputboundaries.RepositoryBoundary;
import businessrules.outputboundaries.ResponseObject;
import entities.Customer;
import entities.Order;

import java.util.List;

/**
 * Use case for cancelling an order entry of a repository
 */
public class CancelOrderInteractor implements CancelOrder {
    CustomerRepository customerRepository;
    RepositoryBoundary repositoryBoundary;
    Repository<Order> orderRepository;
    CustomerBoundary customerBoundary;
    ObjectBoundary<Order> orderObjectBoundary;

    /**
     * Instantiates a use case for cancelling an order entry
     * @param cR the customer repository
     * @param rB the repository boundary
     * @param oR the order repository
     * @param cB the customer boundary
     * @param oOB the order object boundary
     */
    public CancelOrderInteractor(CustomerRepository cR, RepositoryBoundary rB, Repository<Order> oR,
                                 CustomerBoundary cB, ObjectBoundary<Order> oOB) {
        this.customerRepository = cR;
        this.repositoryBoundary = rB;
        this.orderRepository = oR;
        this.customerBoundary = cB;
        this.orderObjectBoundary = oOB;
    }

    /**
     * Method for setting order status as cancelled
     * @param userToken the user token
     * @param orderId the order id
     * @return a response object
     */
    @Override
    public ResponseObject cancelOrder(String userToken, String orderId) {
        Customer customer = (Customer) customerRepository.getUserFromToken(userToken);

        if(customer == null){
            return repositoryBoundary.queryNotFound("No such customer found.");
        }
        Order order = orderRepository.read(orderId);
        if(order == null){
            return repositoryBoundary.queryNotFound("No such order found.");
        }

        if(!order.getCustomerId().equals(customer.getId())){
            return customerBoundary.unauthorizedAccess("You do not own this order.");
        }

        order.setStatus(Order.Status.CANCELLED);

        List<Order> userOrders = orderRepository.readMultiple("customerId", customer.getId() );
        return orderObjectBoundary.showObjectList(userOrders);
    }
}
