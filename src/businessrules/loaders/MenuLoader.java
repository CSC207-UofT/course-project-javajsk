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
    FoodLoader foodLoader; //TODO: merge with branch that has FoodLoader implemented
    AddonLoader addonLoader;
    ErrorModel errorHandler;

    public MenuLoader(ShopRepository shopRepo, FoodLoader foodLoad,
                      AddonLoader addonLoad, ErrorModel er){
        this.shopRepository = shopRepo;
        this.foodLoader = foodLoad;
        this.addonLoader = addonLoad;
        this.errorHandler = er;
    }

    public static Menu loadMenu(JSONObject data) throws JSONException {
        //TODO: implement
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
