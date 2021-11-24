package UseCases.DataAccessInterfaces;

import Entities.FoodTruck;
import Entities.Interfaces.IShop;
import Entities.Menu;

public interface FoodTruckRepository {

    /**
     * @param id The String id associated with the reference of this object in the higher level data system
     * @return
     */
    IShop getFoodTruck(String id);

    public boolean setShopName(String id, String name);

    public boolean setShopMenu(String id, Menu menu);

    public boolean setShopStatus(String id, Boolean status);

    IShop createFoodTruck(IShop FoodTruck);

    boolean save(IShop FoodTruck);

}
