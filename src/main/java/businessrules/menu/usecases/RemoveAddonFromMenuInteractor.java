package businessrules.menu.usecases;

import businessrules.dai.Repository;
import businessrules.dai.VendorRepository;
import businessrules.menu.inputboundaries.RemoveAddonFromMenu;
import businessrules.outputboundaries.ObjectBoundary;
import businessrules.outputboundaries.RepositoryBoundary;
import businessrules.outputboundaries.ResponseObject;
import businessrules.outputboundaries.VendorBoundary;
import entities.Addon;
import entities.Menu;
import entities.Shop;
import entities.Vendor;

/**
 * Use case for removing an addon entity from a menu entry in a repository
 */
public class RemoveAddonFromMenuInteractor implements RemoveAddonFromMenu {
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
     * The Menu object boundary.
     */
    ObjectBoundary<Menu> menuObjectBoundary;

    /**
     * Instantiates a use case for removing an addon entity from a menu entry
     *
     * @param vR  vendor repository
     * @param rB  repository boundary
     * @param vB  vendor boundary
     * @param sR  shop repository
     * @param mOB menu object boundary
     */
    public RemoveAddonFromMenuInteractor(VendorRepository vR, RepositoryBoundary rB, VendorBoundary vB,
                                         Repository<Shop> sR, ObjectBoundary<Menu> mOB) {
        this.vendorRepository = vR;
        this.repositoryBoundary = rB;
        this.vendorBoundary = vB;
        this.shopRepository = sR;
        this.menuObjectBoundary = mOB;
    }

    /**
     * Method for removing an addon entity
     *
     * @param vendorToken vendor token
     * @param addon       addon entity
     * @return a response object
     */
    @Override
    public ResponseObject removeAddon(String vendorToken, Addon addon) {
        Vendor vendor = (Vendor) vendorRepository.getUserFromToken(vendorToken);
        if (vendor == null) {
            return repositoryBoundary.queryNotFound("No such vendor found");
        }
        Shop shop = vendor.getShop();
        Menu menu = shop.getMenu();
        menu.deleteAddon(addon);
        shop.setMenu(menu);

        if (!shopRepository.update(shop.getId(), shop)) {
            return repositoryBoundary.modificationFailed("Failed to remove addon from menu.");
        }

        return menuObjectBoundary.showObject(menu);
    }
}
