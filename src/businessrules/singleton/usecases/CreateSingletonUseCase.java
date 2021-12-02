package businessrules.singleton.usecases;

import businessrules.dai.SingletonRepository;
import businessrules.dai.ShopRepository;
import businessrules.dai.VendorRepository;
import businessrules.loaders.SingletonLoader;
import businessrules.loaders.VendorLoader;
import businessrules.outputboundary.SingletonModel;
import businessrules.outputboundary.ErrorModel;
import businessrules.singleton.inputboundaries.CreateSingletonInputBoundary;
import entities.Singleton;
import entities.Vendor;
import org.json.JSONException;
import org.json.JSONObject;

public class CreateSingletonUseCase implements CreateSingletonInputBoundary {

    SingletonRepository singletonRepository;
    VendorRepository vendorRepository;
    ShopRepository shopRepository;
    ErrorModel errorHandler;
    SingletonModel singletonView;
    VendorLoader vendorLoader;
    SingletonLoader singletonLoader;

    public CreateSingletonUseCase(SingletonRepository singletonRepository, VendorRepository vendorRepository,
                              ShopRepository shopRepository, ErrorModel errorHandler, SingletonModel singletonView) {
        this.singletonRepository = singletonRepository;
        this.vendorRepository = vendorRepository;
        this.shopRepository = shopRepository;
        this.errorHandler = errorHandler;
        this.singletonView = singletonView;
        this.vendorLoader = new VendorLoader(vendorRepository, errorHandler);
        this.singletonLoader = new SingletonLoader(singletonRepository, errorHandler);
    }

    @Override
    public boolean createSingleton(String vendorToken, JSONObject data) {
        Vendor vendor = vendorLoader.loadVendorFromToken(vendorToken);
        if(vendor == null){
            return false;
        }

        Singleton singleton;
        try{
            singleton = SingletonLoader.loadSingleton(data);
        }catch (JSONException e){
            errorHandler.displayError(e.getMessage());
            return false;
        }

        String id = singletonRepository.createSingleton(singleton.jsonify());

        if(id == null) {
            errorHandler.displayError("Unable to create addon in the repository.");
            return false;
        }

        singleton.setId(id);

        singletonView.displaySingleton(singleton.jsonify());
        return true;


    }
}
