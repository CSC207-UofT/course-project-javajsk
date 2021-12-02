package businessrules.shop.usecases;

import businessrules.dai.ShopRepository;
import businessrules.outputboundary.ErrorModel;
import businessrules.outputboundary.ShopModel;
import businessrules.loaders.ShopLoader;
import businessrules.shop.inputboundaries.ReadShopInputBoundary;
import entities.Shop;
import org.json.JSONException;
import org.json.JSONObject;

public class ReadShopUseCase implements ReadShopInputBoundary {
    ShopRepository shopRepository;
    ShopModel shopModel;
    ErrorModel errorHandler;

    public ReadShopUseCase(ShopRepository shopRepo,
                           ShopModel shopMod,
                           ErrorModel er){
        this.shopRepository = shopRepo;
        this.shopModel = shopMod;
        this.errorHandler = er;
    }

    @Override
    public JSONObject readShop(String id) {
        JSONObject shopData = shopRepository.readShop(id);

        Shop shop;
        try {
            shop = ShopLoader.loadShop(shopData);
        }catch (JSONException e){
            errorHandler.displayError(e.getMessage());
            return null;
        }

        shopModel.displayShop(shopData); //necessary?
        return shop.jsonify();

    }
}

