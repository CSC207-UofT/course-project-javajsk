package businessrules.menu.inputboundaries;

import businessrules.outputboundaries.ResponseObject;

/**
 * Input Boundary for GetAvailableFoodsInteractor
 */
public interface GetAvailableFoods {
    /**
     * Method for getting available foods from a shop
     *
     * @param shopId the shop id
     * @return a response object
     */
    ResponseObject getAvailableFoods(String shopId);
}
