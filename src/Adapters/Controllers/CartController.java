package Adapters.Controllers;

import Adapters.JSONParser;
import UseCases.Cart.AddToCartInputBoundary;
import UseCases.Cart.CreateCartInputBoundary;
import UseCases.Cart.RemoveFromCartInputBoundary;
import org.json.JSONObject;

public class CartController {
    AddToCartInputBoundary addToCartInputBoundary;
    CreateCartInputBoundary createCartInputBoundary;
    RemoveFromCartInputBoundary removeFromCartInputBoundary;
    JSONParser parser;

    CartController(AddToCartInputBoundary addToCartInputBoundary, CreateCartInputBoundary createCartInputBoundary,
                   RemoveFromCartInputBoundary removeFromCartInputBoundary, JSONParser parser){
        this.addToCartInputBoundary = addToCartInputBoundary;
        this.createCartInputBoundary = createCartInputBoundary;
        this.removeFromCartInputBoundary = removeFromCartInputBoundary;
        this.parser = parser;
    }

    void runAddToCart(String input){
        JSONObject data = this.parser.parse(input);
        String userToken = (String) data.get("userToken");

        //this.addToCartInputBoundary.addToCart();

    }

}
