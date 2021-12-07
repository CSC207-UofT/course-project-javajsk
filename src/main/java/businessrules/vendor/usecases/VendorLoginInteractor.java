package businessrules.vendor.usecases;

import businessrules.dai.Hasher;
import businessrules.dai.VendorRepository;
import businessrules.outputboundaries.VendorBoundary;
import businessrules.outputboundaries.RepositoryBoundary;
import businessrules.outputboundaries.ResponseObject;
import businessrules.vendor.inputboundaries.VendorLogin;


public class VendorLoginInteractor implements VendorLogin {
    VendorRepository vendorRepository;
    VendorBoundary vendorBoundary;
    RepositoryBoundary repositoryBoundary;
    Hasher hasher;


    public VendorLoginInteractor(VendorRepository vendorRepository,
                                 VendorBoundary vendorBoundary, RepositoryBoundary
                                         repositoryBoundary, Hasher hasher) {
        this.vendorRepository = vendorRepository;
        this.vendorBoundary = vendorBoundary;
        this.repositoryBoundary = repositoryBoundary;
        this.hasher = hasher;
    }

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
            return repositoryBoundary.queryNotFound("Incorrect username or password. Please try again.");
        }

        return vendorBoundary.displayToken(token);
    }
}
