package Adapters.Controllers;

import UseCases.Cart.AddToCartInputBoundary;
import UseCases.Cart.CreateCartInputBoundary;
import UseCases.Cart.RemoveFromCartInputBoundary;

public class CartController {
    AddToCartInputBoundary addToCartInputBoundary;
    CreateCartInputBoundary createCartInputBoundary;
    RemoveFromCartInputBoundary removeFromCartInputBoundary;

    CartController(AddToCartInputBoundary addToCartInputBoundary, CreateCartInputBoundary createCartInputBoundary,
                   RemoveFromCartInputBoundary removeFromCartInputBoundary){
        this.addToCartInputBoundary = addToCartInputBoundary;
        this.createCartInputBoundary = createCartInputBoundary;
        this.removeFromCartInputBoundary = removeFromCartInputBoundary;
    }

}
