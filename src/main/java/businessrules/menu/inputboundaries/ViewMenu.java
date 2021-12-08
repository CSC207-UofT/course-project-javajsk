package businessrules.menu.inputboundaries;

import businessrules.outputboundaries.ResponseObject;

/**
 * Input Boundary for  ViewMenuInteractor
 */
public interface ViewMenu {
    /**
     * Method for getting the menu of a shop
     * @param shopId the shop id
     * @return a response object
     */
    ResponseObject viewMenu(String shopId);
}
