package UseCases.DataAccessInterfaces;

import Entities.Interfaces.IShop;
import Entities.Menu;

public interface ShopRepository {

    /**
     * A method that returns the shop object with the given id.
     * @param id The String id associated with the reference of this object in the higher level data system
     * @return shop object with corresponding id
     */
    IShop getShop(String id);

    boolean setShopName(String id, String name);

    boolean setShopMenu(String id, Menu menu);

    boolean setShopStatus(String id, Boolean status);

    IShop createShop(IShop shop);

    boolean save(IShop shop);
}
