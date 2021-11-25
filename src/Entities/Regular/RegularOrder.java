package Entities.Regular;

import Entities.Interfaces.ICart;
import Entities.Interfaces.IOrder;

public class RegularOrder implements IOrder {
    ICart cart;
    String status;
    /**
     * Construct an instance of a RegularOrder, a cart with the
     *
     * @param cart The cart object for the order
     */
    public RegularOrder(ICart cart){
        this.cart = cart;
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

}
