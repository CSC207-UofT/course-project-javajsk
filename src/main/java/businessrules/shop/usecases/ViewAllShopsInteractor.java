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

/**
 * View all shops use case.
 */
public class ViewAllShopsInteractor implements ViewAllShops {
    /**
     * The Shop repository.
     */
    ShopDB shopRepository;

    /**
     * The database.
     */
    MongoDB db;

    /**
     * Instantiates a new View all shops interactor.
     */
    public ViewAllShopsInteractor() {
        this.db = new MongoDB();
        this.shopRepository = new ShopDB(db);
    }

    /**
     * Method returns response object containing all the shops in the database
     *
     * @return response object
     */
    public ResponseObject viewAllShops() {
        return new ResponseObject(200, "", shopRepository.viewAllShops().toString());
    }
}
