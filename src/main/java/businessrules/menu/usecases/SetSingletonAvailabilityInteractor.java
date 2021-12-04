package businessrules.menu.usecases;

import businessrules.dai.Repository;
import businessrules.dai.VendorRepository;
import businessrules.menu.inputboundaries.SetSingletonAvailability;
import businessrules.outputboundaries.ObjectBoundary;
import businessrules.outputboundaries.RepositoryBoundary;
import businessrules.outputboundaries.ResponseObject;
import businessrules.outputboundaries.VendorBoundary;
import entities.*;

public class SetSingletonAvailabilityInteractor implements SetSingletonAvailability {
    ObjectBoundary<Menu> menuObjectBoundary;
    VendorRepository vendorRepository;
    RepositoryBoundary repositoryBoundary;
    VendorBoundary vendorBoundary;
    Repository<Singleton> singletonRepository;
    Repository<Shop> shopRepository;


    @Override
    public ResponseObject setSingletonAvailability(String vendorToken, Singleton singleton, boolean newAvailability) {
        Vendor vendor = (Vendor) vendorRepository.getUserFromToken(vendorToken);
        if(vendor == null){
            return repositoryBoundary.queryNotFound("No such vendor found.");
        }

        Shop shop = vendor.getShop();
        shop.getMenu().setSingletonAvailability(singleton, newAvailability);

        if(!shopRepository.update(shop.getId(), shop)){
            return repositoryBoundary.modificationFailed("Failed to update singleton availability in shop.");
        }

        return menuObjectBoundary.showObject(shop.getMenu());
    }
}
