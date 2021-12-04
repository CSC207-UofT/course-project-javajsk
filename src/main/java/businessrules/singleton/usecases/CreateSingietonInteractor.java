package businessrules.singleton.usecases;

import businessrules.dai.Repository;
import businessrules.dai.VendorRepository;
import businessrules.outputboundaries.ObjectBoundary;
import businessrules.outputboundaries.RepositoryBoundary;
import businessrules.outputboundaries.ResponseObject;
import businessrules.outputboundaries.VendorBoundary;
import businessrules.singleton.inputboundaries.CreateSingleton;
import entities.Singleton;
import entities.Vendor;

import java.util.List;

public class CreateSingietonInteractor implements CreateSingleton {
    VendorRepository vendorRepository;
    RepositoryBoundary repositoryBoundary;
    Repository<Singleton> singletonRepository;
    ObjectBoundary<Singleton> singletonObjectBoundary;
    VendorBoundary vendorBoundary;

    @Override
    public ResponseObject createSingleton(String vendorToken, Singleton singleton) {
        Vendor vendor = (Vendor) vendorRepository.getUserFromToken(vendorToken);
        if(vendor == null){
            return repositoryBoundary.queryNotFound("No such vendor found.");
        }

        String singletonId =singletonRepository.create(singleton);
        if(!vendor.getShop().getId().equals(singleton.getShopId())){
            return vendorBoundary.error("You do not own this singleton.");
        }
        if(singletonId == null){
            return repositoryBoundary.creationFailed("Failed to create a singleton in the repository.");
        }
        singleton.setId(singletonId);

        List<Singleton> singletons = singletonRepository.readMultiple("shopId", vendor.getShop().getId());
        return singletonObjectBoundary.showObjectList(singletons);
    }
}
