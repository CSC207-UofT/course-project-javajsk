package entities;

import java.util.ArrayList;
import java.util.List;

/**
 * The Order book entity class
 */
public class OrderBook {
    /**
     * The Orders list.
     */
    protected List<Order> ordersList;

    /**
     * Instantiates a new Order book.
     *
     * @param ordersList the orders list
     */
    public OrderBook(List<Order> ordersList) {
        this.ordersList = ordersList;
    }

    /**
     * Instantiates an empty order book
     */
    public OrderBook() {
        this.ordersList = new ArrayList<>();
    }

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

    /**
     * Method for getting order ids
     *
     * @return a list of order ids
     */
    public List<String> getOrderIds() {
        List<String> finalList = new ArrayList<>();
        for (Order order : this.ordersList) {
            finalList.add(order.getId());
        }
        return finalList;
    }

    /**
     * Method for getting orders with in progress statuses
     *
     * @return a list of in progress orders
     */
    public List<Order> getIncompleteOrders() {
        List<Order> incomplete = new ArrayList<>();
        for (Order order : this.ordersList) {
            if (order.status.equals(Order.Status.IN_PROGRESS) || order.status.equals(Order.Status.PLACED)) {
                incomplete.add(order);
            }
        }
        return incomplete;
    }

    /**
     * Method for getting the next incomplete order
     *
     * @return the next order
     */
    public Order getNextOrder() {
        if (this.ordersList.size() == 0) {
            return null;
        }
        List<Order> incompleteOrders = this.getIncompleteOrders();
        return incompleteOrders.get(0);
    }
}
