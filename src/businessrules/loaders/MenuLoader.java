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
    ErrorModel errorHandler;

    public MenuLoader(ShopRepository shopRepo, ErrorModel er){
        this.shopRepository = shopRepo;
        this.errorHandler = er;
    }

    public static Menu loadMenu(JSONObject data) throws JSONException {
        //TODO: implement
        JSONArray foodData = data.getJSONArray("foods");
        JSONArray addonData = data.getJSONArray("addons");

        List<Food> foodList = new ArrayList<>();

        for(int i = 0; i < foodData.length(); i++){
            JSONObject foodRow = foodData.getJSONObject(i);
            //TODO: implement FoodLoader - or get from other branch
            foodList.add(FoodLoader.loadFood(foodRow));
        }

        List<Addon> addonList = new ArrayList<>();
        for(int i = 0; i < addonData.length(); i++){
            JSONObject addonRow = foodData.getJSONObject(i);
            addonList.add(AddonLoader.loadAddon(addonRow));
        }

        return new Menu(foodList, addonList);
    }
}
