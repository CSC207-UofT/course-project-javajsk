package businessrules.selection.inputboundaries;

import businessrules.outputboundaries.ResponseObject;
import EntitiesUnitTest.Selection;

public interface ModifyDefaultSelection {
    ResponseObject modifyDefaultSelection(String vendorToken, String singletonId, Selection selection);
}
