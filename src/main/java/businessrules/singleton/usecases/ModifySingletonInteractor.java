package businessrules.singleton.usecases;

import businessrules.dai.Repository;
import businessrules.dai.VendorRepository;
import businessrules.outputboundaries.ObjectBoundary;
import businessrules.outputboundaries.RepositoryBoundary;
import businessrules.outputboundaries.ResponseObject;
import businessrules.outputboundaries.VendorBoundary;
import businessrules.singleton.inputboundaries.ModifySingleton;
import entities.Singleton;
import entities.Vendor;

public class ModifySingletonInteractor implements ModifySingleton {
    VendorRepository vendorRepository;
    Repository<Singleton> singletonRepository;
    RepositoryBoundary repositoryBoundary;
    VendorBoundary vendorBoundary;
    ObjectBoundary<Singleton> singletonObjectBoundary;


    @Override
    public ResponseObject modifySingleton(String vendorToken, String singletonId, Singleton newSingleton) {
        Vendor vendor = (Vendor) vendorRepository.getUserFromToken(vendorToken);
        if(vendor == null){
            return repositoryBoundary.queryNotFound("No such vendor found.");
        }

        Singleton singleton = singletonRepository.read(singletonId);

        if(singleton == null){
            return repositoryBoundary.queryNotFound("No such singleton found");
        }

        if(!singleton.getId().equals(newSingleton.getId())){
            return vendorBoundary.error("Singleton ids cannot be modified.");
        }

        if(!singletonRepository.update(singletonId, newSingleton)){
            return repositoryBoundary.modificationFailed("Failed to modify singleton in repository.");
        }

        return singletonObjectBoundary.showObject(newSingleton);
    }
}
