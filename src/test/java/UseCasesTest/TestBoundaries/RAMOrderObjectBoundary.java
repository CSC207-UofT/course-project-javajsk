package UseCasesTest.TestBoundaries;

import businessrules.outputboundaries.ObjectBoundary;
import businessrules.outputboundaries.ResponseObject;
import entities.Order;

import java.util.List;

public class RAMOrderObjectBoundary implements ObjectBoundary<Order> {
    @Override
    public ResponseObject showObject(Order obj) {
        ResponseObject responseObject = new ResponseObject(0, "", obj);
        return responseObject;
    }

    @Override
    public ResponseObject showObjectList(List<Order> listToDisp) {
        ResponseObject responseObject = new ResponseObject(0, "", listToDisp);
        return responseObject;
    }

    @Override
    public ResponseObject invalidObject(String message) {
        ResponseObject responseObject = new ResponseObject(1, message, "");
        return responseObject;
    }
}
