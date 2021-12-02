package businessrules.shop.usecases;

import businessrules.dai.ShopRepository;
import businessrules.dai.VendorRepository;
import businessrules.loaders.ShopLoader;
import businessrules.loaders.VendorLoader;
import businessrules.outputboundary.ShopModel;
import businessrules.shop.inputboundaries.UpdateShopInputBoundary;
import entities.Shop;
import entities.Vendor;
import org.json.JSONObject;

public class UpdateShopUseCase implements UpdateShopInputBoundary {
    ShopRepository shopRepository;
    VendorRepository vendorRepository;
    ShopModel shopModel;
    VendorLoader vendorLoader;
    ShopLoader shopLoader;
    ErrorModel errorHandler;

    /**
     * Constructor
     */
    public UpdateShopUseCase(ShopRepository shopRepo, VendorRepository vendorRepo,
                             ShopModel shopMod, VendorLoader vendorLoad, ShopLoader shopLoad,
                             ErrorModel errorHandler) {
        this.shopRepository = shopRepo;
        this.vendorRepository = vendorRepo;
        this.shopModel = shopMod;
        this.vendorLoader = vendorLoad;
        this.shopLoader = shopLoad;
        this.errorHandler = errorHandler;
    }

    @Override
    public boolean updateShop(String vendorToken, String shopId, JSONObject shopData) {
        Vendor vendor = vendorLoader.loadVendorFromToken(vendorToken);
        Shop shop = shopLoader.loadShopFromId(shopId);

        if(vendor == null || shop == null){
            errorHandler.displayError("Error. Invalid vendor token or shop id");
            return false;
        }

        Shop newShop = ShopLoader.loadShop(shopData);
        newShop.setId(shopId); //id of shop should not be changing

        vendor.setShop(newShop);

        if(!shopRepository.updateShop(shopId, shopData)){
            errorHandler.displayError("Error. Unable to update shop.");
            return false;
        } else if(!vendorRepository.updateUser(vendor.getId(), vendor.jsonify())){
            errorHandler.displayError("Error. Unable to update shop for vendor");
            return false;
        }

        shopModel.updateShop(shopId, shopData);
        return true;
    }
}
