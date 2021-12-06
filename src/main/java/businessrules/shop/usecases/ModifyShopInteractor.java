package businessrules.shop.usecases;

import entities.Shop;
import businessrules.dai.Repository;
import businessrules.dai.VendorRepository;
import businessrules.outputboundaries.ObjectBoundary;
import businessrules.outputboundaries.RepositoryBoundary;
import businessrules.outputboundaries.ResponseObject;
import businessrules.outputboundaries.VendorBoundary;
import businessrules.shop.inputboundaries.ModifyShop;

import entities.Vendor;

/**
 * Use case for modifying a Shop
 */
public class ModifyShopInteractor implements ModifyShop {
    VendorRepository vendorRepository;
    RepositoryBoundary repositoryBoundary;
    Repository<Shop> shopRepository;
    VendorBoundary vendorBoundary;
    ObjectBoundary<Shop> shopObjectBoundary;

    /**
     * Method that modifies a shop by replacing it with a new
     * shop. Should only be called by a Vendor.
     *
     * @param vendorToken the Vendor that owns the shop
     * @param shop        the Shop that will replace the original shop
     * @return            JSONOBject representing modified shop
     */
    public ResponseObject modifyShop(String vendorToken, Shop shop) {
        Vendor vendor = (Vendor) vendorRepository.getUserFromToken(vendorToken);
        if (vendor == null) {
            return repositoryBoundary.queryNotFound("No such vendor found.");
        }

        Shop oldShop = vendor.getShop();
        if (!oldShop.getId().equals(shop.getId())) {
            return vendorBoundary.error("Cannot modify ids of shops.");
        }


        if (!shopRepository.update(oldShop.getId(), shop)) {
            return repositoryBoundary.modificationFailed("Failed to update shop in repository.");
        }

        return shopObjectBoundary.showObject(shop);
    }
}
