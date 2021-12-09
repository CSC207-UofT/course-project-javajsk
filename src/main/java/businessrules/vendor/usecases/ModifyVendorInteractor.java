package businessrules.vendor.usecases;

import businessrules.dai.Hasher;
import businessrules.dai.VendorRepository;
import businessrules.outputboundaries.ObjectBoundary;
import businessrules.outputboundaries.RepositoryBoundary;
import businessrules.outputboundaries.ResponseObject;
import businessrules.outputboundaries.VendorBoundary;
import businessrules.vendor.inputboundaries.ModifyVendor;
import entities.Vendor;

/**
 * Use case for modifying a Vendor
 */
public class ModifyVendorInteractor implements ModifyVendor {
    /**
     * The Vendor repository.
     */
    VendorRepository vendorRepository;
    /**
     * The Repository boundary.
     */
    RepositoryBoundary repositoryBoundary;
    /**
     * The Vendor boundary.
     */
    VendorBoundary vendorBoundary;
    /**
     * The Vendor object boundary.
     */
    ObjectBoundary<Vendor> vendorObjectBoundary;
    /**
     * The Hasher.
     */
    Hasher hasher;

    /**
     * Instantiates a new Modify vendor interactor.
     *
     * @param vendorRepository     the vendor repository
     * @param repositoryBoundary   the repository boundary
     * @param vendorBoundary       the vendor boundary
     * @param vendorObjectBoundary the vendor object boundary
     * @param hasher               the hasher
     */
    public ModifyVendorInteractor(VendorRepository vendorRepository,
                                  RepositoryBoundary repositoryBoundary, VendorBoundary vendorBoundary,
                                  ObjectBoundary<Vendor> vendorObjectBoundary, Hasher hasher) {
        this.vendorRepository = vendorRepository;
        this.repositoryBoundary = repositoryBoundary;
        this.vendorBoundary = vendorBoundary;
        this.vendorObjectBoundary = vendorObjectBoundary;
        this.hasher = hasher;
    }

    /**
     * Method that modifies a Vendor. Should only be called
     * by a Vendor to modify itself.
     *
     * @param vendorToken  token of the Vendor being modified
     * @param username     new username of the Vendor
     * @param password     new password of the Vendor
     * @param passwordConf new password of the Vendor confirmed
     * @return Response object
     */
    @Override
    public ResponseObject modifyVendor(String vendorToken, String username, String password, String passwordConf) {
        Vendor vendor = (Vendor) vendorRepository.getUserFromToken(vendorToken);

        if (vendor == null) {
            return repositoryBoundary.queryNotFound("Unable to find such a vendor.");
        }
        if (!password.equals(passwordConf)) {
            return vendorBoundary.error("Passwords must match.");
        }

        if (vendorRepository.findOneByFieldName("username", username) != null) {
            return vendorBoundary.error("Username is already taken.");
        }

        vendor.setUserName(username);
        String cypherText = hasher.hash(password);
        vendor.setHashedPassword(cypherText);

        if (!vendorRepository.update(vendor.getId(), vendor)) {
            return repositoryBoundary.modificationFailed("Failed to update vendor details.");
        }
        return vendorObjectBoundary.showObject(vendor);
    }
}
