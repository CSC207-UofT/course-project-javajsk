package businessrules.addon.usecases;

import businessrules.addon.inputboundaries.ModifyAddon;
import businessrules.dai.Repository;
import businessrules.dai.VendorRepository;
import businessrules.outputboundaries.ObjectBoundary;
import businessrules.outputboundaries.RepositoryBoundary;
import businessrules.outputboundaries.ResponseObject;
import businessrules.outputboundaries.VendorBoundary;
import EntitiesUnitTest.Addon;
import EntitiesUnitTest.Vendor;

public class ModifyAddonInteractor implements ModifyAddon {
    Repository<Addon> addonRepository;
    ObjectBoundary<Addon> addonObjectBoundary;
    RepositoryBoundary repositoryBoundary;
    VendorRepository vendorRepository;
    VendorBoundary vendorBoundary;

    @Override
    public ResponseObject modifyAddon(String vendorToken, String id, Addon addon) {
        Vendor vendor = (Vendor) vendorRepository.getUserFromToken(vendorToken);
        if(vendor == null){
            return vendorBoundary.vendorNotFound();
        }

        Addon oldAddon = addonRepository.read(id);
        if(oldAddon == null){
            return repositoryBoundary.queryNotFound("Unable to find such addon.");
        }

        if(!oldAddon.getShopId().equals(vendor.getShop().getId())){
            return vendorBoundary.unauthorizedAccess("You do not own the addon");
        }

        if(!addon.getId().equals(oldAddon.getId()) || !addon.getShopId().equals(oldAddon.getShopId())){
            return addonObjectBoundary.invalidObject("New addon id and shop id must match old addon id and shop id.");
        }

        if(!addonRepository.update(addon.getId(), addon)){
            return repositoryBoundary.modificationFailed("Unable to modify addon.");
        }

        return addonObjectBoundary.showObject(addon);
    }
}
