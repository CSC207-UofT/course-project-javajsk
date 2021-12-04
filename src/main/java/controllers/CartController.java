package controllers;

import businessrules.cart.inputboundaries.AddToCart;
import businessrules.cart.inputboundaries.EmptyCart;
import businessrules.cart.inputboundaries.RemoveFromCart;
import businessrules.cart.inputboundaries.ViewCart;
import businessrules.outputboundaries.ResponseObject;
import entities.Cart;
import entities.Food;
import entities.Selection;
import entities.Singleton;
import org.springframework.web.bind.annotation.*;

public class CartController {

    AddToCart addToCart;
    EmptyCart emptyCart;
    RemoveFromCart removeFromCart;
    ViewCart viewCart;

    public CartController(AddToCart addToCart, EmptyCart emptyCart, RemoveFromCart removeFromCart, ViewCart viewCart) {
        this.addToCart = addToCart;
        this.emptyCart = emptyCart;
        this.removeFromCart = removeFromCart;
        this.viewCart = viewCart;
    }
    @PutMapping("/AddtoCart/{userToken}/{shopID}/{foodId}")
    public Cart runAddToCart(@PathVariable String userToken, @PathVariable String shopId,
                             @PathVariable String foodId, @RequestBody Selection[] selection){
        ResponseObject response = addToCart.addToCart(userToken, shopId, foodId, selection);
        return (Cart) response.getContents();
    }
    @PutMapping("/EmptyCart/{userToken}")
    public Cart runEmptyCart(@PathVariable String userToken){
        ResponseObject response = emptyCart.emptyCart(userToken);
        return (Cart) response.getContents();
    }

    @PutMapping("/RemovefromCart/{userToken}")
    public Cart runRemoveFromCart(@PathVariable String userToken, @RequestBody Food food,
                                  @RequestBody Selection[] selection){
        ResponseObject response = removeFromCart.removeFromCart(userToken, food, selection);
        return (Cart) response.getContents();
    }
    @GetMapping("/ViewCart/{userToken}")
    public Cart runViewCart(@PathVariable String userToken){
        ResponseObject response = viewCart.viewCart(userToken);
        return (Cart) response.getContents();
    }

}
