package businessrules.shop.inputboundaries;

import businessrules.outputboundaries.ResponseObject;

/**
 * Input boundary for ViewAllShopsInteractor
 */
public interface ViewAllShops {

    /**
     * Method returns response object containing all the shops in the database
     *
     * @return response object
     */
    ResponseObject viewAllShops();
}
