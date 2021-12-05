package UseCasesTest.TestBoundaries;

import businessrules.outputboundaries.ObjectBoundary;
import businessrules.outputboundaries.ResponseObject;

import java.util.List;
import entities.Addon;
public class RAMAddonObjectBoundary implements ObjectBoundary<Addon> {


    @Override
    public ResponseObject showObject(Addon obj) {
        return null;
    }

    @Override
    public ResponseObject showObjectList(List<Addon> listToDisp) {
        return null;
    }

    @Override
    public ResponseObject invalidObject(String message) {
        return null;
    }
}
