package Entities.Interfaces;

import Entities.Interfaces.ICart;

public interface ICustomer {
    String getId();

    void addCart(ICart cart);

    boolean hasCart(ICart cart);


}
