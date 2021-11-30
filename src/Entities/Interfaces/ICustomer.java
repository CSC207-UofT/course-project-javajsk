package Entities.Interfaces;

public interface ICustomer extends IUser {
    /**
     * Returns the id of a customer
     * @return the id of a customer
     */
    String getId();

    /**
     * Method for setting the customer id
     * @param id the id
     */
    void setId(String id);

    /**
     * A method for setting the cart of a customer
     * @param cart a cart object
     */
    void setCart(ICart cart);

    /**
     * A method for getting the cart of a customer
     * @return the cart object of a customer
     */
    ICart getCart();


}
