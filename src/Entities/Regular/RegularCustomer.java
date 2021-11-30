package Entities.Regular;

import Entities.Interfaces.ICustomer;
import Entities.Interfaces.ICart;

public class RegularCustomer implements ICustomer {
    String id;
    ICart currentCart;

    public RegularCustomer(String newId, ICart cartList){
        this.id = newId;
        this.currentCart = cartList;
    }

    @Override
    public String getId(){
        return this.id;
    }

    @Override
    public void setCart(ICart cart){
        currentCart = cart;
    }

    @Override
    public ICart getCart() {
        return currentCart;
    }
}
