package UseCases.DataAccessInterfaces;

import Entities.Interfaces.IShop;
import Entities.Menu;

public interface FoodTruckRepository {

    /**
     * @param id The String id associated with the reference of this object in the higher level data system
     * @return
     */
    IShop getFoodTruck(String id);

    boolean setShopName(String id, String name);

    boolean setShopMenu(String id, Menu menu);

    boolean setShopStatus(String id, Boolean status);

    IShop createFoodTruck(IShop FoodTruck);

    boolean save(IShop FoodTruck);
}
