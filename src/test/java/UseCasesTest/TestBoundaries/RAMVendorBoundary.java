package UseCasesTest.TestBoundaries;

import businessrules.outputboundaries.ObjectBoundary;
import businessrules.outputboundaries.ResponseObject;
import businessrules.outputboundaries.VendorBoundary;
import entities.Vendor;

import java.util.List;

public class RAMVendorBoundary implements  VendorBoundary {

    @Override
    public ResponseObject vendorNotFound() {
        return new ResponseObject(0, "Vendor not found", "");
    }

    @Override
    public ResponseObject unauthorizedAccess(String message) {
        return new ResponseObject(0, message, "");
    }

    @Override
    public ResponseObject error(String message) {
        return new ResponseObject(0, message, "");
    }

    @Override
    public ResponseObject displayToken(String token) {
        return new ResponseObject(0, token, "");
    }
}
