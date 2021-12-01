package businessrules.menu.usecases;

import businessrules.dai.ShopRepository;
import businessrules.loaders.MenuLoader;
import businessrules.menu.inputboundaries.ReadMenuInputBoundary;
import businessrules.outputboundary.ErrorModel;
import businessrules.outputboundary.MenuModel;
import entities.Menu;
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
        JSONObject menuData = shopRepository.readShopMenu(shopId);

        Menu menu;
        try{
            menu = MenuLoader.loadMenu(menuData);
        }catch (JSONException e){
            errorHandler.displayError(e.getMessage());
            return null;
        }

        return menu.jsonify();
    }
}
