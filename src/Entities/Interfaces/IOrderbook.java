package Entities.Interfaces;

/**
 * The Orderbook Interface
 *
 * This is an abstract interface that indicates all methods an order book should implement
 */
public interface IOrderbook {
    /**
     * A method that returns the order at given index in order book
     * @param index index in order book
     * @return order at given index
     */
    IOrder getOrder(int index);

    /**
     * A method that returns the first index of a given order in the order book
     * @param order order that we want the index of
     * @return index of given order
     */
    int getOrderPosition(IOrder order);

    /**
     * A method that returns whether a given order was successfully removed
     * or false if orders cannot be removed from the order book
     * @param order order to be removed
     * @return whether the order was successfully removed
     */
    boolean removeOrder(IOrder order);

    /**
     * A method that returns whether a given order was successfully added
     * @param order order to be added
     * @return whether the order was successfully added
     */
    boolean addOrder(IOrder order);
}
