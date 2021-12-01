package entities;

/**
 * The type Customer.
 */
public class Customer extends User {

    protected OrderBook orderHistory;
    protected Cart currentCart;

    /**
     * Instantiates and new customer
     *
     * @param id id of customer
     * @param user username of customer
     * @param password hashed password
     * @param orderHist orderbook of all customer's previous orders
     * @param cart current cart of customer
     */
    public Customer(String id, String user, String password, OrderBook orderHist, Cart cart) {
        super(id, user, password);
        this.orderHistory = orderHist;
        this.currentCart = cart;
    }

    /**
     * Gets order history.
     *
     * @return the order history
     */
    public OrderBook getOrderHistory() {
        return orderHistory;
    }

    /**
     * Sets order history.
     *
     * @param orderHistory the order history
     */
    public void setOrderHistory(OrderBook orderHistory) {
        this.orderHistory = orderHistory;
    }

    /**
     * Gets current cart.
     *
     * @return the current cart
     */
    public Cart getCurrentCart() {
        return currentCart;
    }

    /**
     * Sets current cart.
     *
     * @param currentCart the current cart
     */
    public void setCurrentCart(Cart currentCart) {
        this.currentCart = currentCart;
    }
}
