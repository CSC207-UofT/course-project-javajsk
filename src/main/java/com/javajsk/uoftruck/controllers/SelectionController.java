package com.javajsk.uoftruck.controllers;

import adapters.dam.entityrepoitories.*;
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
import presenters.CustomerPresenter;
import presenters.ObjectPresenter;
import presenters.RepositoryPresenter;
import presenters.VendorPresenter;


import java.util.HashMap;

@RestController
public class SelectionController {
    ModifyDefaultSelection modifyDefaultSelection;
    ModifySelectionInCart modifySelectionInCart;
    VendorRepository vendorRepository;
    CustomerRepository customerRepository;
    Repository<Singleton> singletonRepository;
    Repository<Food> foodRepository;
    MongoDB db;
    CartDB cartRepository;
    VendorBoundary vendorBoundary = new VendorPresenter();
    CustomerBoundary customerBoundary = new CustomerPresenter();
    RepositoryBoundary repositoryBoundary = new RepositoryPresenter();
    ObjectBoundary<Singleton> singletonObjectBoundary = new ObjectPresenter<>();
    ObjectBoundary<Cart> cartObjectBoundary = new ObjectPresenter<>();


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

    @PostMapping("/ModifyDefaultSelection/{vendorToken}/{singletonId}")
    public ResponseObject runModifyDefaultSelection(@PathVariable String singletonId, @PathVariable String vendorToken,
                                               @RequestBody String selectionStr){
        JSONObject selectionJson = new JSONObject(selectionStr);
        Selection selection = cartRepository.parseSelection(selectionJson);
        return modifyDefaultSelection.modifyDefaultSelection(vendorToken, singletonId, selection);
    }

    @PostMapping("/ModifySelectionInCart/{vendorToken}/{foodId}")
    public ResponseObject runModifySelectionInCart(@PathVariable String foodId, @PathVariable String vendorToken,
                                               @RequestBody Selection[] original, @RequestBody Selection[] new_singletons){
        Selection[] original1 = new Selection[original.length];
        for(int i = 0; i <= original.length; i++){
            original1[i] =  cartrepository.parseSelection(new JSONObject(original[i]));
        }
        Selection[]  new_singletons1= new Selection[new_singletons.length];
        for(int i = 0; i <= new_singletons.length; i++){
            new_singletons1[i] =  cartrepository.parseSelection(new JSONObject(new_singletons[i]));
        }
        return modifySelectionInCart.modifySelection(vendorToken, foodId, original1, new_singletons1);
    }
}
