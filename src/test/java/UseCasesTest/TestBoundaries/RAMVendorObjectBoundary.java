package UseCasesTest.TestBoundaries;

import businessrules.outputboundaries.ObjectBoundary;
import businessrules.outputboundaries.ResponseObject;
import entities.Vendor;

import java.util.List;

public class RAMVendorObjectBoundary implements ObjectBoundary<Vendor> {
    @Override
    public ResponseObject showObject(Vendor obj) {
        return new ResponseObject(0, "", obj);
    }

    @Override
    public ResponseObject showObjectList(List<Vendor> listToDisp) {
        return null;
    }

    @Override
    public ResponseObject invalidObject(String message) {
        return new ResponseObject(1, message, "");
    }
}
