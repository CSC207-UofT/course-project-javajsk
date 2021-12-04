package businessrules.selection.inputboundaries;

import businessrules.outputboundaries.ResponseObject;
import EntitiesUnitTest.Selection;

public interface ModifySelectionInCart {
    ResponseObject modifySelection(String userToken, String foodId,Selection[] originalSelection,
                                   Selection[] selections);
}
