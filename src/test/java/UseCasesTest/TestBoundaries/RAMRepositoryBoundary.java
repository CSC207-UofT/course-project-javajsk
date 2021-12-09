package UseCasesTest.TestBoundaries;

import businessrules.outputboundaries.RepositoryBoundary;
import businessrules.outputboundaries.ResponseObject;

public class RAMRepositoryBoundary implements RepositoryBoundary {
    @Override
    public ResponseObject creationFailed(String message) {
        ResponseObject responseObject = new ResponseObject(1, message, "");
        return responseObject;
    }

    @Override
    public ResponseObject queryNotFound(String message) {
        ResponseObject responseObject = new ResponseObject(1, message, "");
        return responseObject;
    }

    @Override
    public ResponseObject modificationFailed(String message) {
        ResponseObject responseObject = new ResponseObject(1, message, "");
        return responseObject;
    }

    @Override
    public ResponseObject invalidInput(String message) {
        ResponseObject responseObject = new ResponseObject(1, message, "");
        return responseObject;
    }
}
