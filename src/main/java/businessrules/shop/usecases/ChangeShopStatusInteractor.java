package businessrules.shop.usecases;

import businessrules.dai.Repository;
import businessrules.dai.VendorRepository;
import businessrules.outputboundaries.ObjectBoundary;
import businessrules.outputboundaries.RepositoryBoundary;
import businessrules.outputboundaries.ResponseObject;
import businessrules.shop.inputboundaries.ChangeShopStatus;

import entities.Shop;
import entities.Vendor;
import org.springframework.context.annotation.Configuration;

/**
 * Use case for changing the status of a shop
 */
public class ChangeShopStatusInteractor implements ChangeShopStatus {
    VendorRepository vendorRepository;
    Repository<Shop> shopRepository;
    RepositoryBoundary repositoryBoundary;
    ObjectBoundary<Shop> shopObjectBoundary;

    /**
     * Method that modifies the status of the specified shop
     * by replacing it with newStatus. Should only be called by a
     * Vendor.
     *
     * @param vendorToken the vendor that owns the shop
     * @param newStatus   the new status of the shop
     * @return            JSONObject representing the shop
     */
    public ResponseObject changeShopStatus(String vendorToken, boolean newStatus) {

        Vendor vendor = (Vendor) vendorRepository.getUserFromToken(vendorToken);
        if(vendor == null){
            return repositoryBoundary.queryNotFound("No such vendor found.");
        }

        Shop shop = vendor.getShop();
        shop.setOpen(newStatus);
        if(!shopRepository.update(shop.getId(), shop)){
            return repositoryBoundary.modificationFailed("Failed to update shop status in repository.");
        }

        return shopObjectBoundary.showObject(shop);
    }
}
