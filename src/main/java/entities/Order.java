package entities;

import org.json.JSONObject;

import java.util.Date;

/**
 * The order entity
 */
public class Order {
    public enum Status {IN_PROGRESS, PLACED, COMPLETED, CANCELLED}

    /**
     * The order id.
     */
    public String id;
    /**
     * The Cart.
     */
    protected Cart cart;
    /**
     * The id of the shop the order is for
     */
    protected String shopId;
    /**
     * The id of the user who placed the order
     */
    protected String customerId;
    /**
     * The order status.
     */
    protected Status status;

    /**
     * The time the order is placed.
     */
    protected Date timePlaced;
    /**
     * The time the order status is modified.
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
    public Order(String id, Cart cart, String shopId, String customerId, Status status,
                 Date timePlaced, Date timeStatusModified) {
        this.id = id;
        this.cart = cart;
        this.shopId = shopId;
        this.customerId = customerId;
        this.status = status;
        this.timePlaced = timePlaced;
        this.timeStatusModified = timeStatusModified;
    }

    /**
     * Gets order id.
     *
     * @return the id
     */
    public String getId() {
        return id;
    }

    /**
     * Sets order id.
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
    public Status getStatus() {
        return status;
    }

    /**
     * Sets status.
     *
     * @param status the status
     */
    public void setStatus(Status status) {
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

    @Override
    public String toString() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("id", this.id);
        jsonObject.put("cartId", this.cart.id);
        jsonObject.put("shopId", this.shopId);
        jsonObject.put("customerId", this.customerId);
        jsonObject.put("status", this.status);

        // TODO: FIX THIS DATE SHIT.
        jsonObject.put("timePlaced", this.timePlaced);
        jsonObject.put("timeStatusModified", this.timeStatusModified);
        return jsonObject.toString();
    }

}
