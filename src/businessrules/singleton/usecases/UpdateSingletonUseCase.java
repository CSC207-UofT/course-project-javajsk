package businessrules.singleton.usecases;

import businessrules.dai.SingletonRepository;
import businessrules.dai.ShopRepository;
import businessrules.dai.VendorRepository;
import businessrules.loaders.SingletonLoader;
import businessrules.loaders.VendorLoader;
import businessrules.outputboundary.SingletonModel;
import businessrules.outputboundary.ErrorModel;

import businessrules.singleton.inputboundaries.UpdateSingletonInputBoundary;
import entities.Singleton;
import entities.Shop;
import entities.Vendor;
import org.json.JSONObject;

public class UpdateSingletonUseCase implements UpdateSingletonInputBoundary {
    SingletonRepository singletonRepository;
    ErrorModel errorHandler;
    ShopRepository shopRepository;
    VendorRepository vendorRepository;
    SingletonModel singletonView;
    VendorLoader vendorLoader;
    SingletonLoader singletonLoader;

    public UpdateSingletonUseCase(SingletonRepository singletonRepository, ErrorModel errorHandler, ShopRepository shopRepository,
                              VendorRepository vendorRepository, SingletonModel singletonView) {
        this.singletonRepository = singletonRepository;
        this.errorHandler = errorHandler;
        this.shopRepository = shopRepository;
        this.vendorRepository = vendorRepository;
        this.singletonView = singletonView;
        this.singletonLoader = new SingletonLoader(singletonRepository, errorHandler);
        this.vendorLoader = new VendorLoader(vendorRepository, errorHandler);
    }

    @SuppressWarnings("DuplicatedCode")
    @Override
    public boolean updateSingleton(String vendorToken, String SingletonId, JSONObject object) {
        Vendor vendor = vendorLoader.loadVendorFromToken(vendorToken);
        Singleton singleton = singletonLoader.loadSingletonFromId(SingletonId);

        if(vendor == null || singleton == null){
            errorHandler.displayError("Incorrect vendorToken or SingletonId.");
            return false;
        }

        // Get the shop of the current vendor
        Shop shop = vendor.getShop();
        // Replace all instances of the singleton within the shop's foods with the updated singleton
        boolean success = shop.getMenu().updateSingleton(SingletonId, singleton);

        if(!success){
            errorHandler.displayError("No such Singleton in the shop's foods.");
            return false;
        }

        if(!singletonRepository.updateSingleton(SingletonId, singleton.jsonify())){
            errorHandler.displayError("Unable to save modified Singleton in repository.");
            return false;
        }

        if(!shopRepository.updateShop(shop.getId(), shop.jsonify())){
            errorHandler.displayError("Unable to update shop's menu and update Singleton.");
            return false;
        }

        singletonView.updateSingleton(SingletonId, object);
        return true;
    }
}
