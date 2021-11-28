package UseCases.DataAccessInterfaces;

import Entities.Interfaces.IVendor;
import Entities.Regular.RegularVendor;

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
    IVendor getVendor(String id);

    /**
     *  A method that returns the authentication token used by the vendor
     *
     * @param id The associated id of the vendor.
     * @return Return the authentication token used by the vendor.
     */
    String getAuthenticationToken(String id);

    /**
     *  A method that returns the id of a vendor from an authentication token.
     *
     * @param token The authentication token used by a vendor.
     * @return Return the associated id of the vendor.
     */
    String getUserIDFromToken(String token);

    /**
     *  A method that returns the vendor from an authentication token.
     *
     * @param token The authentication token used by a vendor.
     * @return Return the associated vendor.
     */
<<<<<<< HEAD
<<<<<<< HEAD
    public RegularVendor getVendorFromToken(String token);
=======
    RegularVendor getUserFromToken(String token);
>>>>>>> 8cb19257d126513c6fa50aa3852cfe285232df75
=======
    IVendor getVendorFromToken(String token);
>>>>>>> cb06e7220d065ff2e4941227ef32338c140a30e8

    /**
     *  A method that returns whether a vendor's authentication token is currently valid.
     *
     * @param token The authentication token used by a vendor.
     * @return Return whether a vendor's authentication token is currently valid.
     */
    Boolean isTokenValid(String token);

    boolean save(IVendor vendor);
}
