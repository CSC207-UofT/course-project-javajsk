package Entities.Interfaces;

public interface IOrder {
    ICart getCart();

    void setStatus(boolean status);

    //TODO: anything else?
}
