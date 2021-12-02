package businessrules.shop.usecases;

import businessrules.dai.ShopRepository;
import businessrules.outputboundary.ShopModel;
import businessrules.loaders.ShopLoader;
import businessrules.shop.inputboundaries.ReadShopInputBoundary;
import entities.Shop;
import org.json.JSONException;
import org.json.JSONObject;

public class ReadShopUseCase implements ReadShopInputBoundary {
    ShopRepository shopRepository;
    ShopModel shopModel;
    ShopLoader shopLoader;

    public ReadShopUseCase(ShopRepository sR, ShopModel sM, ShopLoader sL){
        this.shopRepository = sR;
        this.shopModel = sM;
        this.shopLoader = sL;
    }

    @Override
    public JSONObject readShop(String id) {
        JSONObject shopData = shopRepository.readShop(id);

        Shop shop;
        try {
            shop = shopLoader.loadShop(shopData);
        }catch (JSONException e){
            return shopModel.displayError(e.getMessage());
        }

        return shopModel.displayShop(shop.jsonify());

    }
}

