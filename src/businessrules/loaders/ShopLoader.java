package businessrules.loaders;

import businessrules.dai.ShopRepository;
import businessrules.outputboundary.ShopModel;
import entities.Menu;
import entities.OrderBook;
import entities.Shop;
import org.json.JSONException;
import org.json.JSONObject;

public class ShopLoader {

    ShopRepository shopRepository;
    ShopModel shopModel;
    MenuLoader menuLoader;

    public ShopLoader(ShopRepository shopRepo, ShopModel sM, MenuLoader mL){
        this.shopRepository = shopRepo;
        this.shopModel = sM;
        this.menuLoader = mL;
    }

    public Shop loadShop(JSONObject data) throws JSONException {
        // These are all assumed to exist (controller's job to check and ensure json is of correct format)
        String id = data.getString("id");
        String name = data.getString("name");
        String location = data.getString("location");
        boolean isOpen = data.getBoolean("isOpen");
        JSONObject orderBookData = data.getJSONObject("orderBook");
        JSONObject menuData = data.getJSONObject("menu");
        OrderBook orderBook = OrderBookLoader.loadOrderBook(orderBookData);
        Menu menu = MenuLoader.loadMenu(menuData);
        return new Shop(id, name, location, isOpen, menu, orderBook);
    }

    public Shop loadShopFromId(String id){
        JSONObject shopRaw = shopRepository.readShop(id);
        if(shopRaw == null){
            shopModel.displayError("Unable to find shop with id: " + id);
            return null;
        }

        try{
            return loadShop(shopRaw);
        }catch (JSONException e){
            shopModel.displayError(e.getMessage());
        }

        return null;
    }
}
