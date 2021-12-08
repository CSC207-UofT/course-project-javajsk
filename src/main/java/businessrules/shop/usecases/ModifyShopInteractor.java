package businessrules.shop.usecases;

import entities.Shop;
import businessrules.dai.Repository;
import businessrules.dai.VendorRepository;
import businessrules.outputboundaries.ObjectBoundary;
import businessrules.outputboundaries.RepositoryBoundary;
import businessrules.outputboundaries.ResponseObject;
import businessrules.outputboundaries.VendorBoundary;
import businessrules.shop.inputboundaries.ModifyShop;

import entities.Vendor;

/**
 * Use case for modifying a Shop
 */
public class ModifyShopInteractor implements ModifyShop {
    /**
     * The Vendor repository.
     */
    VendorRepository vendorRepository;
    /**
     * The Repository boundary.
     */
    RepositoryBoundary repositoryBoundary;
    /**
     * The Shop repository.
     */
    Repository<Shop> shopRepository;
    /**
     * The Vendor boundary.
     */
    VendorBoundary vendorBoundary;
    /**
     * The Shop object boundary.
     */
    ObjectBoundary<Shop> shopObjectBoundary;

    public ModifyShopInteractor(VendorRepository vr, Repository<Shop> sr, RepositoryBoundary rb,
                                      ObjectBoundary<Shop>sOB, VendorBoundary vB) {
        this.vendorRepository = vr;
        this.shopRepository = sr;
        this.repositoryBoundary = rb;
        this.shopObjectBoundary = sOB;
        this.vendorBoundary = vB;
    }

    /**
     * Instantiates a new Modify shop interactor.
     *
     * @param vendorRepository   the vendor repository
     * @param repositoryBoundary the repository boundary
     * @param shopRepository     the shop repository
     * @param vendorBoundary     the vendor boundary
     * @param shopObjectBoundary the shop object boundary
     */
    public ModifyShopInteractor(VendorRepository vendorRepository, RepositoryBoundary repositoryBoundary,
                                Repository<Shop> shopRepository, VendorBoundary vendorBoundary,
                                ObjectBoundary<Shop> shopObjectBoundary) {
        this.vendorRepository = vendorRepository;
        this.repositoryBoundary = repositoryBoundary;
        this.shopRepository = shopRepository;
        this.vendorBoundary = vendorBoundary;
        this.shopObjectBoundary = shopObjectBoundary;
    }

    /**
     * Modifies the specified Vendor's shop by replacing with another
     *
     * @param vendorToken the Vendor that owns the shop
     * @param shop        the Shop that will replace the original shop
     * @return a response object
     */
    public ResponseObject modifyShop(String vendorToken, Shop shop) {
        Vendor vendor = (Vendor) vendorRepository.getUserFromToken(vendorToken);
        if(vendor == null){
            return repositoryBoundary.queryNotFound("No such vendor found.");
        }

        Shop oldShop = vendor.getShop();
        if(!oldShop.getId().equals(shop.getId())){
            return vendorBoundary.error("Cannot modify ids of shops.");
        }


        if(!shopRepository.update(oldShop.getId(), shop)){
            return repositoryBoundary.modificationFailed("Failed to update shop in repository.");
        }

        return shopObjectBoundary.showObject(shop);
    }
}
