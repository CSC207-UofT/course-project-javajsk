package Entities.Regular;

import Entities.Interfaces.ICustomer;
import Entities.Interfaces.ICart;

import java.util.List;

public class RegularCustomer implements ICustomer {
    String id;
    List<ICart> cartList;

    @Override
    public String getId(){
        return this.id;
    }

    @Override
    public void addCart(ICart cart){
        cartList.add(cart);
    }

}
