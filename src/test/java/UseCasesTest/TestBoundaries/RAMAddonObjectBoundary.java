package UseCasesTest.TestBoundaries;

import businessrules.outputboundaries.ObjectBoundary;
import businessrules.outputboundaries.ResponseObject;

import java.util.List;
import entities.Addon;
import entities.Cart;

public class RAMAddonObjectBoundary implements ObjectBoundary<Addon> {


    @Override
    public ResponseObject showObject(Addon obj) {
        return new ResponseObject(0, "",obj);
    }

    @Override
    public ResponseObject showObjectList(List<Addon> listToDisp) {
        return new ResponseObject(0,"Test Works", listToDisp);
    }

    @Override
    public ResponseObject invalidObject(String message) {
            return new ResponseObject(1, message, "");
    }
}
