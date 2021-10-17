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
     *
     * Function pops the first element in the queue of orders
     * @return the popped element from the queue or null if order queue is empty
     */
    @Override
    public Orderable getNextOrder() {
        if(items == 0){
            return null;
        }
        String nextId = orderQueue.remove();
        return orders.get(nextId);
    }

    /**
     * Gets the order corresponding to a given order ID
     * @param id  of the order
     *
     */
    @Override
    public Orderable getOrder(String id) {
        return orders.get(id);

    }

    /** Creates a new order and adds to the order queue
     * @param item is the product the user wishes to order
     *
     */
    @Override
    public void addOrder(Orderable item) {
        items++;
        orders.put(item.getOrderId(), item);
        orderQueue.add(item.getOrderId());

    }
    /** Removes an order that was already placed
     *
     * @param id is the id of the order that the user wishes to remove
     * @return   Returns true if an order is successfully removed from the queue of orders, false otherwise
     */
    @Override
    public boolean removeOrder(String id) {
        if(orders.containsKey(id)){
            orders.remove(id);
            return true;
        }
        return false;

    }

    /** Sets an order status to COMPLETED
     *
     * @param id is the id of the order the vendor wishes to update
     * @return True if the status was successfully updated
     */
    @Override
    public boolean completeOrder(String id) {
        if(orders.containsKey(id)){
            orders.get(id).setOrderStatus(Orderable.COMPLETED);
            return true;
        }
        return false;

    }

    /** Updates the status of an ongoing order
     *
     * @param id of the order
     * @param status that you want to set
     * @return true if status was successfully set
     */
    @Override
    public boolean setOrderStatus(String id, int status) {
        if(orders.containsKey(id)){
            orders.get(id).setOrderStatus(status);
            return true;
        }
        return false;

    }

    /** Gets all items from a  given order
     *
     * @param id of the order
     * @return A list of all the items in a specific order
     */
    @Override
    public ArrayList<Sellable> getOrderItems(String id) {
        if(orders.containsKey(id)) {
            return orders.get(id).getOrderItems();
        }
        return null;
    }

    /** Method that adds a certain amount of items to an order
     *
     * @param id of the order
     * @param item that you wish to add to the order
     * @param quantity of items that you wish to add
     */
    @Override
    public boolean addItemToOrder(String id, Sellable item, int quantity) {
        if(orders.containsKey(id)) {
            orders.get(id).addItem(item, quantity);
            return true;
        }
        return false;
    }

    /** Method that modifies an order
     *
     * @param id of the order
     * @param index of the specific item in an order the user wishes to modify
     * @param item that needs to be modified
     * @return true if order had been modified
     */
    @Override
    public boolean modifyOrder(String id, int index, Sellable item) {
        if(orders.containsKey(id)) {
            return orders.get(id).modifyOrder(index, item);
        }
        return false;
    }

    /** Method that removes an item from an existing order
     *
     * @param id of the order
     * @param index of the item that needs to be removed
     * @return true if item has been removed
     */
    @Override
    public boolean removeItemFromOrder(String id, int index) {
        if(orders.containsKey(id)) {
            return orders.get(id).removeItem(index);
        }
        return false;
    }

    /** Getter function that gets the status of an order
     *
     * @param id of the order
     * @return the integer value that corresponds to an order status
     */
    @Override
    public int getOrderStatus(String id) {
        if(orders.containsKey(id)) {
            return orders.get(id).getOrderStatus();
        }
        return -404;
    }

    /** Method that sums the total price of all items in an order
     *
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

    /** Method that checks to see if an order is cancelled
     *
     * @param id of the order
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

    /** Method that sets a discount to an order
     *
     * @param id of the order
     * @param percentage of discount
     * @return true if discount is successfully set to an order
     */
    @Override
    public boolean setOrderDiscount(String id, float percentage) {
        if(orders.containsKey(id)) {
            return orders.get(id).setDiscount(percentage);

        }
        return false;
    }

    /** Method that gets the discount, if any, applied to an order
     *
     * @param id of the order
     * @return the discount associated with an order
     */
    @Override
    public float getOrderDiscount(String id) {
        if(orders.containsKey(id)) {
            return orders.get(id).getDiscount();

        }
        return -404;
    }

    /** Method that updates the estimated time for an order to be completed
     *
     * @param id of the order
     * @param time estimated for an order to be compelted
     * @return true if ETA has been set
     */
    @Override
    public boolean updateOrderETA(String id, float time) {
        if(orders.containsKey(id)) {
            return orders.get(id).updateETA(time);

        }
        return false;
    }
}
