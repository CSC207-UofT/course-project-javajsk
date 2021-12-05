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
    VendorRepository vendorRepository;
    RepositoryBoundary repositoryBoundary;
    VendorBoundary vendorBoundary;
    ObjectBoundary<Vendor> vendorObjectBoundary;
    Hasher hasher;

    public ModifyVendorInteractor(VendorRepository vendorRepository,
                                  RepositoryBoundary repositoryBoundary, VendorBoundary vendorBoundary,
                                  ObjectBoundary<Vendor> vendorObjectBoundary, Hasher hasher) {
        this.vendorRepository = vendorRepository;
        this.repositoryBoundary = repositoryBoundary;
        this.vendorBoundary = vendorBoundary;
        this.vendorObjectBoundary = vendorObjectBoundary;
        this.hasher = hasher;
    }

    @Override
    public ResponseObject modifyVendor(String vendorToken, String username, String password, String passwordConf) {
        Vendor vendor = (Vendor) vendorRepository.getUserFromToken(vendorToken);

    @Override
    public ResponseObject modifyVendor(String vendorToken, String username, String password, String passwordConf) {
        Vendor vendor = (Vendor) vendorRepository.getUserFromToken(vendorToken);

        if(vendor == null){
            return repositoryBoundary.queryNotFound("Unable to find such a vendor.");
        }
        if(!password.equals(passwordConf)){
            return vendorBoundary.error("Passwords must match.");
        }

        if(vendorRepository.findOneByFieldName("username", username) != null){
            return vendorBoundary.error("Username is already taken.");
        }

        vendor.setUserName(username);
        String cypherText = hasher.hash(password);
        vendor.setHashedPassword(cypherText);

        if(!vendorRepository.update(vendor.getId(), vendor)){
            return repositoryBoundary.modificationFailed("Failed to update vendor details.");
        }
        return vendorObjectBoundary.showObject(vendor);
    }
}
