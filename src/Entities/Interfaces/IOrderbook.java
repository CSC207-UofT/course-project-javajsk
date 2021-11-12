package Entities.Interfaces;

/**
 * The Orderbook Interface
 *
 * This is an abstract interface that indicates all methods an orderbook should implement
 */
public interface IOrderbook {
    /**
     * A method that returns the next order in the orderbook
     * @return next order from orderbook
     */
    public IOrder getNextOrder();

    /**
     * A method that returns the order at given index in orderbook
     * @param index index in orderbook
     * @return order at given index
     */
    public IOrder getOrder(int index);

    /**
     * A method that returns the index of a given order in the orderbook
     * @param order order that we want the index of
     * @return index of given order
     */
    public int getOrderPosition(IOrder order);

    /**
     * A method that returns whether a given order was successfully removed
     * @param order order to be removed
     * @return whether the order was successfully removed
     */
    public boolean removeOrder(IOrder order);

    /**
     * A method that returns whether a given order was successfully added
     * @param order order to be added
     * @return whether the order was successfully added
     */
    public boolean addOrder(IOrder order);
}
