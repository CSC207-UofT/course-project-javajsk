package Entities.Interfaces;

import java.util.List;

public interface IVendor extends IUser {
    void addShop(IShop shop);

    List<IShop> getShops();

    IShop getShop(String id);
    String getId();

}
