package businessrules.vendor.usecases;

import businessrules.dai.Hasher;
import businessrules.dai.Repository;
import businessrules.dai.VendorRepository;
import businessrules.outputboundaries.ObjectBoundary;
import businessrules.outputboundaries.RepositoryBoundary;
import businessrules.outputboundaries.ResponseObject;
import businessrules.outputboundaries.VendorBoundary;
import businessrules.vendor.inputboundaries.VendorSignUp;
import entities.Shop;
import entities.Vendor;

/**
 * Use case that signs up a Vendor
 */
public class VendorSignUpInteractor implements VendorSignUp {
    /**
     * The Vendor repository.
     */
    VendorRepository vendorRepository;
    /**
     * The Hasher.
     */
    Hasher hasher;
    /**
     * The Repository boundary.
     */
    RepositoryBoundary repositoryBoundary;
    /**
     * The Vendor object boundary.
     */
    ObjectBoundary<Vendor> vendorObjectBoundary;
    /**
     * The Shop repository.
     */
    Repository<Shop> shopRepository;
    /**
     * The Vendor boundary.
     */
    VendorBoundary vendorBoundary;

    /**
     * Instantiates a new Vendor sign up interactor.
     *
     * @param vendorRepository     the vendor repository
     * @param hasher               the hasher
     * @param repositoryBoundary   the repository boundary
     * @param vendorObjectBoundary the vendor object boundary
     * @param shopRepository       the shop repository
     * @param vendorBoundary       the vendor boundary
     */
    public VendorSignUpInteractor(VendorRepository vendorRepository, Hasher hasher,
                                  RepositoryBoundary repositoryBoundary, ObjectBoundary<Vendor> vendorObjectBoundary,
                                  Repository<Shop> shopRepository, VendorBoundary vendorBoundary) {
        this.vendorRepository = vendorRepository;
        this.hasher = hasher;
        this.repositoryBoundary = repositoryBoundary;
        this.vendorObjectBoundary = vendorObjectBoundary;
        this.shopRepository = shopRepository;
        this.vendorBoundary = vendorBoundary;
    }

    /**
     * Method that creates a new Vendor
     *
     * @param username     username of the new Vendor
     * @param password     password of the new Vendor
     * @param passwordConf password of the new Vendor confirmed
     * @param shopName     name of the shop of the new Vendor
     * @param shopLocation      location of the shop of the new Vendor
     * @return response object representing the new Vendor (or error message)
     */
    @Override
    public ResponseObject signUp(String username, String password, String passwordConf,
                                 String shopName, String shopLocation) {

        if(!password.equals(passwordConf)){
            return vendorBoundary.error("Passwords do not match.");
        }

        Vendor vendor = vendorRepository.findOneByFieldName("username", username);

        if(vendor != null){
            return vendorBoundary.error("Username is already taken!");
        }

        String cypherText = hasher.hash(password);

        Vendor vendorNew = new Vendor("N/A", username, cypherText, shopName, shopLocation);

        Shop shop = vendorNew.getShop();

        String shopId = shopRepository.create(shop);
        if(shopId == null){
            return repositoryBoundary.creationFailed("Failed to create a new shop in repository.");
        }

        shop.setId(shopId);

        String vendorId = vendorRepository.create(vendorNew);
        if(vendorId == null){
            return repositoryBoundary.creationFailed("Failed to create a new vendor in repository.");
        }
        vendorNew.setId(vendorId);
        return vendorObjectBoundary.showObject(vendorNew);
    }
}
