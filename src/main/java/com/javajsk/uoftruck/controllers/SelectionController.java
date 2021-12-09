package com.javajsk.uoftruck.controllers;

import adapters.dam.*;
import businessrules.dai.CustomerRepository;
import businessrules.dai.Repository;
import businessrules.dai.VendorRepository;
import businessrules.outputboundaries.*;
import businessrules.selection.inputboundaries.ModifyDefaultSelection;
import businessrules.selection.inputboundaries.ModifySelectionInCart;
import businessrules.selection.usecases.ModifyDefaultSelectionInteractor;
import businessrules.selection.usecases.ModifySelectionInCartInteractor;
import entities.Cart;
import entities.Food;
import entities.Selection;
import entities.Singleton;
import framework.MongoDB;
import org.json.JSONObject;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import adapters.presenters.CustomerPresenter;
import adapters.presenters.ObjectPresenter;
import adapters.presenters.RepositoryPresenter;
import adapters.presenters.VendorPresenter;

/**
 * Controller for selection use cases
 */
@RestController
public class SelectionController {
    /**
     * The Modify default selection input boundary.
     */
    ModifyDefaultSelection modifyDefaultSelection;
    /**
     * The Modify selection in cart input boundary.
     */
    ModifySelectionInCart modifySelectionInCart;
    /**
     * The Vendor repository.
     */
    VendorRepository vendorRepository;
    /**
     * The Customer repository.
     */
    CustomerRepository customerRepository;
    /**
     * The Singleton repository.
     */
    Repository<Singleton> singletonRepository;
    /**
     * The Food repository.
     */
    Repository<Food> foodRepository;
    /**
     * The database.
     */
    MongoDB db;
    /**
     * The Cart repository.
     */
    CartDB cartRepository;
    /**
     * The Vendor output boundary.
     */
    VendorBoundary vendorBoundary = new VendorPresenter();
    /**
     * The Customer output boundary.
     */
    CustomerBoundary customerBoundary = new CustomerPresenter();
    /**
     * The Repository output boundary.
     */
    RepositoryBoundary repositoryBoundary = new RepositoryPresenter();
    /**
     * The Singleton object output boundary.
     */
    ObjectBoundary<Singleton> singletonObjectBoundary = new ObjectPresenter<>();
    /**
     * The Cart object output boundary.
     */
    ObjectBoundary<Cart> cartObjectBoundary = new ObjectPresenter<>();


    /**
     * Instantiates a new Selection controller.
     */
    public SelectionController() {
        this.db = new MongoDB();
        this.vendorRepository = new VendorDB(db);
        this.customerRepository = new CustomerDB(db);
        this.singletonRepository = new SingletonDB(db);
        this.foodRepository = new FoodDB(db);
        this.cartRepository = new CartDB(db);
        this.modifyDefaultSelection = new ModifyDefaultSelectionInteractor(vendorRepository, singletonRepository,
                vendorBoundary, repositoryBoundary, singletonObjectBoundary);
        this.modifySelectionInCart = new ModifySelectionInCartInteractor(customerRepository,
                foodRepository, repositoryBoundary, customerBoundary, cartObjectBoundary);
    }

    /**
     * Runs modify default selection use case.
     *
     * @param singletonId  the singleton id
     * @param vendorToken  the vendor token
     * @param selectionStr the selection str
     * @return response object containing data to display to user
     */
    @PostMapping("/ModifyDefaultSelection/{vendorToken}/{singletonId}")
    public ResponseObject runModifyDefaultSelection(@PathVariable String singletonId, @PathVariable String vendorToken,
                                               @RequestBody String selectionStr){
        JSONObject selectionJson = new JSONObject(selectionStr);
        Selection selection = cartRepository.parseSelection(selectionJson);
        return modifyDefaultSelection.modifyDefaultSelection(vendorToken, singletonId, selection);
    }

    /**
     * Runs modify selection in cart use case.
     *
     * @param foodId         the food id
     * @param vendorToken    the vendor token
     * @param original       the original
     * @param new_singletons the new singletons
     * @return response object containing data to display to user
     */
    @PostMapping("/ModifySelectionInCart/{vendorToken}/{foodId}")
    public ResponseObject runModifySelectionInCart(@PathVariable String foodId, @PathVariable String vendorToken,
                                               @RequestBody Selection[] original, @RequestBody Selection[] new_singletons){
        Selection[] original1 = new Selection[original.length];
        for(int i = 0; i <= original.length; i++){
            original1[i] = cartRepository.parseSelection(new JSONObject(original[i]));
        }
        Selection[]  new_singletons1= new Selection[new_singletons.length];
        for(int i = 0; i <= new_singletons.length; i++){
            new_singletons1[i] = cartRepository.parseSelection(new JSONObject(new_singletons[i]));
        }
        return modifySelectionInCart.modifySelection(vendorToken, foodId, original1, new_singletons1);
    }
}
