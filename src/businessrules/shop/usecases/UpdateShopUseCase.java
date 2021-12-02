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

    public UpdateShopUseCase(ShopRepository sR, VendorRepository vR,
                             ShopModel sM, VendorLoader vL, ShopLoader sL) {
        this.shopRepository = sR;
        this.vendorRepository = vR;
        this.shopModel = sM;
        this.vendorLoader = vL;
        this.shopLoader = sL;
    }

    @Override
    public JSONObject updateShop(String vendorToken, String shopId, JSONObject shopData) {
        Vendor vendor = vendorLoader.loadVendorFromToken(vendorToken);
        Shop shop = shopLoader.loadShopFromId(shopId);

        if(vendor == null || shop == null){
            return shopModel.displayError("Error. Invalid vendor token or shop id");
        }

        Shop newShop = shopLoader.loadShop(shopData);
        newShop.setId(shopId); //id of shop should not be changing

        vendor.setShop(newShop);

        if(!shopRepository.updateShop(shopId, shopData)){
            return shopModel.displayError("Error. Unable to update shop.");
        } else if(!vendorRepository.updateUser(vendor.getId(), vendor.jsonify())){
            return shopModel.displayError("Error. Unable to update shop for vendor");
        }

        return shopModel.displayShop(shopData);
    }
}
