package Entities.Regular;

import Entities.Interfaces.IShop;
import Entities.Interfaces.IVendor;

import java.util.ArrayList;
import java.util.Objects;

public class RegularVendor implements IVendor {
    ArrayList<IShop> shopList;
    String id;

    /**
     * Creates a RegularVendor object
     * @param shopList the list of shops a vendor owns
     * @param id the id of the vendor
     */
    public RegularVendor(ArrayList<IShop> shopList, String id){
        this.shopList = shopList;
        this.id = id;
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
            if (Objects.equals(i.getId(), id)){
                return i;
            }
        }
        return null;
    }
    /**
     * Returns the id of a vendor
     * @return the id of a vendor
     */
    @Override
    public String getId() {
        return this.id;
    }

    /**
     * Method for setting the vendor id
     * @param id the id
     */
    @Override
    public void setId(String id) {
        this.id = id;
    }
}
