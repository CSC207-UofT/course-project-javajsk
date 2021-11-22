package UseCases.DataAcessInterfaces;

import Entities.Vendor;

/**
 * The VendorAccessInterface Interface
 *
 * This is an interface that allows for the attainment, changing, deletion, and validation
 * of vendors in the higher level data storage.
 */
public interface VendorRepository {

    /**
     *  A method that returns the desired vendor from
     *  the higher level data storage.
     *
     * @param id The associated id of the vendor.
     * @return Return the vendor associated with the id.
     */
    public Vendor getVendor(String id);

    /**
     *  A method that changes information of the desired vendor from
     *  the higher level data storage.
     *
     * @param id The associated id of the vendor.
     * @return Return the vendor associated with the id.
     */
    public Boolean setVendor(String id, String password, String name);

    /**
     *  A method that returns the authentication token used by the vendor
     *
     * @param id The associated id of the vendor.
     * @return Return the authentication token used by the vendor.
     */
    public String getAuthenticationToken(String id);

    /**
     *  A method that returns the id of a vendor from an authentication token.
     *
     * @param token The authentication token used by a vendor.
     * @return Return the associated id of the vendor.
     */
    public String getUserIDFromToken(String token);

    /**
     *  A method that returns whether a vendor's authentication token is currently valid.
     *
     * @param token The authentication token used by a vendor.
     * @return Return whether a vendor's authentication token is currently valid.
     */
    public Boolean isTokenValid(String token);
}
