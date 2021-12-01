package entities;

/**
 * The type Customer.
 */
public class Customer extends User {
    /**
     * The Order history.
     */
    protected OrderBook orderHistory;
    /**
     * The Current cart.
     */
    protected Cart currentCart;

    /**
     * Instantiates a new Customer.
     *
     * @param orderHistory the order history
     * @param currentCart  the current cart
     */
    public Customer(OrderBook orderHistory, Cart currentCart) {
        this.orderHistory = orderHistory;
        this.currentCart = currentCart;
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
