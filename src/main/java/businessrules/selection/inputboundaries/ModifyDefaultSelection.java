package businessrules.selection.inputboundaries;

import businessrules.outputboundaries.ResponseObject;
import entities.Selection;

/**
 * Input boundary for ModifyDefaultSelectionInteractor
 */
public interface ModifyDefaultSelection {
    /**
     * Methods that modifies the default selection of a Singleton
     * by replacing it with a new one. Should only be called by a Vendor.
     *
     * @param vendorToken token of the Vendor
     * @param singletonId id of Singleton to be modified
     * @param selection   the new default selection
     * @return response object
     */
    ResponseObject modifyDefaultSelection(String vendorToken, String singletonId, Selection selection);
}
