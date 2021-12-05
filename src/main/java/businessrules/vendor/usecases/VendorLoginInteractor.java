package businessrules.vendor.usecases;

import businessrules.dai.Hasher;
import businessrules.dai.VendorRepository;
import businessrules.outputboundaries.VendorBoundary;
import businessrules.outputboundaries.RepositoryBoundary;
import businessrules.outputboundaries.ResponseObject;
import businessrules.vendor.inputboundaries.VendorLogin;

/**
 * Use case for logging a Vendor in
 */
public class VendorLoginInteractor implements VendorLogin {
    VendorRepository vendorRepository;
    VendorBoundary vendorBoundary;
    RepositoryBoundary repositoryBoundary;
    Hasher hasher;

    /**
     * Method that logs in a Vendor
     *
     * @param username username of the Vendor
     * @param password password of the Vendor
     * @return         the token of the Vendor
     */
    @Override
    public ResponseObject login(String username, String password) {
        String hashedPassword = hasher.hash(password);
        String token = vendorRepository.authenticateUser(username, hashedPassword);
        if(token == null){
            return repositoryBoundary.queryNotFound("Unable to locate such user.");
        }

        return vendorBoundary.displayToken(token);
    }
}
