package Entities.Regular;

import Entities.Interfaces.ICustomer;
import Entities.Interfaces.ICart;

public class RegularCustomer implements ICustomer {
    String id;
    ICart currentCart;

    /**
     * Creates a RegularCustomer object
     * @param id the id of the customer
     * @param cartList the cart of the customer
     */
    public RegularCustomer(String id,  ICart cartList){
        this.id = id;
        this.currentCart = cartList;
    }
    /**
     * Returns the id of a customer
     * @return the id of a customer
     */
    @Override
    public String getId(){
        return this.id;
    }

    /**
     * Method for setting the customer id
     * @param id the id
     */
    @Override
    public void setId(String id) {
        this.id = id;
    }

    /**
     * A method for setting the cart of a customer
     * @param cart a cart object
     */
    @Override
    public void setCart(ICart cart){
        currentCart = cart;
    }

    /**
     * A method for getting the cart of a customer
     * @return the cart object of a customer
     */
    @Override
    public ICart getCart() {
        return currentCart;
    }

}
