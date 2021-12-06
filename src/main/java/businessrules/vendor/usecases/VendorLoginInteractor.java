package businessrules.vendor.usecases;

import businessrules.dai.Hasher;
import businessrules.dai.VendorRepository;
import businessrules.outputboundaries.VendorBoundary;
import businessrules.outputboundaries.RepositoryBoundary;
import businessrules.outputboundaries.ResponseObject;
import businessrules.vendor.inputboundaries.VendorLogin;

import javax.sound.midi.Soundbank;

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
