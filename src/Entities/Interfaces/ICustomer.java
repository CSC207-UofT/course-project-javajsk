package Entities.Interfaces;

public interface ICustomer extends IUser {
    String getId();

    void addCart(ICart cart);

    boolean hasCart(ICart cart);


}
