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
    ShopLoader shopLoader;

    public ReadMenuUseCase(ShopRepository shopRepo, MenuModel menuMod, ErrorModel er, ShopLoader shopLoader){
        this.shopRepository = shopRepo;
        this.menuModel = menuMod;
    }

    @Override
    public JSONObject readMenu(String shopId) {
        JSONObject shopData = shopRepository.readShop(shopId);

        Shop shop;
        try{
            shop = this.shopLoader.loadShop(shopData);
        }catch (JSONException e){
            return this.menuModel.displayError(e.getMessage());
        }

        Menu menu = shop.getMenu();
        return menu.jsonify();
    }
}
