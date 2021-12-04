package businessrules.menu.usecases;

import businessrules.dai.Repository;
import businessrules.dai.VendorRepository;
import businessrules.menu.inputboundaries.SetAddonAvailability;
import businessrules.outputboundaries.ObjectBoundary;
import businessrules.outputboundaries.RepositoryBoundary;
import businessrules.outputboundaries.ResponseObject;
import businessrules.outputboundaries.VendorBoundary;
import EntitiesUnitTest.Addon;
import EntitiesUnitTest.Menu;
import EntitiesUnitTest.Shop;
import EntitiesUnitTest.Vendor;

public class SetAddonAvailabilityInteractor implements SetAddonAvailability {
    ObjectBoundary<Menu> menuObjectBoundary;
    VendorRepository vendorRepository;
    RepositoryBoundary repositoryBoundary;
    VendorBoundary vendorBoundary;
    Repository<Shop> shopRepository;

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
