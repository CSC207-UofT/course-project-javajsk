package businessrules.addon.inputboundaries;

import businessrules.outputboundaries.ResponseObject;

/**
 * Input boundary for GetAddonInteractor
 */
public interface GetAddon {
    /**
     * Method that returns addon with given id, if it exists in the form of a response object
     *
     * @param id id of addon
     * @return response object containing addon object or error message
     */
    ResponseObject getAddon(String id);
}
