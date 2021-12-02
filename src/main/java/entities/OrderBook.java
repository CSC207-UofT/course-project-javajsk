package entities;

import java.util.ArrayList;
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
     * Instantiates a new Order book.
     *
     * @param ordersList the orders list
     */
    public OrderBook(List<Order> ordersList) {
        this.ordersList = ordersList;
    }

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

    public List<String> getOrderIds(){
        List<String> finalList = new ArrayList<>();
        for(Order order: this.ordersList){
            finalList.add(order.getId());
        }
        return finalList;
    }
}
