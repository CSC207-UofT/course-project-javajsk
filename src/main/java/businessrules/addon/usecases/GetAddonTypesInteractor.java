package businessrules.addon.usecases;

import adapters.dam.AddonDB;
import businessrules.addon.inputboundaries.GetAddonTypes;
import businessrules.dai.VendorRepository;
import businessrules.outputboundaries.ObjectBoundary;
import businessrules.outputboundaries.RepositoryBoundary;
import businessrules.outputboundaries.ResponseObject;
import businessrules.outputboundaries.VendorBoundary;
import entities.Addon;
import framework.MongoDB;

public class GetAddonTypesInteractor implements GetAddonTypes {
    AddonDB addonRepository;
    VendorRepository vendorRepository;
    VendorBoundary vendorBoundary;
    RepositoryBoundary repositoryBoundary;
    ObjectBoundary<Addon> addonObjectBoundary;
    MongoDB db;
    public GetAddonTypesInteractor() {
        this.db = new MongoDB();
        this.addonRepository = new AddonDB(db);
    }

    public ResponseObject getAddonTypes() {
        System.out.println(addonRepository.getAddonTypes() != null);
        return new ResponseObject(200, "",addonRepository.getAddonTypes().toString());
    }
}
