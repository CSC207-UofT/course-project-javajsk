package Entities.Interfaces;

public interface IOrder {
    ICart getCart();

    void setStatus(String status);

    String getID();

    //TODO: anything else?
}
