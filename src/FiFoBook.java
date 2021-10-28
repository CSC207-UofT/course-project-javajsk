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

    public FiFoBook(HashMap<String, Orderable> orders, Queue<String> orderQueue) {
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
        if (items == 0) {
            return null;
        }
        String nextId = orderQueue.remove(); //Pops the first element in the Queue
        return orders.get(nextId); //Returns the popped element
    }

    /**
     * Gets the order assiociated with a given order id
     *
     * @param id of order
     */
    @Override
    public Orderable getOrder(String id) {
        return orders.get(id);
    }

    /**
     * Adds an item to an existing order
     *
     * @param item to be added to an order
     */
    @Override
    public void addOrder(Orderable item) {
        items++;
        orders.put(item.getOrderId(), item);
        orderQueue.add(item.getOrderId());

    }

    /**
     * Removes an order
     *
     * @param id of the order to be removed
     * @return true if order was removed
     */
    @Override
    public boolean removeOrder(String id) {
        if (orders.containsKey(id)) {
            orders.remove(id);
            return true;
        }
        return false;
    }
}