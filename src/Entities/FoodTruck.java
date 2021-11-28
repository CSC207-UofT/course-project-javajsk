package Entities;

import Entities.Interfaces.*;
import java.util.HashMap;
import java.util.List;
import Entities.Interfaces.IOrderbook;
import Entities.Interfaces.IShop;


public class FoodTruck implements IShop {

    Menu menu;
    IOrderbook orderbook;
    HashMap<IAddon, Boolean> addonAvailability;
    String location;
    String name;
    boolean isOpen;

    /**
     * @param menu Initial Menu implementation
     * @param orderbook Initial OrderBook implementation
     * @param location Initial String location
     * @param name Initial String name
     */
    public FoodTruck(Menu menu, IOrderbook orderbook, String location, String name, HashMap<IAddon, Boolean> addAvail, boolean isOpen){
        this.menu = menu;
        this.orderbook = orderbook;
        this.location = location;
        this.name = name;
        this.addonAvailability = addAvail;
        this.isOpen = isOpen;
    }


    @Override
    public void setAddonAvailability(IAddon add, Boolean avail) {
        if(addonAvailability.containsKey(add)){
            addonAvailability.replace(add, avail);
        }else{
            addonAvailability.put(add, avail);
        }
    }
    
    public boolean isShopOpen(){
        return this.isOpen;
    }
    
    public void setShopOpenStatus(boolean newIsOpen){
        this.isOpen = newIsOpen;
    }

    @Override
    public boolean isAddonAvailable(IAddon add) {
        return addonAvailability.getOrDefault(add, false);
    }

    @Override
    public boolean allAddonsAvailable(List<IAddon> addons) {
        for(IAddon addon: addons){
            if(!this.isAddonAvailable(addon)){
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean isValidAddons(List<ISelection> order) {
        for(ISelection sel: order){
            if(!this.allAddonsAvailable(sel.getUsedAddons())){
                return false;
            }
        }
        return true;
    }

    /**
     * @return Get menu of FoodTruck
     */
    @Override
    public Menu getMenu() {
        return menu;
    }

    /**
     * @return Get orderBook of FoodTruck
     */
    @Override
    public IOrderbook getOrderBook() {
        return orderbook;
    }

    /**
     * @return Get name of FoodTruck
     */
    @Override
    public String getName() {
        return name;
    }

    /**
     * @return Get location of FoodTruck
     */
    @Override
    public String getLocation() {
        return location;
    }

    /**
     * @param menu Set menu of FoodTruck
     */
    @Override
    public void setMenu(Menu menu) {
        this.menu = menu;
    }

    /**
     * @param orderBook Set OrderBook of FoodTruck
     */
    @Override
    public void setOrderBook(IOrderbook orderBook) {
        this.orderbook = orderBook;
    }

    /**
     * @param name Set Name of FoodTruck
     */
    @Override
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @param location Set Location of FoodTruck
     */
    @Override
    public void setLocation(String location) {
        this.location = location;
    }

    @Override
    public String getID() {
        return null;
    }

}
