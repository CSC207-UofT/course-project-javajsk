package businessrules.selection.usecases;

import businessrules.dai.Repository;
import businessrules.dai.VendorRepository;
import businessrules.outputboundaries.ObjectBoundary;
import businessrules.outputboundaries.RepositoryBoundary;
import businessrules.outputboundaries.ResponseObject;
import businessrules.outputboundaries.VendorBoundary;
import businessrules.selection.inputboundaries.ModifyDefaultSelection;
import entities.Selection;
import entities.Singleton;
import entities.Vendor;

/**
 * Use case for modifying the default selection of a Singleton
 */
public class ModifyDefaultSelectionInteractor implements ModifyDefaultSelection {
    /**
     * The Vendor repository.
     */
    VendorRepository vendorRepository;
    /**
     * The Singleton repository.
     */
    Repository<Singleton> singletonRepository;
    /**
     * The Vendor boundary.
     */
    VendorBoundary vendorBoundary;
    /**
     * The Repository boundary.
     */
    RepositoryBoundary repositoryBoundary;
    /**
     * The Singleton object boundary.
     */
    ObjectBoundary<Singleton> singletonObjectBoundary;

    /**
     * Instantiates a new Modify default selection interactor.
     *
     * @param vendorRepository        the vendor repository
     * @param singletonRepository     the singleton repository
     * @param vendorBoundary          the vendor boundary
     * @param repositoryBoundary      the repository boundary
     * @param singletonObjectBoundary the singleton object boundary
     */
    public ModifyDefaultSelectionInteractor(VendorRepository vendorRepository,
                                            Repository<Singleton> singletonRepository,
                                            VendorBoundary vendorBoundary,
                                            RepositoryBoundary repositoryBoundary,
                                            ObjectBoundary<Singleton> singletonObjectBoundary) {
        this.vendorRepository = vendorRepository;
        this.singletonRepository = singletonRepository;
        this.vendorBoundary = vendorBoundary;
        this.repositoryBoundary = repositoryBoundary;
        this.singletonObjectBoundary = singletonObjectBoundary;
    }

    /**
     *  Instantiates a use case for modifying default selections
     * @param vR the vendor repository
     * @param rB the repository boundary
     * @param sR the singleton repository
     * @param vB the vendor boundary
     * @param sOB the singleton object boundary
     */
    public ModifyDefaultSelectionInteractor(VendorRepository vR, RepositoryBoundary rB, Repository<Singleton> sR,
                                            VendorBoundary vB, ObjectBoundary<Singleton> sOB) {
        this.vendorRepository = vR;
        this.repositoryBoundary = rB;
        this.singletonRepository = sR;
        this.vendorBoundary = vB;
        this.singletonObjectBoundary = sOB;
    }

    /**
     * Methods that modifies the default selection of a Singleton
     * by replacing it with a new one.
     *
     * @param vendorToken token of the Vendor
     * @param singletonId id of Singleton to be modified
     * @param selection   the new default selection
     * @return response object
     */
    @Override
    public ResponseObject modifyDefaultSelection(String vendorToken, String singletonId, Selection selection) {
        Vendor vendor = (Vendor) vendorRepository.getUserFromToken(vendorToken);
        if(vendor == null){
            return repositoryBoundary.queryNotFound("No such vendor found");
        }
        Singleton singleton = singletonRepository.read(singletonId);
        if(singleton == null){
            return repositoryBoundary.queryNotFound("No such singleton found");
        }

        if(!singleton.isValidSelection(selection)){
            return vendorBoundary.error("Incorrect values inputted for selection.");
        }

        singleton.setDefaultSelection(selection);

        if(!singletonRepository.update(singletonId, singleton)){
            return repositoryBoundary.modificationFailed("Failed to modify singleton with new default selection.");
        }
        return singletonObjectBoundary.showObject(singleton);

    }
}
