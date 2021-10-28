package Interfaces;

import java.util.ArrayList;

public interface OrderBook {
    Orderable getNextOrder();

    Orderable getOrder(String id);

    /** Adds an item to an existing order
     *
     * @param item to be added to an order
     */
    void addOrder(Orderable item);

    /** Removes an order
     *
     * @param id of the order to be removed
     * @return true if order was removed
     */
    boolean removeOrder(String id);
}

