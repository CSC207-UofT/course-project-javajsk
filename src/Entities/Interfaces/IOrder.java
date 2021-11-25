package Entities.Interfaces;

public interface IOrder {
    ICart getCart();

    void setStatus(String status);

    //TODO: anything else?
}
