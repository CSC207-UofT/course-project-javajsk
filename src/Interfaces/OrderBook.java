package Interfaces;

import java.util.ArrayList;

public interface OrderBook {
    Orderable getNextOrder();

    Orderable getOrder(String id);


    void addOrder(Orderable item); //Adds an order to the OrderQueue


    boolean removeOrder(String id); //Removes an order from the Queue

    boolean completeOrder(String id); //Updates an order to let the user know it is ready for pickup

    boolean setOrderStatus(String id, int status); //Updates the progress of the food item in the Queue

    ArrayList<Sellable> getOrderItems(String id); //Returns a list of items in a particular order

    boolean addItemToOrder(String id, Sellable item, int quantity); //Adds a menu item to an order

    boolean modifyOrder(String id, int index, Sellable item); //Changes an order

    boolean removeItemFromOrder(String id, int index); //Removes a menu item from an order

    int getOrderStatus(String id); //Returns the current status of an order

    float getTotalPriceOfOrder(String id); //Returns the cumulative price of all items in an order

    boolean isOrderCancelled(String id); //Returns True if an order is cancelled or not

    boolean setOrderDiscount(String id, float percentage); //Sets a specified discount on a specific order

    float getOrderDiscount(String id); //Returns the discount amount on an order, if any at all

    boolean updateOrderETA(String id, float time); //Updates the time remaining for an order to be completed
}
