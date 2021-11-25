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

    /**
     * Method that adds a shop to the vendor's shop list that they manage.
     * @param shop The shop that the method is going to add to the vendor's shop list.
     */
    @Override
    public void addShop(IShop shop) {
        shopList.add(shop);
    }

    /**
     * Method that returns the vendor's shop list that they manage.
     * @return The chosen vendor's shop list.
     */
    @Override
    public ArrayList<IShop> getShops() {
        return this.shopList;
    }

    /**
     * Method that returns a vendor's shop.
     * @param id The id of the shop that the vendor owns.
     * @return The shop that the vendor owns.
     */
    @Override
    public IShop getShop(String id) {
        for (IShop i:this.shopList){
            if (i.id == id){
                return i;
            }
        }
        return null;
    }

    @Override
    public String getID() {
        return this.getID();
    }
}
