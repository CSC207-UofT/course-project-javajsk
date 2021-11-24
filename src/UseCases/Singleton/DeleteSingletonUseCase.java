package UseCases.Singleton;

import Entities.Interfaces.ISingleton;
import Entities.Interfaces.IVendor;
import UseCases.DataAccessInterfaces.SingletonRepository;
import UseCases.DataAccessInterfaces.VendorRepository;
import UseCases.OutputBoundary.ErrorPopup;
import UseCases.OutputBoundary.SingletonModel;

public class DeleteSingletonUseCase implements DeleteSingletonInputBoundary {
    SingletonRepository singletonRepository;
    VendorRepository vendorRepository;
    ErrorPopup errorPopup;

    @Override
    public void deleteSingleton(String userToken, String singletonID) {
        IVendor vendor = vendorRepository.getVendorFromToken(userToken);
        if(vendor != null) {
            ISingleton singleton = singletonRepository.getSingleton(singletonID);
            if(singleton != null) {
                singletonRepository.deleteSingleton(singletonID);
            } else { errorPopup.displayError("Invalid ID"); }
        }
        errorPopup.displayError("User is not Vendor");
    }
}
