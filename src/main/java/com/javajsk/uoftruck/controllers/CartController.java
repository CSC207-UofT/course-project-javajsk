package com.javajsk.uoftruck.controllers;

import adapters.dam.entityrepoitories.CustomerDB;
import adapters.dam.entityrepoitories.FoodDB;
import businessrules.cart.inputboundaries.AddToCart;
import businessrules.cart.inputboundaries.EmptyCart;
import businessrules.cart.inputboundaries.RemoveFromCart;
import businessrules.cart.inputboundaries.ViewCart;
import businessrules.cart.usecases.AddToCartInteractor;
import businessrules.cart.usecases.EmptyCartInteractor;
import businessrules.cart.usecases.RemoveFromCartInteractor;
import businessrules.cart.usecases.ViewCartInteractor;
import businessrules.dai.CustomerRepository;
import businessrules.dai.Repository;
import businessrules.outputboundaries.ObjectBoundary;
import businessrules.outputboundaries.RepositoryBoundary;
import businessrules.outputboundaries.ResponseObject;
import businessrules.outputboundaries.VendorBoundary;
import entities.Cart;
import entities.Food;
import entities.Selection;
import framework.MongoDB;
import org.springframework.web.bind.annotation.*;
import presenters.ObjectPresenter;
import presenters.RepositoryPresenter;
import presenters.VendorPresenter;

@RestController
public class CartController {

    AddToCart addToCart;
    EmptyCart emptyCart;
    RemoveFromCart removeFromCart;
    ViewCart viewCart;
    MongoDB db = new MongoDB();
    Repository<Food> foodRepository = new FoodDB(db);
    CustomerRepository customerRepository = new CustomerDB(db);
    VendorBoundary vendorBoundary = new VendorPresenter();
    RepositoryBoundary repositoryBoundary = new RepositoryPresenter();
    ObjectBoundary<Cart> cartObjectBoundary = new ObjectPresenter<Cart>();

    CartController(){
        addToCart = new AddToCartInteractor(foodRepository, cartObjectBoundary, customerRepository, repositoryBoundary);
        emptyCart = new EmptyCartInteractor(customerRepository, repositoryBoundary, cartObjectBoundary);
        removeFromCart = new RemoveFromCartInteractor(customerRepository, repositoryBoundary, foodRepository, cartObjectBoundary);
        viewCart = new ViewCartInteractor(cartObjectBoundary, customerRepository, repositoryBoundary);
    }
    @PutMapping("/AddtoCart/{userToken}/{foodId}/{shopId}")
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
