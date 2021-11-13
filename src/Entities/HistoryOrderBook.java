package Entities;

import Entities.Interfaces.IOrder;
import Entities.Interfaces.IOrderbook;

import java.util.LinkedList;

public class HistoryOrderBook implements IOrderbook {
    /**
     * The HistoryOrderBook class.
     *
     * This class represents a list of previous orders made by a user
     * (i.e. a list of order history for one user).
     *
     * orders -  list of orders the user has previously made (sorted from oldest to most recent)
     */
    public LinkedList<IOrder> orders;

    /**
     * Class constructor with no parameters - default construction of class
     * is an empty linked list
     */
    public HistoryOrderBook(){
        this.orders = new LinkedList<IOrder>();
    }

    /**
     * Class contruction with one parameter - creates an instance of
     * HistoryOrderBook with a linked list orderHistory
     * @param orderHistory the linked list of previous orders
     */
    public HistoryOrderBook(LinkedList<IOrder> orderHistory){
        this.orders = orderHistory;
    }

    /**
     * A method that returns the order at given index in order book
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
}
