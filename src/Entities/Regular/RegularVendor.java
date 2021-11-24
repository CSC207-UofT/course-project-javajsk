package Entities.Regular;

import Entities.Interfaces.ICart;
import Entities.Interfaces.IShop;
import Entities.Interfaces.IVendor;

import java.util.List;

public class RegularVendor implements IVendor {
    List<IShop> shopList;
    @Override
    public void addShop(IShop shop) {
        shopList.add(shop);

    }
}
