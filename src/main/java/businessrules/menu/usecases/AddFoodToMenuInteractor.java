package businessrules.menu.usecases;

import businessrules.dai.Repository;
import businessrules.dai.VendorRepository;
import businessrules.menu.inputboundaries.AddFoodToMenu;
import businessrules.outputboundaries.ObjectBoundary;
import businessrules.outputboundaries.RepositoryBoundary;
import businessrules.outputboundaries.ResponseObject;
import businessrules.outputboundaries.VendorBoundary;
import EntitiesUnitTest.Food;
import EntitiesUnitTest.Menu;
import EntitiesUnitTest.Shop;
import EntitiesUnitTest.Vendor;

public class AddFoodToMenuInteractor implements AddFoodToMenu {
    VendorRepository vendorRepository;
    RepositoryBoundary repositoryBoundary;
    VendorBoundary vendorBoundary;
    Repository<Shop> shopRepository;
    ObjectBoundary<Menu> menuObjectBoundary;


    @Override
    public ResponseObject addFood(String vendorToken, Food food) {
        Vendor vendor = (Vendor) vendorRepository.getUserFromToken(vendorToken);
        if(vendor == null){
            return repositoryBoundary.queryNotFound("No such vendor found");
        }
        Shop shop = vendor.getShop();
        Menu menu = shop.getMenu();
        if(!vendor.getShop().getId().equals(food.getShopId())){
            return vendorBoundary.error("This food does not belong to this shop.");
        }

        menu.addFood(food);

        if(!shopRepository.update(shop.getId(), shop)){
            return repositoryBoundary.modificationFailed("Failed to update shop with new food in the menu.");
        }

        return menuObjectBoundary.showObject(menu);

    }
}
