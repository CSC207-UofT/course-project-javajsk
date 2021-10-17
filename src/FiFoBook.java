import Interfaces.OrderBook;
import Interfaces.Orderable;
import Interfaces.Sellable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

public class FiFoBook implements OrderBook {
    HashMap<String, Orderable> orders = new HashMap<>();
    Queue<String> orderQueue = new LinkedList<String>();
    int items = 0;
    public FiFoBook( HashMap<String, Orderable> orders, Queue<String> orderQueue){
        this.orders = orders;
        this.orderQueue = orderQueue;
    }

    /**
     * gets the next order in the queue of orders
     *
     * @return the next order in the queue
     */
    @Override
    public Orderable getNextOrder() {
        if(items == 0){
            return null;
        }
        String nextId = orderQueue.remove(); //Pops the first element in the Queue
        return orders.get(nextId); //Returns the popped element
    }

    /**
     * Gets the order assiociated with a given order id
     * @param id of order
     */
    @Override
    public Orderable getOrder(String id) {
        return orders.get(id);
    }
    /** Adds an item to an existing order
     *
     * @param item to be added to an order
     */
    @Override
    public void addOrder(Orderable item) {
        items++;
        orders.put(item.getOrderId(), item);
        orderQueue.add(item.getOrderId());

    }
    /** Removes an order
     *
     * @param id of the order to be removed
     * @return true if order was removed
     */
    @Override
    public boolean removeOrder(String id) {
        if(orders.containsKey(id)){
            orders.remove(id);
            return true;
        }
        return false;
    }
    /** Updates order status to Complete and remove it from the order queue
     *
     * @param id of order
     * @return true when order is competed
     */
    @Override
    public boolean completeOrder(String id) {
        if(orders.containsKey(id)){
            orders.get(id).setOrderStatus(Orderable.COMPLETED);
            return true;
        }
        return false;
    }
    /** Updates the progress of an order
     *
     * @param id of order
     * @param status to update to
     * @return true of order has been updated
     */
    @Override
    public boolean setOrderStatus(String id, int status) {
        if(orders.containsKey(id)){
            orders.get(id).setOrderStatus(status);
            return true;
        }
        return false;
    }
    /** Gets all items in an order
     *
     * @param id of the order
     * @return A list of all items in an order
     */
    @Override
    public ArrayList<Sellable> getOrderItems(String id) {
        if(orders.containsKey(id)) {
            return orders.get(id).getOrderItems();

        }
        return null;
    }
    /** Adds an item to order
     *
     * @param id of order
     * @param item to be added
     * @param quantity of items
     * @return true if item was added
     */
    @Override
    public boolean addItemToOrder(String id, Sellable item, int quantity) {
        if(orders.containsKey(id)) {
            orders.get(id).addItem(item, quantity);
            return true;
        }
        return false;
    }
    /** Changes an order
     *
     * @param id of order
     * @param index of item in an order
     * @param item to be changed
     * @return true if order is changed
     */
    @Override
    public boolean modifyOrder(String id, int index, Sellable item) {
        if(orders.containsKey(id)) {
            return orders.get(id).modifyOrder(index, item);
        }
        return false;
    }
    /** Removes item from order
     *
     * @param id of order
     * @param index of item to be removed
     * @return true if item has been removed
     */
    @Override
    public boolean removeItemFromOrder(String id, int index) {
        if(orders.containsKey(id)) {
            return orders.get(id).removeItem(index);
        }
        return false;
    }

    /**
     *  Gets the status of an order
     * @param id of order
     * @return integer value corresponding to the status of the order
     */
    @Override
    public int getOrderStatus(String id) {
        if(orders.containsKey(id)) {
            return orders.get(id).getOrderStatus();
        }
        return -404;
    }
    /**
     * Gets the total price of an order
     * @param id of order
     * @return the total price of an order
     */
    @Override
    public float getTotalPriceOfOrder(String id) {
        if(orders.containsKey(id)) {
            return orders.get(id).getTotalPrice();
        }
        return -404;
    }
    /**
     * Checks if order is cancelled
     * @param id of order
     * @return true if order is cancelled
     */
    @Override
    public boolean isOrderCancelled(String id) {
        if(orders.containsKey(id)) {
            return orders.get(id).isCancelled();
        }
        System.out.println("NULL POINTER");
        return false;
    }
    /**
     * Sets a discount for a certain order
     * @param id of order
     * @param percentage of discount
     * @return true if discount is applied to an order
     */
    @Override
    public boolean setOrderDiscount(String id, float percentage) {
        if(orders.containsKey(id)) {
            return orders.get(id).setDiscount(percentage);

        }
        return false;
    }
    /**
     * Gets the discount associated with an order
     * @param id of order
     * @return the discount percentage, if any
     */
    @Override
    public float getOrderDiscount(String id) {
        if(orders.containsKey(id)) {
            return orders.get(id).getDiscount();

        }
        return -404;
    }
    /**
     * Changes the time an order should be ready for pickup
     * @param id of order
     * @param time to be changed to
     * @return true if order time has been updated
     */
    @Override
    public boolean updateOrderETA(String id, float time) {
        if(orders.containsKey(id)) {
            return orders.get(id).updateETA(time);

        }
        return false;
    }
}
