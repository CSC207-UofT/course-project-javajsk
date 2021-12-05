package businessrules.outputboundaries;

/**
*Interface for customer output boundaries
*/
public interface CustomerBoundary {
    /**
     * A method that returns a responseObject containing information to display the user token
     * @param token token of user
     * @return responseObject containing information to display token
     */
    ResponseObject displayToken(String token);

    /**
     * A method that returns a responseObject containing information to display an error message
     * @param message error message to add to response object
     * @return response object containing information to display error message
     */
    ResponseObject error(String message);

    /**
     * A method that returns a responseObject containing information to display an error message due to
     * unauthorized access
     * @param message message to add to response object
     * @return response object containing information to display message
     */
    ResponseObject unauthorizedAccess(String message);
}
