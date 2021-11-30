package Adapters.Gateway.DatabaseDAI;

import Entities.Interfaces.IShop;
import Entities.Menu;
import UseCases.DataAccessInterfaces.FoodTruckRepository;

public class ShopDB implements FoodTruckRepository {
    @Override
    public IShop getFoodTruck(String id) {
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
    public IShop createFoodTruck(IShop FoodTruck) {
        return null;
    }

    @Override
    public boolean save(IShop FoodTruck) {
        return false;
    }
}
