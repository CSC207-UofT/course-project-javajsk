package UseCasesTest.TestBoundaries;

import businessrules.outputboundaries.ObjectBoundary;
import businessrules.outputboundaries.ResponseObject;
import entities.Menu;

import java.util.List;

public class RAMMenuObjectBoundary implements ObjectBoundary<Menu> {

    @Override
    public ResponseObject showObject(Menu obj) {
        return new ResponseObject(0, "",obj);
    }

    @Override
    public ResponseObject showObjectList(List<Menu> listToDisp) {
        return new ResponseObject(0, "Test Works", listToDisp);
    }

    @Override
    public ResponseObject invalidObject(String message) {
        return new ResponseObject(0,message,"");
    }
}
