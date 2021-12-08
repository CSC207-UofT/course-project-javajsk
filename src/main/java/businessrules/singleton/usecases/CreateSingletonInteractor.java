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

/**
 * Use case for creating a Singleton and updating the repository with the changes
 */
public class CreateSingletonInteractor implements CreateSingleton {
    /**
     * The Vendor repository.
     */
    VendorRepository vendorRepository;
    /**
     * The Repository boundary.
     */
    RepositoryBoundary repositoryBoundary;
    /**
     * The Singleton repository.
     */
    Repository<Singleton> singletonRepository;
    /**
     * The Singleton object boundary.
     */
    ObjectBoundary<Singleton> singletonObjectBoundary;
    /**
     * The Vendor boundary.
     */
    VendorBoundary vendorBoundary;

    /**
     * Instantiates a new Create singleton interactor.
     *
     * @param vendorRepository        the vendor repository
     * @param repositoryBoundary      the repository boundary
     * @param singletonRepository     the singleton repository
     * @param singletonObjectBoundary the singleton object boundary
     * @param vendorBoundary          the vendor boundary
     */
    public CreateSingletonInteractor(VendorRepository vendorRepository, RepositoryBoundary repositoryBoundary,
                                     Repository<Singleton> singletonRepository,
                                     ObjectBoundary<Singleton> singletonObjectBoundary,
                                     VendorBoundary vendorBoundary) {
        this.vendorRepository = vendorRepository;
        this.repositoryBoundary = repositoryBoundary;
        this.singletonRepository = singletonRepository;
        this.singletonObjectBoundary = singletonObjectBoundary;
        this.vendorBoundary = vendorBoundary;
    }

    /**
     * Method that creates a Singleton entity and returns its JSONObject representation
     *
     * @param vendorToken   the token of the vendor creating the singleton
     * @param singleton     the Singleton object being created
     * @return response object
     */
    @Override
    public ResponseObject createSingleton(String vendorToken, Singleton singleton) {
        Vendor vendor = (Vendor) vendorRepository.getUserFromToken(vendorToken);
        if(vendor == null){
            return repositoryBoundary.queryNotFound("No such vendor found.");
        }

        if(!vendor.getShop().getId().equals(singleton.getShopId())){
            return vendorBoundary.error("You do not own this singleton.");
        }

        String singletonId = singletonRepository.create(singleton);
        if(singletonId == null){
            return repositoryBoundary.creationFailed("Failed to create a singleton in the repository.");
        }
        singleton.setId(singletonId);

        return singletonObjectBoundary.showObject(singleton);
    }
}
