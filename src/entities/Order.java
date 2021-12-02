package entities;

import org.json.JSONObject;

import java.util.Date;

/**
 * The type Order.
 */
public class Order implements JSONable{
    /**
     * The Id.
     */
    public String id;
    /**
     * The Cart.
     */
    protected Cart cart;
    /**
     * The Shop id.
     */
    protected String shopId;
    /**
     * The User id.
     */
    protected String customerId;
    /**
     * The Status.
     */
    protected String status;

    /**
     * The Time placed.
     */
    protected Date timePlaced;
    /**
     * The Time status modified.
     */
    protected Date timeStatusModified;

    /**
     * Instantiates a new Order.
     *
     * @param id                 the id
     * @param cart               the cart
     * @param shopId             the shop id
     * @param customerId         the customer id
     * @param status             the status
     * @param timePlaced         the time placed
     * @param timeStatusModified the time status modified
     */
    public Order(String id, Cart cart, String shopId, String customerId, String status, Date timePlaced, Date timeStatusModified) {
        this.id = id;
        this.cart = cart;
        this.shopId = shopId;
        this.customerId = customerId;
        this.status = status;
        this.timePlaced = timePlaced;
        this.timeStatusModified = timeStatusModified;
    }

    /**
     * Gets id.
     *
     * @return the id
     */
    public String getId() {
        return id;
    }

    /**
     * Sets id.
     *
     * @param id the id
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Gets cart.
     *
     * @return the cart
     */
    public Cart getCart() {
        return cart;
    }

    /**
     * Sets cart.
     *
     * @param cart the cart
     */
    public void setCart(Cart cart) {
        this.cart = cart;
    }

    /**
     * Gets shop id.
     *
     * @return the shop id
     */
    public String getShopId() {
        return shopId;
    }

    /**
     * Sets shop id.
     *
     * @param shopId the shop id
     */
    public void setShopId(String shopId) {
        this.shopId = shopId;
    }

    /**
     * Gets customer id.
     *
     * @return the customer id
     */
    public String getCustomerId() {
        return customerId;
    }

    /**
     * Sets customer id.
     *
     * @param customerId the customer id
     */
    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    /**
     * Gets status.
     *
     * @return the status
     */
    public String getStatus() {
        return status;
    }

    /**
     * Sets status.
     *
     * @param status the status
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * Gets time placed.
     *
     * @return the time placed
     */
    public Date getTimePlaced() {
        return timePlaced;
    }

    /**
     * Sets time placed.
     *
     * @param timePlaced the time placed
     */
    public void setTimePlaced(Date timePlaced) {
        this.timePlaced = timePlaced;
    }

    /**
     * Gets time status modified.
     *
     * @return the time status modified
     */
    public Date getTimeStatusModified() {
        return timeStatusModified;
    }

    /**
     * Sets time status modified.
     *
     * @param timeStatusModified the time status modified
     */
    public void setTimeStatusModified(Date timeStatusModified) {
        this.timeStatusModified = timeStatusModified;
    }


    public JSONObject jsonify(){
        JSONObject final_data = new JSONObject();
        final_data.put("id", this.id);
        final_data.put("cart", this.cart.jsonify());
        final_data.put("status", this.status);
        final_data.put("shopId", this.shopId);
        final_data.put("customerId", this.customerId);
        final_data.put("timePlaced", this.timePlaced);
        final_data.put("timeStatusModified", this.timeStatusModified);
        return final_data;

    }
}
