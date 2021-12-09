package businessrules.menu.usecases;

import businessrules.dai.Repository;
import businessrules.dai.VendorRepository;
import businessrules.menu.inputboundaries.SetSingletonAvailability;
import businessrules.outputboundaries.ObjectBoundary;
import businessrules.outputboundaries.RepositoryBoundary;
import businessrules.outputboundaries.ResponseObject;
import businessrules.outputboundaries.VendorBoundary;
import entities.*;

/**
 * Use case for setting the availability of a singleton in a menu of a repository
 */
public class SetSingletonAvailabilityInteractor implements SetSingletonAvailability {
    /**
     * The Menu object boundary.
     */
    ObjectBoundary<Menu> menuObjectBoundary;
    /**
     * The Vendor repository.
     */
    VendorRepository vendorRepository;
    /**
     * The Repository boundary.
     */
    RepositoryBoundary repositoryBoundary;
    /**
     * The Vendor boundary.
     */
    VendorBoundary vendorBoundary;
    /**
     * The Singleton repository.
     */
    Repository<Singleton> singletonRepository;
    /**
     * The Shop repository.
     */
    Repository<Shop> shopRepository;

    /**
     * Instantiates a use case for setting the availability of a singleton in a menu
     *
     * @param mOB   the menu object boundary
     * @param vR    the vendor repository
     * @param rB    the repository boundary
     * @param vB    the vendor boundary
     * @param singR the singleton repository
     * @param sR    the shop repository
     */
    public SetSingletonAvailabilityInteractor(ObjectBoundary<Menu> mOB, VendorRepository vR, RepositoryBoundary rB,
                                              VendorBoundary vB, Repository<Singleton> singR, Repository<Shop> sR) {
        this.menuObjectBoundary = mOB;
        this.vendorRepository = vR;
        this.repositoryBoundary = rB;
        this.vendorBoundary = vB;
        this.singletonRepository = singR;
        this.shopRepository = sR;
    }

    /**
     * Method for setting the availability of a singleton
     *
     * @param vendorToken     the vendor token
     * @param singleton       the singleton entity
     * @param newAvailability the new availability
     * @return a response object
     */
    @Override
    public ResponseObject setSingletonAvailability(String vendorToken, Singleton singleton, boolean newAvailability) {
        Vendor vendor = (Vendor) vendorRepository.getUserFromToken(vendorToken);
        if (vendor == null) {
            return repositoryBoundary.queryNotFound("No such vendor found.");
        }

        Shop shop = vendor.getShop();
        shop.getMenu().setSingletonAvailability(singleton, newAvailability);

        if (!shopRepository.update(shop.getId(), shop)) {
            return repositoryBoundary.modificationFailed("Failed to update singleton availability in shop.");
        }

        return menuObjectBoundary.showObject(shop.getMenu());
    }
}
