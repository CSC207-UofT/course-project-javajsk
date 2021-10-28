import Interfaces.*;

import java.util.ArrayList;

public class FoodTruck implements Shop {
    /**
     * A food truck class.
     *
     * This food truck class is supposed to represent a food truck (specifically ones found on campus at University of
     * Toronto, st. george campus.
     *
     * shopName is the Name of this foodTruck
     * location is the current location of the foodTruck
     * isOpen is a boolean value that stores whether or not the foodTruck is open.
     * menu is an instance of a Menu object that stores this foodTruck's menu.
     * orderbook is an orderBook object that allows orders to be managed for this foodTruck.
     */
    String shopName;
    String location;
    boolean isOpen;
    Menu menu;
    OrderBook orderBook;
    String ID;


    /**
     * A function that gets the shop's name in plaintext.
     * @return The shops name
     */
    @Override
    public String getShopName() {

        return shopName;

    }

    /**
     * Function to set the shop's name
     * @param newName the new name to set the shop to in plaintext.
     */
    @Override
    public void setShopName(String newName) {
        this.shopName = newName;
    }

    /**
     * A function to get the current location of the foodTruck
     * @return the location of the foodTruck.
     */
    @Override
    public String getLocation() {
        return location;
    }

    /**
     * This function is a setter for the location of the foodTruck
     * @param location the new location to move the foodTruck to.
     */
    @Override
    public void setLocation(String location) {
        this.location = location;
    }

    /**
     * A function to get the current status of the shop (i.e. is it open or closed)
     * @return a boolean value, true = open, false = closed.
     */
    @Override
    public boolean getShopStatus() {
        return isOpen;
    }


    /**
     * A function that sets the current shop's open/closed status.
     * @param status the status to set the foodTruck to.
     */
    @Override
    public void setShopStatus(boolean status) {
        isOpen = status;
    }

    @Override
    public OrderBook getOrderbook() {
        return null;
    }

    @Override
    public Menu getMenu() {
        return null;
    }


}
