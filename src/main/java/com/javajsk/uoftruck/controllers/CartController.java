package com.javajsk.uoftruck.controllers;

import adapters.dam.CartDB;
import adapters.dam.CustomerDB;
import adapters.dam.FoodDB;
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
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.web.bind.annotation.*;
import adapters.presenters.ObjectPresenter;
import adapters.presenters.RepositoryPresenter;
import adapters.presenters.VendorPresenter;

import java.util.Iterator;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class CartController {

    AddToCart addToCart;
    EmptyCart emptyCart;
    RemoveFromCart removeFromCart;
    ViewCart viewCart;
    MongoDB db = new MongoDB();
    Repository<Food> foodRepository = new FoodDB(db);
    CustomerRepository customerRepository = new CustomerDB(db);
    RepositoryBoundary repositoryBoundary = new RepositoryPresenter();
    ObjectBoundary<Cart> cartObjectBoundary = new ObjectPresenter<>();
    CartDB cartrepository = new CartDB(db);

    CartController(){
        addToCart = new AddToCartInteractor(foodRepository, cartObjectBoundary, customerRepository, repositoryBoundary);
        emptyCart = new EmptyCartInteractor(customerRepository, repositoryBoundary, cartObjectBoundary);
        removeFromCart = new RemoveFromCartInteractor(customerRepository, repositoryBoundary, foodRepository, cartObjectBoundary);
        viewCart = new ViewCartInteractor(cartObjectBoundary, customerRepository, repositoryBoundary);
    }
    
    @PutMapping("/AddtoCart/{userToken}/{foodId}/{shopId}")
    public Cart runAddToCart(@PathVariable String userToken, @PathVariable String shopId,
                             @PathVariable String foodId, @RequestBody String selections){
        JSONObject contents = new JSONObject(selections);
        JSONArray raw_selections = contents.getJSONArray("selections");
        Selection[] selection_list = new Selection[raw_selections.length()];
        for(int i = 0; i < raw_selections.length(); i++)
        {
            JSONObject raw_object = raw_selections.getJSONObject(i);
            Selection curr_selection = cartrepository.parseSelection(raw_object);
            selection_list[i] = curr_selection;
        }
        ResponseObject response = addToCart.addToCart(userToken, shopId, foodId, selection_list);
        return (Cart) response.getContents();
    }
    @PutMapping("/EmptyCart/{userToken}")
    public ResponseObject runEmptyCart(@PathVariable String userToken){
        return emptyCart.emptyCart(userToken);
    }

    @PutMapping("/RemovefromCart/{userToken}")
    public ResponseObject runRemoveFromCart(@PathVariable String userToken, @RequestBody Food food,
                                  @RequestBody Selection[] selections){
        Selection[] contents = new Selection[selections.length];
        for(int i = 0; i <= selections.length; i++){
            contents[i] =  cartrepository.parseSelection(new JSONObject(selections[i]));
        }

        return removeFromCart.removeFromCart(userToken, food, contents);
    }
    @GetMapping("/ViewCart/{userToken}")
    public ResponseObject runViewCart(@PathVariable String userToken){
        return viewCart.viewCart(userToken);
    }

}
