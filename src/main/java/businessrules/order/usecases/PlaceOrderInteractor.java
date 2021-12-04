package businessrules.order.usecases;

import businessrules.dai.CustomerRepository;
import businessrules.dai.Repository;
import businessrules.order.inputboundaries.PlaceOrder;
import businessrules.outputboundaries.CustomerBoundary;
import businessrules.outputboundaries.ObjectBoundary;
import businessrules.outputboundaries.RepositoryBoundary;
import businessrules.outputboundaries.ResponseObject;
import entities.Cart;
import entities.Customer;
import entities.Order;

import java.util.Date;

public class PlaceOrderInteractor implements PlaceOrder {
    CustomerRepository customerRepository;
    RepositoryBoundary repositoryBoundary;
    Repository<Order> orderRepository;
    Repository<Cart> cartRepository;
    CustomerBoundary customerBoundary;
    ObjectBoundary<Order> orderObjectBoundary;

    @Override
    public ResponseObject placeOrder(String userToken) {
        Customer customer = (Customer) customerRepository.getUserFromToken(userToken);
        if(customer == null){
            return repositoryBoundary.queryNotFound("No such user found.");
        }

        if(customer.getCurrentCart().isEmpty()){
            return customerBoundary.error("Cart is empty.");
        }

        String cartId = cartRepository.create(customer.getCurrentCart());
        if(cartId == null){
            return repositoryBoundary.creationFailed("Failed to create cart in database");
        }
        customer.getCurrentCart().setId(cartId);
        Cart cart = customer.getCurrentCart();
        Date currentTime = new Date();
        Order order = new Order("N/A", cart, cart.getShopId(), customer.getId(), Order.Status.PLACED, currentTime,
                currentTime);

        String orderId = orderRepository.create(order);
        if(orderId == null){
            repositoryBoundary.creationFailed("Failed to create order in database.");
        }

        order.setId(orderId);

        return orderObjectBoundary.showObject(order);
    }
}
