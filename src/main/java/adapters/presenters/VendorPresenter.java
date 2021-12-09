package adapters.presenters;

import businessrules.outputboundaries.ResponseObject;
import businessrules.outputboundaries.VendorBoundary;

/**
 * Presenter for Vendor
 */
public class VendorPresenter implements VendorBoundary {
    /**
     * A method that returns a responseObject containing information to display an error message for vendor not found
     *
     * @return response object containing information to display error message
     */
    @Override
    public ResponseObject vendorNotFound() {
        return new ResponseObject(404, "Invalid vendor token. Could not find vendor.", null);
    }

    /**
     * A method that returns a responseObject containing information to display an error message due to
     * unauthorized access
     *
     * @param message message to add to response object
     * @return response object containing information to display message
     */
    @Override
    public ResponseObject unauthorizedAccess(String message) {
        return new ResponseObject(403, message, null);
        // 403 is http status code for forbidden
    }

    /**
     * A method that returns a responseObject containing information to display an error message
     *
     * @param message error message to add to response object
     * @return response object containing information to display error message
     */
    @Override
    public ResponseObject error(String message) {
        System.out.println(message);
        return new ResponseObject(406, message, null);
        // 406 is http status code for not acceptable
    }

    /**
     * A method that returns a responseObject containing information to display the user token
     *
     * @param token token of user
     * @return responseObject containing information to display token
     */
    @Override
    public ResponseObject displayToken(String token) {
        return new ResponseObject(200, "", token);
    }
}
