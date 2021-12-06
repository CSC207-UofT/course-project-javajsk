package entities;

import org.json.JSONObject;

import javax.print.attribute.standard.JobStateReasons;

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

    public void emptyCart(){
        this.currentCart.empty();
    }
    @Override
    public String toString(){
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("cart", new JSONObject(this.currentCart.toString()));

        return jsonObject.toString();
    }

}
