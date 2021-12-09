package businessrules.addon.inputboundaries;

import businessrules.outputboundaries.ResponseObject;

/**
 * Input boundary for GetAddonTypesInteractor
 */
public interface GetAddonTypes {
    /**
     * Method that returns a response object containing addon types
     *
     * @return response object containing addon types
     */
    ResponseObject getAddonTypes();
}
