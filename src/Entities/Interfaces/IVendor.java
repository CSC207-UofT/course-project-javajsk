package Entities.Interfaces;

import java.util.List;

public interface IVendor extends IUser {
    void addShop(IShop shop);

    List<IShop> getShops();

    IShop getShop(String id);

    /**
     * A method that updates the vendor's shop with id shopId with the new given shop
     * @param shopId id of shop to update
     * @param shop updated shop
     * @return whether shop was successfully updated
     */
    boolean updateShop(String shopId, IShop shop);

}
