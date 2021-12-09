package UseCasesTest.TestBoundaries;

import businessrules.outputboundaries.ObjectBoundary;
import businessrules.outputboundaries.ResponseObject;
import entities.Customer;

import java.util.List;

public class RAMCustomerObjectBoundary implements ObjectBoundary<Customer> {
    @Override
    public ResponseObject showObject(Customer obj) {
        ResponseObject responseObject = new ResponseObject(0, "", obj);
        return responseObject;
    }

    @Override
    public ResponseObject showObjectList(List<Customer> listToDisp) {
        return null;
    }

    @Override
    public ResponseObject invalidObject(String message) {
        ResponseObject responseObject = new ResponseObject(1, message, "");
        return responseObject;
    }
}
