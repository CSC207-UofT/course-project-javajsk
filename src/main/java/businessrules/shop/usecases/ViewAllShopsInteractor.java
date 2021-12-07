package businessrules.shop.usecases;

import adapters.dam.entityrepoitories.ShopDB;
import businessrules.dai.Repository;
import businessrules.dai.VendorRepository;
import businessrules.outputboundaries.ObjectBoundary;
import businessrules.outputboundaries.RepositoryBoundary;
import businessrules.outputboundaries.ResponseObject;
import businessrules.outputboundaries.VendorBoundary;
import businessrules.shop.inputboundaries.ViewAllShops;
import entities.Shop;
import framework.MongoDB;
import org.json.JSONObject;

public class ViewAllShopsInteractor implements ViewAllShops {
    ShopDB shopRepository;
    VendorRepository vendorRepository;
    VendorBoundary vendorBoundary;
    RepositoryBoundary repositoryBoundary;
    ObjectBoundary<Shop> shopObjectBoundary;
    MongoDB db;
    public ViewAllShopsInteractor() {
        this.db = new MongoDB();
        this.shopRepository = new ShopDB(db);
    }

    public ResponseObject viewAllShops() {
        //System.out.println(shopRepository.viewAllShops() != null);
        ResponseObject res = new ResponseObject(200, "",shopRepository.viewAllShops().toString());
        return res;
    }
}
