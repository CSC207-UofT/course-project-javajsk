package businessrules.loaders;

import businessrules.dai.ShopRepository;
import businessrules.outputboundary.ErrorModel;
import entities.*;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MenuLoader {

    ShopRepository shopRepository;
    FoodLoader foodLoader;
    AddonLoader addonLoader;
    ErrorModel errorHandler;

    public MenuLoader(ShopRepository shopRepo, FoodLoader fL, AddonLoader aL, ErrorModel er){
        this.shopRepository = shopRepo;
        this.foodLoader = fL;
        this.addonLoader = aL;
        this.errorHandler = er;
    }

    public Menu loadMenu(JSONObject data) throws JSONException {
        JSONArray foodData = data.getJSONArray("foods");
        JSONArray addonData = data.getJSONArray("addons");

        List<Food> foodList = new ArrayList<>();

        for(int i = 0; i < foodData.length(); i++){
            JSONObject foodRow = foodData.getJSONObject(i);
            foodList.add(foodLoader.loadFood(foodRow));
        }

        List<Addon> addonList = new ArrayList<>();
        for(int i = 0; i < addonData.length(); i++){
            JSONObject addonRow = foodData.getJSONObject(i);
            addonList.add(addonLoader.loadAddon(addonRow));
        }

        return new Menu(foodList, addonList);
    }
}
