package adapters.controllers;

import adapters.JSONParser;
import entities.Interfaces.Addon;
import entities.Interfaces.Selection;
import entities.Regular.RegularSelection;
import usecases.Cart.AddToCartInputBoundary;
import usecases.Cart.CreateCartInputBoundary;
import usecases.Cart.RemoveFromCartInputBoundary;
import usecases.DataAccessInterfaces.AddonRepository;
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

    CartController(AddToCartInputBoundary addToCartInputBoundary, CreateCartInputBoundary createCartInputBoundary,
                   RemoveFromCartInputBoundary removeFromCartInputBoundary, JSONParser parser){
        this.addToCartInputBoundary = addToCartInputBoundary;
        this.createCartInputBoundary = createCartInputBoundary;
        this.removeFromCartInputBoundary = removeFromCartInputBoundary;
        this.parser = parser;
    }

    Selection generateSelection(JSONObject selection){
        Set<String> addon_ids = selection.keySet();
        HashMap<Addon, Integer> finalOrderInfo = new HashMap<>();
        for(String addon_id: addon_ids){
            Addon addon = addonRepository.getAddon(addon_id);
            finalOrderInfo.put(addon, selection.getInt(addon_id));
        }
        return (Selection) new RegularSelection(finalOrderInfo);
    }

    List<Selection> generateOrderInfo(JSONArray selections){
        ArrayList<Selection> orderData = new ArrayList<>();
        for (int i =0; i < selections.length(); i++) {
            orderData.add(generateSelection(selections.getJSONObject(i)));
        }
        return orderData;
    }

    void runAddToCart(String input){
        JSONObject data = this.parser.parse(input);
        String userToken = data.getString("userToken");
        String cartId = data.getString("cartId");
        String foodId = data.getString("foodId");
        String shopId = data.getString("shoId");
        JSONArray selections = data.getJSONArray("orderInfo");

        List<Selection> parsedSelections = this.generateOrderInfo(selections);

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
