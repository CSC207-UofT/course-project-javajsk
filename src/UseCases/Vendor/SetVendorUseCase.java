package UseCases.Vendor;

import Entities.Vendor;
import UseCases.DataAccessInterfaces.VendorRepository;

public class SetVendorUseCase implements SetVendorInputBoundary{
    VendorRepository vendorRepository;
    ErrorPopup errorDisplayer;

    public SetVendorUseCase(VendorRepository vendorRepository){
        this.vendorRepository = vendorRepository;
    }

    @Override
    public boolean setVendor(Vendor vendor, String token){
       Vendor curr_ven = vendorRepository.getUserFromToken(token);
       if (curr_ven != null){
           vendorRepository.getUserFromToken(token).updateVendor(vendor);
           return true;
       }
        errorDisplayer.displayError("User must be logged in as vendor");
        return false;
    }

}
