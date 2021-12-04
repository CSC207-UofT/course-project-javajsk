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

@SuppressWarnings("DuplicatedCode")
public class RemoveFoodFromMenuInteractor implements RemoveFoodFromMenu {
    VendorRepository vendorRepository;
    RepositoryBoundary repositoryBoundary;
    VendorBoundary vendorBoundary;
    Repository<Shop> shopRepository;
    ObjectBoundary<Menu> menuObjectBoundary;

    @Override
    public ResponseObject removeFood(String vendorToken, Food food) {
        Vendor vendor = (Vendor) vendorRepository.getUserFromToken(vendorToken);
        if(vendor == null){
            return repositoryBoundary.queryNotFound("No such vendor found");
        }
        Shop shop = vendor.getShop();
        Menu menu = shop.getMenu();
        menu.deleteFood(food);

        if(!shopRepository.update(shop.getId(), shop)){
            return repositoryBoundary.modificationFailed("Failed to remove food from menu.");
        }

        return menuObjectBoundary.showObject(menu);

    }
}
