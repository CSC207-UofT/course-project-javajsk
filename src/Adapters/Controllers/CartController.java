package Adapters.Controllers;

import Adapters.JSONParser;
import Entities.Interfaces.IAddon;
import Entities.Interfaces.ISelection;
import Entities.Regular.RegularSelection;
import UseCases.Cart.AddToCartInputBoundary;
import UseCases.Cart.CreateCartInputBoundary;
import UseCases.Cart.RemoveFromCartInputBoundary;
import UseCases.DataAccessInterfaces.AddonRepository;
import UseCases.ErrorPopup;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

public class CartController {
    AddToCartInputBoundary addToCartInputBoundary;
    CreateCartInputBoundary createCartInputBoundary;
    RemoveFromCartInputBoundary removeFromCartInputBoundary;
    JSONParser parser;
    AddonRepository addonRepository;
    ErrorPopup errorHandler;

    CartController(AddToCartInputBoundary addToCartInputBoundary, CreateCartInputBoundary createCartInputBoundary,
                   RemoveFromCartInputBoundary removeFromCartInputBoundary, JSONParser parser){
        this.addToCartInputBoundary = addToCartInputBoundary;
        this.createCartInputBoundary = createCartInputBoundary;
        this.removeFromCartInputBoundary = removeFromCartInputBoundary;
        this.parser = parser;
    }

    ISelection generateSelection(JSONObject selection){
        Set<String> addon_ids = selection.keySet();
        HashMap<IAddon, Integer> finalOrderInfo = new HashMap<>();
        for(String addon_id: addon_ids){
            IAddon addon = addonRepository.getAddon(addon_id);
            finalOrderInfo.put(addon, selection.getInt(addon_id));
        }
        return (ISelection) new RegularSelection(finalOrderInfo);
    }

    List<ISelection> generateOrderInfo(JSONArray selections){
        ArrayList<ISelection> orderData = new ArrayList<>();
        for (int i =0; i < selections.length(); i++) {
            orderData.add(generateSelection(selections.getJSONObject(i)));
        }
        return orderData;
    }

    void runAddToCart(String input){
        JSONObject data = this.parser.parse(input);

        if (!(data.has("userToken"))){
            errorHandler.displayError("No UserToken key in JSON Object.");
            return;
        }
        if (!(data.has("cartId"))){
            errorHandler.displayError("No cartId key in JSON Object.");
            return;
        }
        if (!(data.has("foodId"))){
            errorHandler.displayError("No foodId key in JSON Object.");
            return;
        }
        if (!(data.has("shopId"))){
            errorHandler.displayError("No shopId key in JSON Object.");
            return;
        }
        if (!(data.has("orderInfo"))){
            errorHandler.displayError("No orderInfo key in JSON Object.");
            return;
        }

        String userToken = data.getString("userToken");
        String cartId = data.getString("cartId");
        String foodId = data.getString("foodId");
        String shopId = data.getString("shopId");
        JSONArray selections = data.getJSONArray("orderInfo");

        List<ISelection> parsedSelections = this.generateOrderInfo(selections);

        this.addToCartInputBoundary.addToCart(cartId, foodId, parsedSelections, shopId, userToken);
    }

    void runCreateCart(String input){
        JSONObject data = this.parser.parse(input);
        String userToken = data.getString("userToken");
        String cartType = data.getString("cartType");
        this.createCartInputBoundary.createCart(userToken, cartType);
    }

    void runRemoveFromCart(String input){
        JSONObject data = this.parser.parse(input);
        String userToken = data.getString("userToken");
        String foodId = data.getString("foodId");
        int index = data.getInt("index");

        // TODO: Does this still make sense?
        String cartId = data.getString("cartId");

        this.removeFromCartInputBoundary.removeFromCart(cartId, foodId, index, userToken);
    }

}
