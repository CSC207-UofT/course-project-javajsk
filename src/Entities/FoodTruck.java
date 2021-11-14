package Entities;

import Entities.Interfaces.IFood;
import Entities.Interfaces.IOrderbook;
import Entities.Interfaces.IShop;

public class FoodTruck implements IShop {

    Menu menu;
    FifoOrderBook orderbook;
    String location;
    String name;

    /**
     * @param menu Initial Menu implementation
     * @param orderbook Initial OrderBook implementation
     * @param location Initial String location
     * @param name Initial String name
     */
    public FoodTruck(Menu menu, FifoOrderBook orderbook, String location, String name){
        this.menu = menu;
        this.orderbook = orderbook;
        this.location = location;
        this.name = name;
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
    public void setOrderBook(FifoOrderBook orderBook) {
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

}
