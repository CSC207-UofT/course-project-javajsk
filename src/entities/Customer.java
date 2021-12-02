package entities;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * The type Customer.
 */

public class Customer extends User implements JSONable{
    /**
     * The Order history.
     */
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

    public Customer(String id, String username, String hashedPassword, OrderBook orderHistory, Cart currentCart) {
        super(id, username, hashedPassword);
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

    @Override
    public JSONObject jsonify() {
        JSONObject finalValue = new JSONObject();
        finalValue.put("username", super.userName);
        finalValue.put("password", super.hashedPassword);
        finalValue.put("cart", currentCart.jsonify());
        finalValue.put("orderHistory", new JSONArray(this.orderHistory.getOrderIds()));
        return finalValue;
    }
}
