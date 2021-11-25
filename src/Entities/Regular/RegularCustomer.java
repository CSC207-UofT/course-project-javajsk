package Entities.Regular;

import Entities.Interfaces.ICustomer;
import Entities.Interfaces.ICart;

import java.util.List;

public class RegularCustomer implements ICustomer {
    String ID;
    List<ICart> cartList;
    public RegularCustomer(String ID,  List<ICart> cartList){
        this.ID = ID;
        this.cartList = cartList;
    }
    @Override
    public String getId(){
        return this.ID;
    }

    @Override
    public void addCart(ICart cart){
        cartList.add(cart);
    }

    @Override
    public boolean hasCart(ICart cart) {
        return false;
    }

}
