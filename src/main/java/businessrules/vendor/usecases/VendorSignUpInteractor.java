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

public class VendorSignUpInteractor implements VendorSignUp {
    VendorRepository vendorRepository;
    Hasher hasher;
    RepositoryBoundary repositoryBoundary;
    ObjectBoundary<Vendor> vendorObjectBoundary;
    Repository<Shop> shopRepository;
    VendorBoundary vendorBoundary;

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

        Vendor vendorNew = new Vendor("N/A", username,cypherText, shopName, shopLocation);

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
