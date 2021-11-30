package Entities.Regular;

import Entities.Interfaces.IShop;
import Entities.Interfaces.IVendor;

import java.util.ArrayList;
import java.util.Objects;

public class RegularVendor implements IVendor {
    ArrayList<IShop> shopList;
    String id;

    public RegularVendor(ArrayList<IShop> shopList, String newId){
        this.shopList = shopList;
        this.id = newId;
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

    @Override
    public String getId() {
        return this.id;
    }

    /**
     * A method that updates the vendor's shop with id shopId with the new given shop
     * @param shopId id of shop to update
     * @param shop updated shop
     * @return whether shop was successfully updated
     */
    @Override
    public boolean updateShop(String shopId, IShop shop){
        for(int i = 0; i < this.shopList.size(); i++){
           if(this.shopList.get(i).getId().equals(shopId)){
               this.shopList.set(i, shop);
               return true;
           }
        }
        return false;
    }
}
