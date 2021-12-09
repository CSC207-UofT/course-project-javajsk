package UseCasesTest.TestBoundaries;

import businessrules.outputboundaries.ObjectBoundary;
import businessrules.outputboundaries.ResponseObject;
import entities.Shop;

import java.util.List;

public class RAMShopObjectBoundary implements ObjectBoundary<Shop> {
    @Override
    public ResponseObject showObject(Shop obj) {
        return new ResponseObject(0, "", obj);
    }

    @Override
    public ResponseObject showObjectList(List<Shop> listToDisp) {
        return null;
    }

    @Override
    public ResponseObject invalidObject(String message) {
        return new ResponseObject(1, message, "");
    }
}
