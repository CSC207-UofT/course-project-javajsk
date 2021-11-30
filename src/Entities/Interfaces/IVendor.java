package Entities.Interfaces;

import java.util.List;

public interface IVendor extends IUser {
    /**
     * Returns the id of a vendor
     * @return the id of a vendor
     */
    String getId();

    /**
     * Method for setting the vendor id
     * @param id the id
     */
    void setId(String id);

    /**
     * A method that adds a shop to a vendor's collection
     * @param shop the shop to add to the vendor
     */
    void addShop(IShop shop);

    /**
     * A method that gets all the shops a vendor has
     * @return a list of shop objects owned by the vendor
     */
    List<IShop> getShops();

    /**
     * A method for finding a shop by the id
     * @param id the id of a shop
     * @return the shop object that corresponds to the id
     */
    IShop getShop(String id);


}
