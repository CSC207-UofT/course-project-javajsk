package businessrules.outputboundaries;

/**
 * Output boundary for vendor
 */
public interface VendorBoundary {
    /**
     * A method that returns a responseObject containing information to display an error message for vendor not found
     *
     * @return response object containing information to display error message
     */
    ResponseObject vendorNotFound();

    /**
     * A method that returns a responseObject containing information to display an error message due to
     * unauthorized access
     *
     * @param message message to add to response object
     * @return response object containing information to display message
     */
    ResponseObject unauthorizedAccess(String message);

    /**
     * A method that returns a responseObject containing information to display an error message
     *
     * @param message error message to add to response object
     * @return response object containing information to display error message
     */
    ResponseObject error(String message);

    /**
     * A method that returns a responseObject containing information to display the user token
     *
     * @param token token of user
     * @return responseObject containing information to display token
     */
    ResponseObject displayToken(String token);
}
