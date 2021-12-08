package businessrules.shop.usecases;

import businessrules.dai.Repository;
import businessrules.dai.VendorRepository;
import businessrules.outputboundaries.ObjectBoundary;
import businessrules.outputboundaries.RepositoryBoundary;
import businessrules.outputboundaries.ResponseObject;
import businessrules.shop.inputboundaries.ChangeShopStatus;

import entities.Shop;
import entities.Vendor;

/**
 * Use case for changing the status of a shop
 */
public class ChangeShopStatusInteractor implements ChangeShopStatus {
    /**
     * The Vendor repository.
     */
    VendorRepository vendorRepository;
    /**
     * The Shop repository.
     */
    Repository<Shop> shopRepository;
    /**
     * The Repository boundary.
     */
    RepositoryBoundary repositoryBoundary;
    /**
     * The Shop object boundary.
     */
    ObjectBoundary<Shop> shopObjectBoundary;

    public ChangeShopStatusInteractor(VendorRepository vr, Repository<Shop> sr, RepositoryBoundary rb,
                                      ObjectBoundary<Shop>sOB) {
        this.vendorRepository = vr;
        this.shopRepository = sr;
        this.repositoryBoundary = rb;
        this.shopObjectBoundary = sOB;
    }

    /**
     * Instantiates a new Change shop status interactor.
     *
     * @param vendorRepository   the vendor repository
     * @param shopRepository     the shop repository
     * @param repositoryBoundary the repository boundary
     * @param shopObjectBoundary the shop object boundary
     */
    public ChangeShopStatusInteractor(VendorRepository vendorRepository, Repository<Shop> shopRepository,
                                      RepositoryBoundary repositoryBoundary, ObjectBoundary<Shop> shopObjectBoundary) {
        this.vendorRepository = vendorRepository;
        this.shopRepository = shopRepository;
        this.repositoryBoundary = repositoryBoundary;
        this.shopObjectBoundary = shopObjectBoundary;
    }

    /**
     * Change the status of the shop belonging to the
     * specified vendor. Must only be used by a vendor.
     *
     * @param vendorToken the vendor that owns the shop
     * @param newStatus   the new status of the shop
     * @return  response object
     */
    public ResponseObject changeShopStatus(String vendorToken, boolean newStatus) {

        Vendor vendor = (Vendor) vendorRepository.getUserFromToken(vendorToken);
        if(vendor == null){
            return repositoryBoundary.queryNotFound("No such vendor found.");
        }

        Shop shop = vendor.getShop();
        shop.setOpen(newStatus);
        if(!shopRepository.update(shop.getId(), shop)){
            return repositoryBoundary.modificationFailed("Failed to update shop status in repository.");
        }

        return shopObjectBoundary.showObject(shop);
    }
}
