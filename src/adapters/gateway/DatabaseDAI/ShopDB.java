package adapters.gateway.DatabaseDAI;

import entities.Interfaces.Shop;
import entities.Menu;
import usecases.DataAccessInterfaces.FoodTruckRepository;

public class ShopDB implements FoodTruckRepository {
    @Override
    public Shop getFoodTruck(String id) {
        return null;
    }

    @Override
    public boolean setShopName(String id, String name) {
        return false;
    }

    @Override
    public boolean setShopMenu(String id, Menu menu) {
        return false;
    }

    @Override
    public boolean setShopStatus(String id, Boolean status) {
        return false;
    }

    @Override
    public Shop createFoodTruck(Shop FoodTruck) {
        return null;
    }

    @Override
    public boolean save(Shop FoodTruck) {
        return false;
    }
}
