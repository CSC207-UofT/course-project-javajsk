package businessrules.menu.usecases;

import businessrules.dai.Repository;
import businessrules.dai.VendorRepository;
import businessrules.menu.inputboundaries.SetAddonAvailability;
import businessrules.outputboundaries.ObjectBoundary;
import businessrules.outputboundaries.RepositoryBoundary;
import businessrules.outputboundaries.ResponseObject;
import businessrules.outputboundaries.VendorBoundary;
import entities.Addon;
import entities.Menu;
import entities.Shop;
import entities.Vendor;

/**
 * Use case for setting the availability of an addon on a menu of a repository
 */
public class SetAddonAvailabilityInteractor implements SetAddonAvailability {
    /**
     * The Menu object boundary.
     */
    ObjectBoundary<Menu> menuObjectBoundary;
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
     * The Shop repository.
     */
    Repository<Shop> shopRepository;

    /**
     * Instantiates a use case for setting the availability of an addon on a menu
     *
     * @param mOB the menu object boundary
     * @param vR  the vendor repository
     * @param rB  the repository boundary
     * @param vB  the vendor boundary
     * @param sR  the shop repository
     */
    public SetAddonAvailabilityInteractor(ObjectBoundary<Menu> mOB, VendorRepository vR, RepositoryBoundary rB,
                                          VendorBoundary vB, Repository<Shop> sR) {
        this.menuObjectBoundary = mOB;
        this.vendorRepository = vR;
        this.repositoryBoundary = rB;
        this.vendorBoundary = vB;
        this.shopRepository = sR;
    }

    /**
     * Method for setting add on availability
     * @param vendorToken the vendor token
     * @param addon the addon entity
     * @param newAvailability the new availability
     * @return a response object
     */
    @Override
    public ResponseObject setAddonAvailability(String vendorToken, Addon addon, boolean newAvailability) {
        Vendor vendor = (Vendor) vendorRepository.getUserFromToken(vendorToken);
        if(vendor == null){
            return repositoryBoundary.queryNotFound("No such vendor found.");
        }

        Shop shop = vendor.getShop();

        Addon shopAddon = shop.getMenu().getAddon(addon);
        if(shopAddon == null){
            return vendorBoundary.error("No such addon found in the menu.");
        }

        shopAddon.setAvailable(newAvailability);

        if(!shopRepository.update(shop.getId(), shop)){
            return repositoryBoundary.modificationFailed("Failed to update addon availability in shop.");
        }

        return menuObjectBoundary.showObject(shop.getMenu());
    }
}
