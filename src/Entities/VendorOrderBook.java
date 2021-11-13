package Entities;

import Entities.Interfaces.IOrder;
import Entities.Interfaces.IOrderbook;

import java.util.LinkedList;

public class VendorOrderBook implements IOrderbook {
    /**
     * The VendorOrderBook Class
     *
     * This represents an order book for vendor with specific functionality
     * that a vendor would need.
     * Assumes a vendor would typically work on orders in a first come, first
     * serve basis (this assumption is for the getNextOrder method).
     *
     * orders - list of orders in the book (sorted oldest to newest)
     */
    public LinkedList<IOrder> orders;

    /**
     * Constructs a FifoOrderBook with an arraylist of 0 orders
     */
    public VendorOrderBook(){
        this.orders = new LinkedList<IOrder>();
    }

    /**
     * Constructs a FifoOrderBook with the given arraylist of orders
     * @param listOfOrders orders to be added to FifoOrderBook instance
     */
    public VendorOrderBook(LinkedList<IOrder> listOfOrders){
        this.orders = listOfOrders;
    }

    /**
     * A method that returns the order at given index in orders
     * @param index index in order book
     * @return order at given index
     */
    @Override
    public IOrder getOrder(int index) {
        return this.orders.get(index);
    }

    /**
     * A method that returns the first index of a given order in orders
     * @param order order that we want the index of
     * @return index of given order
     */
    @Override
    public int getOrderPosition(IOrder order) {
        return this.orders.indexOf(order);
    }

    /**
     * A method that returns whether a given order was successfully added
     * @param order order to be added
     * @return whether the order was successfully added
     */
    @Override
    public boolean addOrder(IOrder order) {
        return this.orders.add(order);
    }

    /**
     * A method that returns the next order in the order book (the oldest
     * order added)
     * @return next order from orderbook
     */
    public IOrder getNextOrder() {
        return this.orders.getFirst();
    }

    /**
     * A method that returns whether a given order was successfully removed
     * @param order order to be removed
     * @return whether the order was successfully removed
     */
    public boolean removeOrder(IOrder order) {
        return this.orders.remove(order);
    }

    /**
     * A method that removes every order in the order book.
     */
    public void clearOrderBook(){
        this.orders.clear();
    }
}
