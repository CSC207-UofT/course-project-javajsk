package businessrules.menu.usecases;

import businessrules.dai.Repository;
import businessrules.dai.VendorRepository;
import businessrules.menu.inputboundaries.AddAddonToMenu;
import businessrules.outputboundaries.ObjectBoundary;
import businessrules.outputboundaries.RepositoryBoundary;
import businessrules.outputboundaries.ResponseObject;
import businessrules.outputboundaries.VendorBoundary;
import entities.Addon;
import entities.Menu;
import entities.Shop;
import entities.Vendor;

/**
 * Use case for adding an addon to a menu of a repository
 */
public class AddAddonToMenuInteractor implements AddAddonToMenu {
    VendorRepository vendorRepository;
    RepositoryBoundary repositoryBoundary;
    VendorBoundary vendorBoundary;
    Repository<Shop> shopRepository;
    ObjectBoundary<Menu> menuObjectBoundary;

    /**
     * Instantiates a use case for adding an addon to a menu
     * @param vR the vendor repository
     * @param rB the repository boundary
     * @param vB the vendor boundary
     * @param sR the shop repository
     * @param mOB the menu object boundary
     */
    public AddAddonToMenuInteractor(VendorRepository vR, RepositoryBoundary rB, VendorBoundary vB,
                                    Repository<Shop> sR, ObjectBoundary<Menu> mOB) {
        this.vendorRepository = vR;
        this.repositoryBoundary = rB;
        this.vendorBoundary = vB;
        this.shopRepository = sR;
        this.menuObjectBoundary = mOB;
    }

    /**
     * Method for adding an addon to a menu
     * @param vendorToken the vendor token
     * @param addon the addon entity
     * @return a response object
     */
    @Override
    public ResponseObject addAddon(String vendorToken, Addon addon) {
        Vendor vendor = (Vendor) vendorRepository.getUserFromToken(vendorToken);
        if(vendor == null){
            return repositoryBoundary.queryNotFound("No such vendor found");
        }
        Shop shop = vendor.getShop();
        Menu menu = shop.getMenu();

        if(!vendor.getShop().getId().equals(addon.getShopId())){
            return vendorBoundary.error("This addon does not belong to this shop.");
        }

        menu.addAddon(addon);
        shop.setMenu(menu);

        if(!shopRepository.update(shop.getId(), shop)){
            return repositoryBoundary.modificationFailed("Failed to update shop with new addon in the menu.");
        }

        return menuObjectBoundary.showObject(menu);

    }
}
