package Entities.Regular;

import Entities.Interfaces.ICart;
import Entities.Interfaces.IShop;
import Entities.Interfaces.IVendor;

import java.util.List;

public class RegularVendor implements IVendor {
    List<IShop> shopList;
    String ID;
    public RegularVendor(List<IShop> shopList, String ID){
        this.shopList = shopList;
        this.ID = ID;
    }
    @Override
    public void addShop(IShop shop) {
        shopList.add(shop);

    }
}
