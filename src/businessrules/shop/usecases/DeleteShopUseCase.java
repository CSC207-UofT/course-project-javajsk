package businessrules.shop.usecases;

import businessrules.dai.ShopRepository;
import businessrules.dai.VendorRepository;
import businessrules.loaders.ShopLoader;
import businessrules.loaders.VendorLoader;
import businessrules.outputboundary.ShopModel;
import businessrules.outputboundary.VendorModel;
import businessrules.shop.inputboundaries.DeleteShopInputBoundary;
import entities.Shop;
import entities.Vendor;
import org.json.JSONObject;

public class DeleteShopUseCase implements DeleteShopInputBoundary {
    VendorRepository vendorRepository;
    ShopRepository shopRepository;
    ShopModel shopModel;
    VendorModel vendorModel;
    VendorLoader vendorLoader;
    ShopLoader shopLoader;

    public DeleteShopUseCase(VendorRepository vR, ShopRepository sR, ShopModel sM,
                             VendorModel vM, VendorLoader vL, ShopLoader sL) {
        this.vendorRepository = vR;
        this.shopRepository = sR;
        this.shopModel = sM;
        this.vendorModel = vM;
        this.vendorLoader = vL;
        this.shopLoader = sL;
    }

    @Override
    public JSONObject deleteShop(String vendorToken, String shopId) {
        Vendor vendor = vendorLoader.loadVendorFromToken(vendorToken);
        Shop shop = shopLoader.loadShopFromId(shopId);

        if(vendor == null || shop == null ){
            return shopModel.displayError("Incorrect vendor token or shop id.");
        }

        //TODO: is it necessary to check shop id if any vendor only has one shop
        boolean deleteSuccess = vendor.deleteShop(shopId);
        if(!deleteSuccess){
            return shopModel.displayError("Vendor does not own the shop.");
        }

        deleteSuccess = shopRepository.deleteShop(shopId);
        if(!deleteSuccess){
            return shopModel.displayError("Unable to delete shop from repository.");
        }

        deleteSuccess = vendorRepository.updateUser(vendor.getId(), vendor.jsonify());
        if(!deleteSuccess){
            return shopModel.displayError("Unable to update vendor.");
        }

        vendorModel.displayVendor(vendor.jsonify());
        return shopModel.displayShop(new JSONObject());
    }
}
