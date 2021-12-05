package com.javajsk.uoftruck.controllers;

import businessrules.cart.inputboundaries.AddToCart;
import businessrules.cart.inputboundaries.EmptyCart;
import businessrules.cart.inputboundaries.RemoveFromCart;
import businessrules.cart.inputboundaries.ViewCart;
import businessrules.outputboundaries.ResponseObject;
import entities.Cart;
import entities.Food;
import entities.Selection;
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
    public ResponseObject runEmptyCart(@PathVariable String userToken){
        return emptyCart.emptyCart(userToken);
    }

    @PutMapping("/RemovefromCart/{userToken}")
    public ResponseObject runRemoveFromCart(@PathVariable String userToken, @RequestBody Food food,
                                  @RequestBody Selection[] selection){
        return removeFromCart.removeFromCart(userToken, food, selection);
    }
    @GetMapping("/ViewCart/{userToken}")
    public ResponseObject runViewCart(@PathVariable String userToken){
        return viewCart.viewCart(userToken);
    }

}
