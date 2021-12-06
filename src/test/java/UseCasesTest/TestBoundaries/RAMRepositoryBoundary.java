package UseCasesTest.TestBoundaries;

import businessrules.outputboundaries.RepositoryBoundary;
import businessrules.outputboundaries.ResponseObject;

public class RAMRepositoryBoundary implements RepositoryBoundary {
    @Override
    public ResponseObject creationFailed(String message) {
        return null;
    }

    @Override
    public ResponseObject queryNotFound(String message) {
        return null;
    }

    @Override
    public ResponseObject modificationFailed(String message) {
        return null;
    }

    @Override
    public ResponseObject invalidInput(String message) {
        return null;
    }
}
