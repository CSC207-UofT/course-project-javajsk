package Entities;

import Entities.Interfaces.IOrder;
import Entities.Interfaces.IOrderbook;

public class HistoryOrderBook implements IOrderbook {
    @Override
    public IOrder getNextOrder() {
        return null;
    }

    @Override
    public IOrder getOrder(int index) {
        return null;
    }

    @Override
    public int getOrderPosition(IOrder order) {
        return 0;
    }

    @Override
    public boolean removeOrder(IOrder order) {
        return false;
    }

    @Override
    public boolean addOrder(IOrder order) {
        return false;
    }
    // This is a class to store A **USER'S** order history.
    // This is mainly just a simple storage system but is very similar to an orderbook so i think it makes sense to have
    // this as an Iorderbook
}
