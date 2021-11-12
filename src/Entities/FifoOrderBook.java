package Entities;

import Entities.Interfaces.IOrder;
import Entities.Interfaces.IOrderbook;

public class FifoOrderBook implements IOrderbook {
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
}
