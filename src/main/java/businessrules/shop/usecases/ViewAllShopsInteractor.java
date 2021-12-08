package businessrules.shop.usecases;

import adapters.dam.ShopDB;
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

    MongoDB db;
    public ViewAllShopsInteractor() {
        this.db = new MongoDB();
        this.shopRepository = new ShopDB(db);
    }

    /**
     * @return Returns a ResponseObject whose contents are a list of JSONs of all current shops in the database.
     */
    public ResponseObject viewAllShops() {
        return new ResponseObject(200, "",shopRepository.viewAllShops().toString());
    }
}
