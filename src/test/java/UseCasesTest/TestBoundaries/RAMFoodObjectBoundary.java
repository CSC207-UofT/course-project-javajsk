package UseCasesTest.TestBoundaries;

import businessrules.outputboundaries.ObjectBoundary;
import businessrules.outputboundaries.ResponseObject;
import entities.Food;

import java.util.List;

public class RAMFoodObjectBoundary implements ObjectBoundary<Food> {
    @Override
    public ResponseObject showObject(Food obj) {
        return new ResponseObject(0, "", obj);
    }

    @Override
    public ResponseObject showObjectList(List<Food> listToDisp) {
        return null;
    }

    @Override
    public ResponseObject invalidObject(String message) {
        return new ResponseObject(0, message, "");
    }
}
