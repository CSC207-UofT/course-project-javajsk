package businessrules.vendor.usecases;

import businessrules.vendor.inputboundaries.SignUpVendorInputBoundary;
import businessrules.dai.VendorRepository;
import businessrules.dai.Hasher;
import businessrules.outputboundary.VendorModel;
import businessrules.outputboundary.ErrorModel;
import entities.*;
import org.json.JSONObject;

public class SignUpVendorUseCase implements SignUpVendorInputBoundary {
    VendorRepository vendorRepository;
    ErrorModel errorHandler;
    Hasher passwordHasher;
    VendorModel vendorModel;

    public SignUpVendorUseCase(VendorRepository vendorRepository, ErrorModel errorHandler, Hasher passwordHasher,
                               VendorModel vendorModel) {
        this.vendorRepository = vendorRepository;
        this.errorHandler = errorHandler;
        this.passwordHasher = passwordHasher;
        this.vendorModel = vendorModel;
    }

    @Override
    public boolean signUp(JSONObject data) {
        String username = data.getString("username");
        String password = data.getString("password");
        String passwordConfirmation = data.getString("passwordConfirmation");


        if (!vendorRepository.isUsernameUnique(username)) {
            errorHandler.displayError("Username is already taken!");
            return false;
        }

        if (!password.equals(passwordConfirmation)) {
            errorHandler.displayError("Passwords must match!");
            return false;
        }

        // Strategy design pattern.
        String hashedPassword = passwordHasher.hash(password);

        Vendor vendor = new Vendor("", username, password, null);

        if (!vendorRepository.createVendor(username, hashedPassword)) {
            errorHandler.displayError("Unable to save Vendor information in repository.");
        }

        vendorModel.displayVendor(vendor.jsonify());
        return true;
    }
}
