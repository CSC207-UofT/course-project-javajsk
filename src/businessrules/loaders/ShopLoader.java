package businessrules.loaders;

import businessrules.dai.ShopRepository;
import businessrules.outputboundary.ErrorModel;
import entities.Menu;
import entities.OrderBook;
import entities.Shop;
import org.json.JSONException;
import org.json.JSONObject;

public class ShopLoader {

    ShopRepository shopRepository;
    MenuLoader menuLoader;
    ErrorModel errorHandler;

    public ShopLoader(ShopRepository shopRepo, ErrorModel er){
        this.shopRepository = shopRepo;
        this.errorHandler = er;
    }

    public static Shop loadShop(JSONObject data) throws JSONException {
        // These are all assumed to exist (controller's job to check and ensure json is of correct format)
        String id = data.getString("id");
        String name = data.getString("name");
        String location = data.getString("location");
        boolean isOpen = data.getBoolean("isOpen");
        // TODO: get orderbook and menu
        JSONObject orderBookData = data.getJSONObject("orderBook");
        JSONObject menuData = data.getJSONObject("menu");
        OrderBook orderBook = OrderBookLoader.loadOrderBook(orderBookData);
        Menu menu = MenuLoader.loadMenu(menuData);
        return new Shop(id, name, location, isOpen, menu, orderBook);
    }

    public Shop loadShopFromId(String id){
        JSONObject shopRaw = shopRepository.readShop(id);
        if(shopRaw == null){
            errorHandler.displayError("Unable to find shop with id: " + id);
            return null;
        }

        try{
            return ShopLoader.loadShop(shopRaw);
        }catch (JSONException e){
            errorHandler.displayError(e.getMessage());
        }

        return null;
    }
}
