package UseCasesTest.TestBoundaries;

import businessrules.outputboundaries.CustomerBoundary;
import businessrules.outputboundaries.ResponseObject;

public class RAMCustomerBoundary implements CustomerBoundary {
    @Override
    public ResponseObject displayToken(String token) {
        ResponseObject responseObject = new ResponseObject(0, "", token);
        return null;
    }

    @Override
    public ResponseObject error(String message) {
        ResponseObject responseObject = new ResponseObject(1, message, "");
        return responseObject;
    }

    @Override
    public ResponseObject unauthorizedAccess(String message) {
        ResponseObject responseObject = new ResponseObject(1, message, "");
        return responseObject;
    }
}
