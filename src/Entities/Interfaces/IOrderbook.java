package Entities.Interfaces;

public interface IOrderbook {
    public IOrder getNextOrder();

    public IOrder getOrder(int index);

    public int getOrderPosition(IOrder order);

    public boolean removeOrder(IOrder order);

    public boolean addOrder(IOrder order);
}
