package businessrules.outputboundaries;

public interface VendorBoundary {
    ResponseObject vendorNotFound();
    ResponseObject unauthorizedAccess(String message);
    ResponseObject error(String message);
    ResponseObject displayToken(String token);
}
