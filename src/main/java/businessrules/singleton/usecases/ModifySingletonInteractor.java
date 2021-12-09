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

/**
 * Use case for modifying a Singleton and updating the repository with the changes
 */
public class ModifySingletonInteractor implements ModifySingleton {
    /**
     * The Vendor repository.
     */
    VendorRepository vendorRepository;
    /**
     * The Singleton repository.
     */
    Repository<Singleton> singletonRepository;
    /**
     * The Repository boundary.
     */
    RepositoryBoundary repositoryBoundary;
    /**
     * The Vendor boundary.
     */
    VendorBoundary vendorBoundary;
    /**
     * The Singleton object boundary.
     */
    ObjectBoundary<Singleton> singletonObjectBoundary;

    /**
     * Instantiates a new Modify singleton interactor.
     *
     * @param vendorRepository        the vendor repository
     * @param singletonRepository     the singleton repository
     * @param repositoryBoundary      the repository boundary
     * @param vendorBoundary          the vendor boundary
     * @param singletonObjectBoundary the singleton object boundary
     */
    public ModifySingletonInteractor(VendorRepository vendorRepository, Repository<Singleton> singletonRepository,
                                     RepositoryBoundary repositoryBoundary, VendorBoundary vendorBoundary,
                                     ObjectBoundary<Singleton> singletonObjectBoundary) {
        this.vendorRepository = vendorRepository;
        this.singletonRepository = singletonRepository;
        this.repositoryBoundary = repositoryBoundary;
        this.vendorBoundary = vendorBoundary;
        this.singletonObjectBoundary = singletonObjectBoundary;
    }

    /**
     * Replaces the Singleton with the specified id
     * owned by the specified vendor with a new Singleton.
     * Must be used by the vendor that owns the specified Singleton.
     *
     * @param vendorToken  the token of the vendor modifying the Singleton
     * @param singletonId  the id of the Singleton to be modified
     * @param newSingleton the Singleton replacing the original
     * @return a reponse object
     */
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
