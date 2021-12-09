package businessrules.vendor.usecases;

import businessrules.dai.Hasher;
import businessrules.dai.VendorRepository;
import businessrules.outputboundaries.VendorBoundary;
import businessrules.outputboundaries.RepositoryBoundary;
import businessrules.outputboundaries.ResponseObject;
import businessrules.vendor.inputboundaries.VendorLogin;

/**
 * Vendor Login use case
 */
public class VendorLoginInteractor implements VendorLogin {
    /**
     * The Vendor repository.
     */
    VendorRepository vendorRepository;
    /**
     * The Vendor boundary.
     */
    VendorBoundary vendorBoundary;
    /**
     * The Repository boundary.
     */
    RepositoryBoundary repositoryBoundary;
    /**
     * The Hasher.
     */
    Hasher hasher;


    /**
     * Instantiates a new Vendor login interactor.
     *
     * @param vendorRepository   the vendor repository
     * @param vendorBoundary     the vendor boundary
     * @param repositoryBoundary the repository boundary
     * @param hasher             the hasher
     */
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
     * @return response object containing the token of the Vendor or an error message
     */
    @Override
    public ResponseObject login(String username, String password) {

        String hashedPassword = hasher.hash(password);
        String token = vendorRepository.authenticateUser(username, hashedPassword);

        if (token == null) {
            return repositoryBoundary.queryNotFound("Incorrect username or password. Please try again.");
        }

        return vendorBoundary.displayToken(token);
    }
}
