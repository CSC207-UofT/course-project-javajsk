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

public class GetUserPastOrdersInteractor implements GetUserPastOrders {
    CustomerRepository customerRepository;
    RepositoryBoundary repositoryBoundary;
    Repository<Order> orderRepository;
    ObjectBoundary<Order> orderObjectBoundary;

    @Override
    public ResponseObject getUserPastOrders(String userToken) {
        Customer customer = (Customer) customerRepository.getUserFromToken(userToken);
        if(customer == null){
            return repositoryBoundary.queryNotFound("No such user found.");
        }

        List<Order> orders = orderRepository.readMultiple("customerId", customer.getId());
        return orderObjectBoundary.showObjectList(orders);
    }
}
