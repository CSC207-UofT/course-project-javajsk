package businessrules.shop.usecases;

import businessrules.dai.Repository;
import businessrules.dai.VendorRepository;
import businessrules.outputboundaries.ObjectBoundary;
import businessrules.outputboundaries.RepositoryBoundary;
import businessrules.outputboundaries.ResponseObject;
import businessrules.outputboundaries.VendorBoundary;
import businessrules.shop.inputboundaries.ModifyShop;

import entities.Shop;
import entities.Vendor;
import org.springframework.context.annotation.Configuration;


public class ModifyShopInteractor implements ModifyShop {
    VendorRepository vendorRepository;
    RepositoryBoundary repositoryBoundary;
    Repository<Shop> shopRepository;
    VendorBoundary vendorBoundary;
    ObjectBoundary<Shop> shopObjectBoundary;

    public ResponseObject modifyShop(String vendorToken, Shop shop) {
        Vendor vendor = (Vendor) vendorRepository.getUserFromToken(vendorToken);
        if(vendor == null){
            return repositoryBoundary.queryNotFound("No such vendor found.");
        }

        Shop oldShop = vendor.getShop();
        if(!oldShop.getId().equals(shop.getId())){
            return vendorBoundary.error("Cannot modify ids of shops.");
        }


        if(!shopRepository.update(oldShop.getId(), shop)){
            return repositoryBoundary.modificationFailed("Failed to update shop in repository.");
        }

        return shopObjectBoundary.showObject(shop);
    }
}
