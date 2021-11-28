package Entities.Regular;

import Entities.Interfaces.ICart;
import Entities.Interfaces.IOrder;

public class RegularOrder implements IOrder {
    ICart cart;

    String ID;
    String status;
    /**
     * Construct an instance of a RegularOrder, a cart with the
     *
     * @param cart The cart object for the order
     */
    public RegularOrder(ICart cart, String ID){
        this.cart = cart;
        this.ID = ID;
    }
    /**
     * Get the cart of the order
     *
     * @return Return the corresponding cart object
     */
    @Override
    public ICart getCart() {
        return this.cart;
    }

    /**
     * Set the status of the order
     *
     * @param status False for a non-completed order and true for a completed order
     */
    @Override
    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String getID() {
        return this.ID;
    }

}
