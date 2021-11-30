package entities;

import java.util.List;

/**
 * The type Order book.
 */
public class OrderBook {
    /**
     * The Orders list.
     */
    protected List<Order> ordersList;

    /**
     * Gets orders list.
     *
     * @return the orders list
     */
    public List<Order> getOrdersList() {
        return ordersList;
    }

    /**
     * Sets orders list.
     *
     * @param ordersList the orders list
     */
    public void setOrdersList(List<Order> ordersList) {
        this.ordersList = ordersList;
    }
}
