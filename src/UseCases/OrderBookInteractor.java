package UseCases;

import Entities.HistoryOrderBook;
import Entities.Interfaces.IOrder;
import Entities.Interfaces.IOrderbook;
import Entities.VendorOrderBook;

/**
 * The OrderBookInteractor class
 *
 * Responsible for creating, reading, updating, and deleting OrderBook data and object instances.
 */
public class OrderBookInteractor {
    protected IOrderbook orderBook;

    /**
     * Constructor creates an empty order book of given type
     * @param type the type of order book to be created
     */
    public OrderBookInteractor(String type){
        if (type.equals("Vendor")){
            this.orderBook = new VendorOrderBook();
        } else if (type.equals("History")){
            this.orderBook = new HistoryOrderBook();
        }
    }

    /**
     * A method that adds a given order to the order book
     * @param order order to be added
     */
    public void addToOrderBook(IOrder order){
        this.orderBook.addOrder(order);
    }

    /**
     * A method that removes a given order from the order book and returns
     * whether the order was successfully removed.
     *
     * @param order order to be removed
     * @return whether order was successfully removed
     */
    public boolean removeFromOrderBook(IOrder order){
        return this.orderBook.removeOrder(order);
    }


}
