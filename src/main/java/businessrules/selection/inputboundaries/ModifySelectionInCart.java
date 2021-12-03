package businessrules.selection.inputboundaries;

import businessrules.outputboundaries.ResponseObject;
import entities.Selection;

public interface ModifySelectionInCart {
    ResponseObject modifySelection(String userToken, String foodId,Selection[] originalSelection,
                                   Selection[] selections);
}
