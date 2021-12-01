package businessrules.shop.usecase;

import businessrules.dai.AddonRepository;
import businessrules.dai.ShopRepository;
import businessrules.dai.VendorRepository;
import businessrules.loaders.AddonLoader;
import businessrules.loaders.ShopLoader;
import businessrules.loaders.VendorLoader;
import businessrules.outputboundary.AddonModel;
import businessrules.outputboundary.ErrorModel;
import businessrules.outputboundary.ShopModel;
import businessrules.shop.inputboundary.CreateShopInputBoundary;
import entities.Shop;
import entities.Vendor;
import org.json.JSONException;
import org.json.JSONObject;

public class CreateShopUseCase implements CreateShopInputBoundary {

    ShopRepository shopRepository;
    VendorRepository vendorRepository;
    ErrorModel errorHandler;
    ShopModel shopView;
    VendorLoader vendorLoader;
    ShopLoader shopLoader;

    /**
     * Constructor
     */
    public CreateShopUseCase(ShopRepository shopRepo, VendorRepository vendorRepo,
                             ErrorModel er, ShopModel shopMod, VendorLoader vendLoad,
                             ShopLoader shopLoad){
        this.shopRepository = shopRepo;
        this.vendorRepository = vendorRepo;
        this.errorHandler = er;
        this.shopView = shopMod;
        this.vendorLoader = vendLoad;
        this.shopLoader = shopLoad;

    }


    @Override
    public boolean createShop(String vendorToken, JSONObject data) {
        Vendor vendor = vendorLoader.loadVendorFromToken(vendorToken);
        if(vendor == null){
            return false;
        }

        Shop shop;
        try{
            shop = ShopLoader.loadShop(data);
        }catch (JSONException e){
            errorHandler.displayError(e.getMessage());
            return false;
        }

        String id = shopRepository.createShop(shop.jsonify());

        if(id == null) {
            errorHandler.displayError("Unable to create shop in repository.");
            return false;
        }

        shop.setId(id);
        vendor.setShop(shop);

        boolean success = vendorRepository.updateUser(vendor.getId(), vendor.jsonify());
        if(!success){
            errorHandler.displayError("Unable to update vendor in the repository");
            return false;
        }

        shopView.displayShop(shop.jsonify());
        return true;
    }
}
