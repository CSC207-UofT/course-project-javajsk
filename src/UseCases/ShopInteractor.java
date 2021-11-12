package UseCases;

import Entities.*;

import java.lang.reflect.Array;

public class ShopInteractor {

    public FoodTruck createShop(Menu menu,FifoOrderBook orderbook, String location, String name){
        FoodTruck foodTruck = new FoodTruck(menu, orderbook, location, name);

        return foodTruck;
    }
    public boolean deleteShop(FoodTruck foodTruck){
        return true;
    }

    public void updateShop(FoodTruck foodTruck,Menu menu,FifoOrderBook orderbook, String location, String name ){
        foodTruck.setLocation(location);
        foodTruck.setName(name);
        foodTruck.setOrderBook(orderbook);
        foodTruck.setMenu(menu);
    }
  //The read method just prints out information for now. There might be a better way of doing this.
    public void readShop(FoodTruck foodTruck){
        String name = foodTruck.getName();
        String location = foodTruck.getLocation();
        FifoOrderBook Orderbook = (FifoOrderBook) foodTruck.getOrderBook();
        Menu menu = foodTruck.getMenu();

        System.out.println("Name:"+name);
        System.out.println("Location:"+location);
        System.out.println("Orderbook:"+Orderbook);
        System.out.println("Menu:"+menu);

    }
}
