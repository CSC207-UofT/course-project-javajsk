package Interfaces;

import java.util.ArrayList;

/** The shop interface
 *
 * This interface allows vendors and users to interact with a shop. The shop is an abstract interface
 * which allows modification of menu items, order placing and order updating and certain getters and setters
 * related directly to shops (e.g. Shop name, shop location and shop status).
 *
 */
public interface Shop {
    /** The following methods are for shop attributes **/


    /**
     * A function that gets the shop's name in plaintext.
     * @return The shops name
     */
    String getShopName();

    /**
     * Function to set the shop's name
     * @param newName the new name to set the shop to in plaintext.
     */
    void setShopName(String newName);

    /**
     * A function to get the current location of the shop
     * @return the location of the shop.
     */
    String getLocation();

    /**
     * This function is a setter for the location of the shop
     * @param location the new location to move the shop to.
     */
    void setLocation(String location);

    /**
     * A function to get the current status of the shop (i.e. is it open or closed)
     * @return a boolean value, true = open, false = closed.
     */
    boolean getShopStatus();

    /**
     * A function that sets the current shop's open/closed status.
     * @param status the status to set the shop to.
     */
    void setShopStatus(boolean status);

    /** The following methods are related to the Menu of the shop**/

    OrderBook getOrderbook();

    Menu getMenu();

}
