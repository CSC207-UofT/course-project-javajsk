package Entities;

import Entities.Interfaces.IOrderbook;
import Entities.Interfaces.IShop;

public class FoodTruck implements IShop {

    Menu menu;
    FifoOrderBook orderbook;
    String location;
    String name;
    public FoodTruck(Menu menu, FifoOrderBook orderbook, String location, String name){
        this.menu = menu;
        this.orderbook = orderbook;
        this.location = location;
        this.name = name;
    }
    @Override
    public Menu getMenu() {
        return menu;
    }

    @Override
    public IOrderbook getOrderBook() {
        return orderbook;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getLocation() {
        return location;
    }

    @Override
    public void setMenu(Menu menu) {
        this.menu = menu;
    }

    @Override
    public void setOrderBook(FifoOrderBook orderBook) {
        this.orderbook = orderBook;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public void setLocation(String location) {
        this.location = location;
    }
}
