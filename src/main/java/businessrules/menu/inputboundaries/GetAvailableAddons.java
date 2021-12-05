package businessrules.menu.inputboundaries;

import businessrules.outputboundaries.ResponseObject;

/**
 * Input Boundary for GetAvailableAddonsInteractor
 */
public interface GetAvailableAddons {
    /**
     * Method for getting available addons
     * @param shopId the shop id
     * @return a response object
     */
    ResponseObject getAvailableAddons(String shopId);
}
