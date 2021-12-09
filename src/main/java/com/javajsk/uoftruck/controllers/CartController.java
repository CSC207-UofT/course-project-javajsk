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

/**
 * Controller for cart use cases
 */
@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class CartController {

    /**
     * The Add to cart input boundary.
     */
    AddToCart addToCart;
    /**
     * The Empty cart input boundary.
     */
    EmptyCart emptyCart;
    /**
     * The Remove from cart input boundary.
     */
    RemoveFromCart removeFromCart;
    /**
     * The View cart input boundary.
     */
    ViewCart viewCart;
    /**
     * The database.
     */
    MongoDB db = new MongoDB();
    /**
     * The Food repository.
     */
    Repository<Food> foodRepository = new FoodDB(db);
    /**
     * The Customer repository.
     */
    CustomerRepository customerRepository = new CustomerDB(db);
    /**
     * The Repository output boundary.
     */
    RepositoryBoundary repositoryBoundary = new RepositoryPresenter();
    /**
     * The Cart object output boundary.
     */
    ObjectBoundary<Cart> cartObjectBoundary = new ObjectPresenter<>();
    /**
     * The Cart repository.
     */
    CartDB cartrepository = new CartDB(db);

    /**
     * Instantiates a new Cart controller.
     */
    CartController(){
        addToCart = new AddToCartInteractor(foodRepository, cartObjectBoundary, customerRepository, repositoryBoundary);
        emptyCart = new EmptyCartInteractor(customerRepository, repositoryBoundary, cartObjectBoundary);
        removeFromCart = new RemoveFromCartInteractor(customerRepository, repositoryBoundary, foodRepository, cartObjectBoundary);
        viewCart = new ViewCartInteractor(cartObjectBoundary, customerRepository, repositoryBoundary);
    }

    /**
     * Runs add to cart use case.
     *
     * @param userToken  The user token associated with the user trying to place the order
     * @param shopId     The shopId of the shop that the user is tring to place the order to
     * @param foodId     The foodId of the food the user is trying to order
     * @param selections The selections associated with the food the user has chosen. This will be a stirng JSON that will be parsed to an object
     * @return The updated Cart after the item has been added to the cart
     */
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

    /**
     * Runs empty cart use case.
     *
     * @param userToken The usertoken associated with cart that is trying to be emptied
     * @return A ResponseObject containing an empty cart and status codes
     */
    @PutMapping("/EmptyCart/{userToken}")
    public ResponseObject runEmptyCart(@PathVariable String userToken){
        return emptyCart.emptyCart(userToken);
    }

    /**
     * Runs remove from cart use case.
     *
     * @param userToken  The user token associated with the user trying to modify their cart
     * @param food       The food that is trying to be removed
     * @param selections The selections of said food
     * @return A ResponseObject containing the updated cart and status codes
     */
    @PutMapping("/RemovefromCart/{userToken}")
    public ResponseObject runRemoveFromCart(@PathVariable String userToken, @RequestBody Food food,
                                  @RequestBody Selection[] selections){
        Selection[] contents = new Selection[selections.length];
        for(int i = 0; i <= selections.length; i++){
            contents[i] =  cartrepository.parseSelection(new JSONObject(selections[i]));
        }

        return removeFromCart.removeFromCart(userToken, food, contents);
    }

    /**
     * Runs view cart use case.
     *
     * @param userToken The user token associated with the user trying to view their cart
     * @return A ResponseObject containing their cart object and status codes
     */
    @GetMapping("/ViewCart/{userToken}")
    public ResponseObject runViewCart(@PathVariable String userToken){
        return viewCart.viewCart(userToken);
    }

}
