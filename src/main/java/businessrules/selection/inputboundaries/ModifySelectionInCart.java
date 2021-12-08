package businessrules.selection.inputboundaries;

import businessrules.outputboundaries.ResponseObject;
import entities.Selection;

/**
 * Input boundary for ModifySelectionInCartInteractor
 */
public interface ModifySelectionInCart {
    /**
     * Modifies the selections in a cart
     *
     * @param userToken         token representing the user that owns the cart
     * @param foodId            the food that has the selections
     * @param originalSelection The original selection
     * @param selections        The selection that will replace the original selection
     * @return a response object
     */
    ResponseObject modifySelection(String userToken, String foodId,Selection[] originalSelection,
                                   Selection[] selections);
}
