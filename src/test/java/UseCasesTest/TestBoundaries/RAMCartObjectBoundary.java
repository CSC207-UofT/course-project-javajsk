package UseCasesTest.TestBoundaries;

import businessrules.outputboundaries.ObjectBoundary;
import businessrules.outputboundaries.ResponseObject;
import entities.Cart;

import java.util.List;

public class RAMCartObjectBoundary implements ObjectBoundary<Cart> {
    @Override
    public ResponseObject showObject(Cart obj) {
        return new ResponseObject(0, "",obj);
    }
    @Override
    public ResponseObject showObjectList(List<Cart> listToDisp) {
        return null;
    }

    @Override
    public ResponseObject invalidObject(String message) {
        return new ResponseObject(1, message, "");
    }
}
