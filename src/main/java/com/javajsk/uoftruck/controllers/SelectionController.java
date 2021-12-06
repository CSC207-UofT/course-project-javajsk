package com.javajsk.uoftruck.controllers;

import adapters.dam.entityrepoitories.CartDB;
import businessrules.dai.Repository;
import businessrules.outputboundaries.ResponseObject;
import businessrules.selection.inputboundaries.ModifyDefaultSelection;
import businessrules.selection.inputboundaries.ModifySelectionInCart;
import entities.Cart;
import entities.Selection;
import framework.MongoDB;
import org.json.JSONObject;
import org.springframework.web.bind.annotation.*;


import java.util.HashMap;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class SelectionController {
    ModifyDefaultSelection modifyDefaultSelection;
    ModifySelectionInCart modifySelectionInCart;
    MongoDB db = new MongoDB();
    CartDB cartrepository = new CartDB(db);
    public SelectionController() {
        this.modifyDefaultSelection = modifyDefaultSelection;
        this.modifySelectionInCart = modifySelectionInCart;
    }

    @PostMapping("/ModifyDefaultSelection/{vendorToken}/{singletonId}")
    public ResponseObject runModifyDefaultSelection(@PathVariable String singletonId, @PathVariable String vendorToken,
                                               @RequestBody String selection){

        Selection selection1 = cartrepository.parseSelection(new JSONObject(selection));

        return modifyDefaultSelection.modifyDefaultSelection(vendorToken, singletonId, selection1);

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
