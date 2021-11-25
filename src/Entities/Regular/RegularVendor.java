package Entities.Regular;

import Entities.Interfaces.ICart;
import Entities.Interfaces.IShop;
import Entities.Interfaces.IVendor;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class RegularVendor implements IVendor {
    ArrayList<IShop> shopList;
    String ID;
    public RegularVendor(ArrayList<IShop> shopList, String ID){
        this.shopList = shopList;
        this.ID = ID;
    }
    @Override
    public void addShop(IShop shop) {
        shopList.add(shop);
    }

    @Override
    public ArrayList<IShop> getShops(IVendor vendor) {
        return this.shopList;
    }

    @Override
    public IShop getShop(String id) {
        for (IShop i:this.shopList){
            if (i.id == id){
                return i;
            }
        }
        return null;
    }
}
