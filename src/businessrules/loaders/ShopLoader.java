package businessrules.loaders;

import businessrules.dai.ShopRepository;
import businessrules.outputboundary.ErrorModel;
import entities.Menu;
import entities.OrderBook;
import entities.Shop;
import org.json.JSONException;
import org.json.JSONObject;

public class ShopLoader {

    ErrorModel errorHandler;
    ShopRepository shopRepository;

    public ShopLoader(ShopRepository shopRepo, ErrorModel er){
        this.shopRepository = shopRepo;
        this.errorHandler = er;
    }

    public static Shop loadShop(JSONObject data) throws JSONException {
        // TODO;
        // These are all assumed to exist (controller's job to check and ensure json is of correct format)
        String id = data.getString("id");
        String name = data.getString("name");
        String location = data.getString("location");
        boolean isOpen = data.getBoolean("isOpen");
        // TODO: get orderbook and menu
        OrderBook orderBook;
        Menu menu;
        return new Shop(id, name, location, isOpen, menu, orderBook);
    }
}
