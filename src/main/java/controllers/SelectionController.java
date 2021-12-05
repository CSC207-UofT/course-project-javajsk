package controllers;

import businessrules.outputboundaries.ResponseObject;
import businessrules.selection.inputboundaries.ModifyDefaultSelection;
import businessrules.selection.inputboundaries.ModifySelectionInCart;
import businessrules.selection.usecases.ModifyDefaultSelectionInteractor;
import entities.Selection;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

public class SelectionController {
    ModifyDefaultSelection modifyDefaultSelection;
    ModifySelectionInCart modifySelectionInCart;

    public SelectionController(ModifyDefaultSelection modifyDefaultSelection,
                               ModifySelectionInCart modifySelectionInCart) {
        this.modifyDefaultSelection = modifyDefaultSelection;
        this.modifySelectionInCart = modifySelectionInCart;
    }

    @PostMapping("/ModifyDefaultSelection/{vendorToken}/{singletonId}")
    public ResponseObject runModifyDefaultSelection(@PathVariable String singletonId, @PathVariable String vendorToken,
                                               @RequestBody Selection selection){
        return modifyDefaultSelection.modifyDefaultSelection(vendorToken, singletonId, selection);
    }

    @PostMapping("/ModifySelectionInCart/{vendorToken}/{foodId}")
    public ResponseObject runModifySelectionInCart(@PathVariable String foodId, @PathVariable String vendorToken,
                                               @RequestBody Selection[] original, @RequestBody Selection[] new_singletons){
        return modifySelectionInCart.modifySelection(vendorToken, foodId, original, new_singletons);
    }
}
