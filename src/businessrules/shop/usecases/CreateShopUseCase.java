package businessrules.shop.usecases;

import businessrules.dai.ShopRepository;
import businessrules.dai.VendorRepository;
import businessrules.loaders.ShopLoader;
import businessrules.loaders.VendorLoader;
import businessrules.outputboundary.ShopModel;
import businessrules.shop.inputboundaries.CreateShopInputBoundary;
import entities.Shop;
import entities.Vendor;
import org.json.JSONException;
import org.json.JSONObject;

public class CreateShopUseCase implements CreateShopInputBoundary {

    ShopRepository shopRepository;
    VendorRepository vendorRepository;
    ShopModel shopView;
    VendorLoader vendorLoader;
    ShopLoader shopLoader;

    /**
     * Constructor
     */
    public CreateShopUseCase(ShopRepository sR, VendorRepository vR, ShopModel sM,
                             VendorLoader vL, ShopLoader sL){
        this.shopRepository = sR;
        this.vendorRepository = vR;
        this.shopView = sM;
        this.vendorLoader = vL;
        this.shopLoader = sL;

    }


    @Override
    public JSONObject createShop(String vendorToken, JSONObject data) {
        Vendor vendor = vendorLoader.loadVendorFromToken(vendorToken);
        if(vendor == null){
            return shopView.displayError("Invalid vendor token");
        }

        Shop shop;
        try{
            shop = shopLoader.loadShop(data);
        }catch (JSONException e){
            return shopView.displayError(e.getMessage());
        }

        String id = shopRepository.createShop(shop.jsonify());

        if(id == null) {
            return shopView.displayError("Unable to create shop in repository.");
        }

        shop.setId(id);
        vendor.setShop(shop);

        boolean success = vendorRepository.updateUser(vendor.getId(), vendor.jsonify());
        if(!success){
            return shopView.displayError("Unable to update vendor in the repository");
        }

        return shopView.displayShop(shop.jsonify());
    }
}
