package entities;

import java.util.Date;

/**
 * The type Order.
 */
public class Order {
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
    protected String userId;
    /**
     * The Status.
     */
    protected String status;

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
     * Gets user id.
     *
     * @return the user id
     */
    public String getUserId() {
        return userId;
    }

    /**
     * Sets user id.
     *
     * @param userId the user id
     */
    public void setUserId(String userId) {
        this.userId = userId;
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

    /**
     * The Time placed.
     */
    protected Date timePlaced;
    /**
     * The Time status modified.
     */
    protected Date timeStatusModified;
}
