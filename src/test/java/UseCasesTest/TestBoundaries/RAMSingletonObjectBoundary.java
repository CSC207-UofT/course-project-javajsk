package UseCasesTest.TestBoundaries;

import businessrules.outputboundaries.ObjectBoundary;
import businessrules.outputboundaries.ResponseObject;
import entities.Singleton;

import java.util.List;

public class RAMSingletonObjectBoundary implements ObjectBoundary<Singleton> {

    @Override
    public ResponseObject showObject(Singleton obj) {
        return new ResponseObject(0, "", obj);
    }

    @Override
    public ResponseObject showObjectList(List<Singleton> listToDisp) {
        return null;
    }

    @Override
    public ResponseObject invalidObject(String message) {
        return new ResponseObject(1, message, "");
    }
}
