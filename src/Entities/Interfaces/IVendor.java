package Entities.Interfaces;

import java.util.List;

public interface IVendor extends IUser {
    void addShop(IShop shop);

    List<IShop> getShops(IVendor vendor);

    IShop getShop(String id);
}
