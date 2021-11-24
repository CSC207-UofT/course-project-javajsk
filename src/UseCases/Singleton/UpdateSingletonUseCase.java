package UseCases.Singleton;

import Entities.Interfaces.ISingleton;
import Entities.Regular.RegularSingleton;
import Entities.Interfaces.IVendor;
import UseCases.DataAccessInterfaces.SingletonRepository;
import UseCases.DataAccessInterfaces.VendorRepository;
import UseCases.OutputBoundary.ErrorPopup;
import UseCases.OutputBoundary.SingletonModel;

public class UpdateSingletonUseCase implements UpdateSingletonInputBoundary {
    SingletonRepository singletonRepository;
    VendorRepository vendorRepository;
    SingletonModel singletonModel;
    ErrorPopup errorPopup;

    @Override
    public boolean updateSingleton(String userToken, String singletonID, RegularSingleton newsingleton) {
        IVendor vendor = vendorRepository.getVendorFromToken(userToken);
        if(vendor != null) {
            ISingleton singleton = singletonRepository.getSingleton(singletonID);
            if(singleton != null) {
                singleton.setSingleton(newsingleton);
                singletonModel.updateSingleton(singleton);
                return singletonRepository.save(singleton);
            } else { errorPopup.displayError("Invalid ID"); }
        }
        errorPopup.displayError("User is not Vendor");
        return false;
    }
}
