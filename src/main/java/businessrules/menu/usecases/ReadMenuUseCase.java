package businessrules.menu.usecases;

import businessrules.dai.ShopRepository;
import businessrules.loaders.MenuLoader;
import businessrules.loaders.ShopLoader;
import businessrules.menu.inputboundaries.ReadMenuInputBoundary;
import businessrules.outputboundary.ErrorModel;
import businessrules.outputboundary.MenuModel;
import entities.Menu;
import entities.Shop;
import org.json.JSONException;
import org.json.JSONObject;

public class ReadMenuUseCase implements ReadMenuInputBoundary {
    ShopRepository shopRepository;
    MenuModel menuModel;
    ErrorModel errorHandler;

    public ReadMenuUseCase(ShopRepository shopRepo, MenuModel menuMod, ErrorModel er){
        this.shopRepository = shopRepo;
        this.menuModel = menuMod;
        this.errorHandler = er;
    }

    @Override
    public JSONObject readMenu(String shopId) {
        JSONObject shopData = shopRepository.readShop(shopId);

        Shop shop;
        try{
            shop = ShopLoader.loadShop(shopData);
        }catch (JSONException e){
            errorHandler.displayError(e.getMessage());
            return null;
        }

        Menu menu = shop.getMenu();
        return menu.jsonify();
    }
}
