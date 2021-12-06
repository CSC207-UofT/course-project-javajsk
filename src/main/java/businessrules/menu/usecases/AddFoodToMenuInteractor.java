package businessrules.menu.usecases;

import businessrules.dai.Repository;
import businessrules.dai.VendorRepository;
import businessrules.menu.inputboundaries.AddFoodToMenu;
import businessrules.outputboundaries.ObjectBoundary;
import businessrules.outputboundaries.RepositoryBoundary;
import businessrules.outputboundaries.ResponseObject;
import businessrules.outputboundaries.VendorBoundary;
import entities.Food;
import entities.Menu;
import entities.Shop;
import entities.Vendor;

/**
 * Use case for adding a food entity to a menu entry in a repository
 */
public class AddFoodToMenuInteractor implements AddFoodToMenu {
    VendorRepository vendorRepository;
    RepositoryBoundary repositoryBoundary;
    VendorBoundary vendorBoundary;
    Repository<Shop> shopRepository;
    ObjectBoundary<Menu> menuObjectBoundary;

    /**
     * Instantiates a use case for adding a food entity to a menu
     *
     * @param vR  the vendor repository
     * @param rB  the repository boundary
     * @param vB  the vendor boundary
     * @param sR  the shop repository
     * @param mOB the menu object boundary
     */
    public AddFoodToMenuInteractor(VendorRepository vR, RepositoryBoundary rB, VendorBoundary vB,
                                   Repository<Shop> sR, ObjectBoundary<Menu> mOB) {
        this.vendorRepository = vR;
        this.repositoryBoundary = rB;
        this.vendorBoundary = vB;
        this.shopRepository = sR;
        this.menuObjectBoundary = mOB;
    }

    /**
     * Method for adding a food entity to the menu
     *
     * @param vendorToken the vendor token
     * @param food        the food entity
     * @return a response object
     */
    @Override
    public ResponseObject addFood(String vendorToken, Food food) {
        Vendor vendor = (Vendor) vendorRepository.getUserFromToken(vendorToken);
        if (vendor == null) {
            return repositoryBoundary.queryNotFound("No such vendor found");
        }
        Shop shop = vendor.getShop();
        Menu menu = shop.getMenu();
        if (!vendor.getShop().getId().equals(food.getShopId())) {
            return vendorBoundary.error("This food does not belong to this shop.");
        }

        menu.addFood(food);

        if (!shopRepository.update(shop.getId(), shop)) {
            return repositoryBoundary.modificationFailed("Failed to update shop with new food in the menu.");
        }

        return menuObjectBoundary.showObject(menu);

    }
}
