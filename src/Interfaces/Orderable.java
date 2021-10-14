package Interfaces;

import java.util.ArrayList;

public interface Orderable{
    final int CANCELLED = -1;
    final int RECEIVED = 0;
    final int PREPARING = 1;
    final int COMPLETED = 2;


    ArrayList<Sellable> getOrderItems();

    void addItem(Sellable item, int q);

    boolean modifyOrder(int index, Sellable item);

    boolean removeItem(int index);

    void setOrderStatus(int status);

    int getOrderStatus();

    float getTotalPrice();

    boolean isCancelled();

    boolean setDiscount(float percentage);

    float getDiscount();

    boolean updateETA(float time);
}
