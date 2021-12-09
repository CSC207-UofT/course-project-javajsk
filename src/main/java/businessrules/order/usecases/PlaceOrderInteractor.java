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

/**
 * Use case for placing an order in the repository
 */
public class PlaceOrderInteractor implements PlaceOrder {
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
     * The Cart repository.
     */
    Repository<Cart> cartRepository;
    /**
     * The Customer boundary.
     */
    CustomerBoundary customerBoundary;
    /**
     * The Order object boundary.
     */
    ObjectBoundary<Order> orderObjectBoundary;

    /**
     * Instantiates a use case for placing an order in the repository
     *
     * @param cusR the customer repository
     * @param rB   the repository boundary
     * @param oR   the order repository
     * @param cR   the cart repository
     * @param cB   the customer boundary
     * @param oOB  the order object boundary
     */
    public PlaceOrderInteractor(CustomerRepository cusR, RepositoryBoundary rB, Repository<Order> oR, Repository<Cart> cR, CustomerBoundary cB, ObjectBoundary<Order> oOB) {
        this.customerRepository = cusR;
        this.repositoryBoundary = rB;
        this.orderRepository = oR;
        this.cartRepository = cR;
        this.customerBoundary = cB;
        this.orderObjectBoundary = oOB;
    }

    /**
     * Method for placing an order
     *
     * @param userToken the customer token
     * @return a response object
     */
    @Override
    public ResponseObject placeOrder(String userToken) {
        Customer customer = (Customer) customerRepository.getUserFromToken(userToken);
        if (customer == null) {
            return repositoryBoundary.queryNotFound("No such user found.");
        }

        if (customer.getCurrentCart().isEmpty()) {
            return customerBoundary.error("Cart is empty.");
        }

        String cartId = cartRepository.create(customer.getCurrentCart());
        if (cartId == null) {
            return repositoryBoundary.creationFailed("Failed to create cart in database");
        }
        customer.getCurrentCart().setId(cartId);
        Cart cart = customer.getCurrentCart();
        Date currentTime = new Date();
        Order order = new Order("N/A", cart, cart.getShopId(), customer.getId(), Order.Status.PLACED, currentTime,
                currentTime);

        String orderId = orderRepository.create(order);
        if (orderId == null) {
            repositoryBoundary.creationFailed("Failed to create order in database.");
        }

        order.setId(orderId);

        return orderObjectBoundary.showObject(order);
    }
}
