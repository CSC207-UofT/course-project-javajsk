package businessrules.selection.inputboundaries;

import businessrules.outputboundaries.ResponseObject;
import entities.Selection;

public interface ModifyDefaultSelection {
    ResponseObject modifyDefaultSelection(String vendorToken, String singletonId, Selection selection);
}
