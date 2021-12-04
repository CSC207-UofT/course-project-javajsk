package businessrules.addon.usecases;

import businessrules.addon.inputboundaries.CreateAddon;
import businessrules.dai.Repository;
import businessrules.dai.VendorRepository;
import businessrules.outputboundaries.ObjectBoundary;
import businessrules.outputboundaries.RepositoryBoundary;
import businessrules.outputboundaries.ResponseObject;
import businessrules.outputboundaries.VendorBoundary;
import entities.Addon;
import entities.Vendor;

public class CreateAddonInteractor implements CreateAddon {
    Repository<Addon> addonRepository;
    VendorRepository vendorRepository;
    VendorBoundary vendorBoundary;
    RepositoryBoundary repositoryBoundary;
    ObjectBoundary<Addon> addonObjectBoundary;

    @Override
    public ResponseObject createAddon(String vendorToken, Addon addon) {
        Vendor vendor = (Vendor) vendorRepository.getUserFromToken(vendorToken);
        if(vendor == null){
            return vendorBoundary.vendorNotFound();
        }

        String addonId = addonRepository.create(addon);

        if(addonId == null){
            return repositoryBoundary.creationFailed("Unable to create addon in repository.");
        }

        addon.setId(addonId);
        return addonObjectBoundary.showObject(addon);
    }
}
