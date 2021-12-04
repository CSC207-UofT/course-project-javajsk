package businessrules.order.usecases;

import businessrules.dai.CustomerRepository;
import businessrules.dai.Repository;
import businessrules.order.inputboundaries.CancelOrder;
import businessrules.outputboundaries.CustomerBoundary;
import businessrules.outputboundaries.ObjectBoundary;
import businessrules.outputboundaries.RepositoryBoundary;
import businessrules.outputboundaries.ResponseObject;
import EntitiesUnitTest.Customer;
import EntitiesUnitTest.Order;

import java.util.List;

public class CancelOrderInteractor implements CancelOrder {
    CustomerRepository customerRepository;
    RepositoryBoundary repositoryBoundary;
    Repository<Order> orderRepository;
    CustomerBoundary customerBoundary;
    ObjectBoundary<Order> orderObjectBoundary;


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
