package Entities.Regular;

import Entities.Interfaces.ICustomer;
import Entities.Interfaces.ICart;

public class RegularCustomer implements ICustomer {
    String ID;
    ICart currentCart;

    public RegularCustomer(String ID,  ICart cartList){
        this.ID = ID;
        this.currentCart = cartList;
    }
    @Override
    public String getId(){
        return this.ID;
    }

    @Override
    public void setCart(ICart cart){
        currentCart = cart;
    }

    @Override
    public ICart getCart() {
        return currentCart;
    }

    @Override
    public String getID() {
        return this.getId();
    }
}
