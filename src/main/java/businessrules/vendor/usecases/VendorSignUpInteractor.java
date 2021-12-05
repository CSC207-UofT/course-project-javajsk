package businessrules.vendor.usecases;

import adapters.dam.DBGateway;
import adapters.dam.SHA512Hasher;
import adapters.dam.entityrepoitories.ShopDB;
import adapters.dam.entityrepoitories.VendorDB;
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
import framework.MongoDB;
import presenters.ObjectPresenter;
import presenters.RepositoryPresenter;
import presenters.VendorPresenter;

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

        System.out.println("Interactor running");
        System.out.println(this.vendorRepository);
        System.out.println(this.hasher);
        System.out.println(this.repositoryBoundary);
        System.out.println(this.vendorObjectBoundary);
        System.out.println(this.shopRepository);
        System.out.println(this.vendorBoundary);

        if(!password.equals(passwordConf)){
            vendorBoundary.error("Passwords do not match.");
        }


        System.out.println(vendorRepository.findOneByFieldName("username", username));
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
