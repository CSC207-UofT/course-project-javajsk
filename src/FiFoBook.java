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
    @Override
    public Orderable getNextOrder() {
        if(items == 0){ //Returns null if order queue is empty
            return null;
        }
        String nextId = orderQueue.remove(); //Pops the first element in the Queue
        return orders.get(nextId); //Returns the popped element
    }

    @Override
    public Orderable getOrder(String id) {
        return orders.get(id); // Returns the order details, given the order ID
    }

    @Override
    public void addOrder(Orderable item) {
        items++;
        orders.put(item.getOrderId(), item); //Adds an item to order hashmap
        orderQueue.add(item.getOrderId()); //Adds the order to the orderQueue

    }

    @Override
    public boolean removeOrder(String id) {
        if(orders.containsKey(id)){
            orders.remove(id);
            return true; //Returns true once the order is removed from the list of orders
        }
        return false; //Returns false if the order is not in the list of orders
    }

    @Override
    public boolean completeOrder(String id) {
        if(orders.containsKey(id)){ //Checks if the order is in the current list of orders
            orders.get(id).setOrderStatus(Orderable.COMPLETED); //Sets order status to "Completed"
            return true;
        }
        return false;
    }

    @Override
    public boolean setOrderStatus(String id, int status) {
        if(orders.containsKey(id)){
            orders.get(id).setOrderStatus(status); //Sets the status, provided by the user, of an order
            return true;
        }
        return false;
    }

    @Override
    public ArrayList<Sellable> getOrderItems(String id) {
        if(orders.containsKey(id)) {
            return orders.get(id).getOrderItems(); // Returns a list of items in a specific order.
                                                    // This order is found using an ID provided by the user
        }
        return null;
    }

    @Override
    public boolean addItemToOrder(String id, Sellable item, int quantity) {
        if(orders.containsKey(id)) {
            orders.get(id).addItem(item, quantity); //Adds items to  a specific order
            return true;                            //You can add items and the quantity of items to an already existing order
        }
        return false;
    }

    @Override
    public boolean modifyOrder(String id, int index, Sellable item) {
        if(orders.containsKey(id)) {
            return orders.get(id).modifyOrder(index, item); //Returns a boolean value to see if an order has been modified
        }
        return false;
    }

    @Override
    public boolean removeItemFromOrder(String id, int index) {
        if(orders.containsKey(id)) {
            return orders.get(id).removeItem(index); //Removes an item from a given index
        }
        return false;
    }


    @Override
    public int getOrderStatus(String id) {
        if(orders.containsKey(id)) {
            return orders.get(id).getOrderStatus(); //Returns the Status of a specific order
        }
        return -404; //Returns 404 if ID is not present in the list of orders
    }

    @Override
    public float getTotalPriceOfOrder(String id) {
        if(orders.containsKey(id)) {
            return orders.get(id).getTotalPrice(); //Returns total price of all items in an order
        }
        return -404;
    }

    @Override
    public boolean isOrderCancelled(String id) {
        if(orders.containsKey(id)) {
            return orders.get(id).isCancelled(); //Returns true if order has been previously cancelled
        }
        System.out.println("NULL POINTER");
        return false;
    }

    @Override
    public boolean setOrderDiscount(String id, float percentage) {
        if(orders.containsKey(id)) {
            return orders.get(id).setDiscount(percentage); //Sets a specified percentage discount to a specific order

        }
        return false;
    }

    @Override
    public float getOrderDiscount(String id) {
        if(orders.containsKey(id)) {
            return orders.get(id).getDiscount(); //Gets the discount percentage of an order, if there is any

        }
        return -404;
    }

    @Override
    public boolean updateOrderETA(String id, float time) {
        if(orders.containsKey(id)) {
            return orders.get(id).updateETA(time); //Updates the estimated time that an order would be completed

        }
        return false;
    }
}
