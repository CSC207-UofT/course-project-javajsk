package businessrules.loaders;

import businessrules.dai.ShopRepository;
import businessrules.outputboundary.ErrorModel;
import entities.Food;
import entities.Menu;
import entities.OrderBook;
import entities.Shop;
import org.json.JSONException;
import org.json.JSONObject;

public class ShopLoader {

    ShopRepository shopRepository;
    MenuLoader menuLoader;
    ErrorModel errorHandler;

    public ShopLoader(ShopRepository shopRepo, MenuLoader menuLoad,
                      ErrorModel er){
        this.shopRepository = shopRepo;
        this.menuLoader = menuLoad;
        this.errorHandler = er;
    }

    public static Shop loadShop(JSONObject data) throws JSONException {
        // These are all assumed to exist (controller's job to check and ensure json is of correct format)
        String id = data.getString("id");
        String name = data.getString("name");
        String location = data.getString("location");
        boolean isOpen = data.getBoolean("isOpen");
        // TODO: get orderbook and menu
        OrderBook orderBook;
        JSONObject menuData = data.getJSONObject("menu")
        Menu menu = menuLoader.loadMenu(menuData);
        return new Shop(id, name, location, isOpen, menu, orderBook);
    }
}
