package businessrules.outputboundaries;

/**
 * The output boundary for customer entities
 */
public interface VendorBoundary {
    /**
     * Method for getting information when a vendor is not found
     * @return a response object
     */
    ResponseObject vendorNotFound();

    /**
     * Method for getting information when attempting to access unauthorized data
     * @param message error message
     * @return a response object
     */
    ResponseObject unauthorizedAccess(String message);

    /**
     * Method for getting information when an error occurs
     * @param message error message
     * @return a response object
     */
    ResponseObject error(String message);

    /**
     * Method for displaying a vendor
     * @param token vendor token
     * @return a response object
     */
    ResponseObject displayToken(String token);
}
