package UseCases;

import Entities.*;

import java.lang.reflect.Array;

public class ShopInteractor {

    /**
     * @param menu Menu object of the FoodTruck
     * @param orderbook OrderBook object of the FoodTruck
     * @param location String location of the FoodTruck
     * @param name String name of the FoodTruck
     * @return
     */
    public FoodTruck createShop(Menu menu,FifoOrderBook orderbook, String location, String name){
        FoodTruck foodTruck = new FoodTruck(menu, orderbook, location, name);

        return foodTruck;
    }

    /**
     * @param foodTruck The FoodTruck we want to delete
     * @return
     */
    public boolean deleteShop(FoodTruck foodTruck){
        return true;
    }
    //Database implementation undecided yet. Delete function will be implemented once that is done.
    /**
     * @param foodTruck The FoodTruck we want to update the details of
     * @param menu The menu we want the updated shop to have
     * @param orderbook The OrderBook the updated shop should have
     * @param location The location the updated shop should have
     * @param name The name the updated shop should have
     */
    public void updateShop(FoodTruck foodTruck,Menu menu,FifoOrderBook orderbook, String location, String name ){
        foodTruck.setLocation(location);
        foodTruck.setName(name);
        foodTruck.setOrderBook(orderbook);
        foodTruck.setMenu(menu);
    }

    /**
     * @param foodTruck The FoodTruck object we want to read the details of
     */
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
