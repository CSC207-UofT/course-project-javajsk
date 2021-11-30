package UseCases.DataAccessInterfaces;

import Entities.Interfaces.IVendor;

/**
 * The VendorAccessInterface Interface
 *
 * This is an interface that allows for the attainment, changing, deletion, and validation
 * of vendors in the higher level data storage.
 */
public interface VendorRepository extends UserRepository{
    
/**
     *  A method that returns the desired vendor from
     *  the higher level data storage.
     *
     * @param id The associated id of the vendor.
     * @return Return the vendor associated with the id.
     */
    public IVendor getVendor(String id);

    /**
     *  A method that changes information of the desired vendor from
     *  the higher level data storage.
     *
     * @param id The associated id of the vendor.
     * @return Return the vendor associated with the id.
     */
    public Boolean setVendor(String id, String password, String name);
    
    
    /**
    * Method for saving a vendor into the repository
    * @param vendor the vendor object being saved to the repository
    */
    boolean save(IVendor vendor);
}
