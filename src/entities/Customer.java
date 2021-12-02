package entities;

import org.json.JSONObject;

/**
 * The type Customer.
 */

public class Customer extends User {
    /**
     * The Order history.
     */
    protected OrderBook orderHistory;
    protected Cart currentCart;

    public Customer(String id,String username, String password, OrderBook orderHistory, Cart currentCart) {
        super(id, username, password);
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
    public JSONObject jsonify(){
        JSONObject userData = new JSONObject();
        userData.put("id", this.id);
        userData.put("userName", this.userName);
        userData.put("hashedPassword", this.hashedPassword);
        userData.put("currentCart", this.currentCart.jsonify());

        return userData;
    }
}
