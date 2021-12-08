package UseCasesTest.TestBoundaries;

import businessrules.outputboundaries.CustomerBoundary;
import businessrules.outputboundaries.ResponseObject;

public class RAMCustomerBoundary implements CustomerBoundary {
    /**
     * A method that returns a responseObject containing information to display the user token
     *
     * @param token token of user
     * @return responseObject containing information to display token
     */
    @Override
    public ResponseObject displayToken(String token) {
        return new ResponseObject(0, token, "");
    }

    /**
     * A method that returns a responseObject containing information to display an error message
     *
     * @param message error message to add to response object
     * @return response object containing information to display error message
     */
    @Override
    public ResponseObject error(String message) {
        return new ResponseObject(0, message, "");
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
        return new ResponseObject(0, message, "");
    }
}