package Entities.Interfaces;

public interface ICustomer extends IUser {
    String getId();

    void setCart(ICart cart);

    ICart getCart();


}
