package entities;

import org.json.JSONObject;

/**
 * The Customer entity class
 */
public class Customer extends User {
    /**
     * The Order history.
     */
    protected OrderBook orderHistory;
    /**
     * The current cart.
     */
    protected Cart currentCart;


    /**
     * Instantiates a new Customer.
     *
     * @param id           the id
     * @param username     the username
     * @param password     the password
     * @param orderHistory the order history
     * @param currentCart  the current cart
     */
    public Customer(String id,String username, String password, OrderBook orderHistory, Cart currentCart) {
        super(id, username, password);
        this.orderHistory = orderHistory;
        this.currentCart = currentCart;
    }

    /**
     * Instantiates a new Customer.
     *
     * @param id       the id
     * @param username the username
     * @param password the password
     */
    public Customer(String id,String username, String password) {
        super(id, username, password);
        this.orderHistory = new OrderBook();
        this.currentCart = new Cart();
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

    /**
     * Empty cart.
     */
    public void emptyCart(){
        this.currentCart.empty();
    }

    /**
     * Method returns customer as a string representation
     *
     * @return string representation of customer
     */
    @Override
    public String toString(){
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("cart", new JSONObject(this.currentCart.toString()));

        return jsonObject.toString();
    }

}
