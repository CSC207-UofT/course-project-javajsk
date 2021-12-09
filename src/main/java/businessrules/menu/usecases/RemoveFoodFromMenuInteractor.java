package businessrules.menu.usecases;

import businessrules.dai.Repository;
import businessrules.dai.VendorRepository;
import businessrules.menu.inputboundaries.RemoveFoodFromMenu;
import businessrules.outputboundaries.ObjectBoundary;
import businessrules.outputboundaries.RepositoryBoundary;
import businessrules.outputboundaries.ResponseObject;
import businessrules.outputboundaries.VendorBoundary;
import entities.Food;
import entities.Menu;
import entities.Shop;
import entities.Vendor;

/**
 * Use case for removing a food entity from a menu entry in a repository
 */
@SuppressWarnings("DuplicatedCode")
public class RemoveFoodFromMenuInteractor implements RemoveFoodFromMenu {
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
     * Instantiates a use case for removing a food entity from a meny
     *
     * @param vR  the vendor repository
     * @param rB  the repository boundary
     * @param vB  the vendor boundary
     * @param sR  the shop repository
     * @param mOB the menu object boundary
     */
    public RemoveFoodFromMenuInteractor(VendorRepository vR, RepositoryBoundary rB, VendorBoundary vB,
                                        Repository<Shop> sR, ObjectBoundary<Menu> mOB) {
        this.vendorRepository = vR;
        this.repositoryBoundary = rB;
        this.vendorBoundary = vB;
        this.shopRepository = sR;
        this.menuObjectBoundary = mOB;
    }

    /**
     * Method for removing food entities from a menu
     *
     * @param vendorToken vendor token
     * @param food        the food entity
     * @return a response object
     */
    @Override
    public ResponseObject removeFood(String vendorToken, Food food) {
        Vendor vendor = (Vendor) vendorRepository.getUserFromToken(vendorToken);
        if (vendor == null) {
            return repositoryBoundary.queryNotFound("No such vendor found");
        }
        Shop shop = vendor.getShop();
        Menu menu = shop.getMenu();
        menu.deleteFood(food);

        if (!shopRepository.update(shop.getId(), shop)) {
            return repositoryBoundary.modificationFailed("Failed to remove food from menu.");
        }

        return menuObjectBoundary.showObject(menu);

    }
}
