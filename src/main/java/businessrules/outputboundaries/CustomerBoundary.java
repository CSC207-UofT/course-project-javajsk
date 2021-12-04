package businessrules.outputboundaries;

/**
 * The output boundary for customer
 */
public interface CustomerBoundary {
    /**
     * Method for displaying a customer
     * @param token customer token
     * @return a response object
     */
    ResponseObject displayToken(String token);

    /**
     * Method for getting information when an error occurs
     * @param message error message
     * @return a response object
     */
    ResponseObject error(String message);

    /**
     * Method for getting information when attempting to access unauthorized data
     * @param message error message
     * @return a response object
     */
    ResponseObject unauthorizedAccess(String message);
}
