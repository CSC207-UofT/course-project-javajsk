package UseCasesTest.TestBoundaries;

import businessrules.outputboundaries.ObjectBoundary;
import businessrules.outputboundaries.ResponseObject;
import businessrules.outputboundaries.VendorBoundary;
import entities.Vendor;

import java.util.List;

public class RAMVendorBoundary implements  VendorBoundary {

    @Override
    public ResponseObject vendorNotFound() {
        return null;
    }

    @Override
    public ResponseObject unauthorizedAccess(String message) {
        return null;
    }

    @Override
    public ResponseObject error(String message) {
        return null;
    }

    @Override
    public ResponseObject displayToken(String token) {
        return null;
    }
}
