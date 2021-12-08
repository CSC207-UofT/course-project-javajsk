package UseCasesTest.TestBoundaries;

import businessrules.outputboundaries.ResponseObject;
import businessrules.outputboundaries.VendorBoundary;

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
