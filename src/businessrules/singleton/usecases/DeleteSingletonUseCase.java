package businessrules.singleton.usecases;

import businessrules.dai.AddonRepository;
import businessrules.dai.ShopRepository;
import businessrules.dai.SingletonRepository;
import businessrules.dai.VendorRepository;
import businessrules.loaders.AddonLoader;
import businessrules.loaders.SingletonLoader;
import businessrules.loaders.VendorLoader;
import businessrules.outputboundary.AddonModel;
import businessrules.outputboundary.ErrorModel;
import businessrules.outputboundary.SingletonModel;
import businessrules.singleton.inputboundaries.DeleteSingletonInputBoundary;
import entities.Addon;
import entities.Shop;
import entities.Singleton;
import entities.Vendor;

public class DeleteSingletonUseCase implements DeleteSingletonInputBoundary {
    SingletonRepository singletonRepository;
    SingletonModel singletonModel;
    ErrorModel errorHandler;
    VendorRepository vendorRepository;
    ShopRepository shopRepository;
    VendorLoader vendorLoader;
    SingletonLoader singletonLoader;

    public DeleteSingletonUseCase(SingletonRepository singletonrep, VendorRepository vr, ShopRepository sr,
                              SingletonModel smodel, ErrorModel em){
        this.singletonRepository = singletonrep;
        this.vendorRepository = vr;
        this.errorHandler = em;
        this.shopRepository = sr;
        this.singletonLoader = new SingletonLoader(singletonrep, em);
        this.vendorLoader = new VendorLoader(vr, em);
    }

    @SuppressWarnings("DuplicatedCode")
    @Override
    public boolean deleteSingleton(String vendorToken, String id) {
        Vendor vendor = vendorLoader.loadVendorFromToken(vendorToken);
        Singleton singleton = singletonLoader.loadSingletonFromId(id);
        if(vendor == null || singleton == null ){
            errorHandler.displayError("Incorrect vendorToken or singleton id.");
            return false;
        }
/*        Shop shop = vendor.getShop();

        boolean deleteSuccess = shop.getMenu().deleteAddon(addon);

        if(!deleteSuccess){
            errorHandler.displayError("No such addon in any shop owned by you.");
            return false;
        }

        boolean updateSuccess = shopRepository.updateShop(shop.getId(), shop.jsonify());
        if(!updateSuccess){
            errorHandler.displayError("Unable to update shop's menu and remove addon.");
            return false;
        }*/ // TODO: if singletons aren't in menus then this doesn't matter

        if(singletonRepository.deleteSingleton(id)){
            singletonModel.deleteSingleton(id);
            return true;
        }
        return false;
    }
}
