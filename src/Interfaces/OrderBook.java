package Interfaces;

import java.util.ArrayList;

public interface OrderBook {
    Orderable getNextOrder();

    Orderable getOrder(String id);


    void addOrder(Orderable item);


    boolean removeOrder(String id);

    boolean completeOrder(String id);

    boolean setOrderStatus(String id, int status);

    ArrayList<Sellable> getOrderItems(String id);

    boolean addItemToOrder(String id, Sellable item, int quantity);

    boolean modifyOrder(String id, int index, Sellable item);

    boolean removeItemFromOrder(String id, int index);

    int getOrderStatus(String id);

    float getTotalPriceOfOrder(String id);

    boolean isOrderCancelled(String id);

    boolean setOrderDiscount(String id, float percentage);

    float getOrderDiscount(String id);

    boolean updateOrderETA(String id, float time);
}
