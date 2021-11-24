package UseCases.Singleton;

import Entities.Interfaces.ISingleton;
import Entities.Interfaces.IVendor;
import UseCases.DataAccessInterfaces.SingletonRepository;
import UseCases.DataAccessInterfaces.AddonRepository;
import UseCases.DataAccessInterfaces.VendorRepository;
import UseCases.OutputBoundary.ErrorPopup;
import java.util.ArrayList;

public class CreateSingletonUseCase implements CreateSingletonInputBoundary {
    SingletonRepository singletonRepository;
    VendorRepository vendorRepository;
    ArrayList<AddonRepository> addonRepository;
    ErrorPopup errorPopup;

    public CreateSingletonUseCase(SingletonRepository sr, VendorRepository vr, ErrorPopup er) {
        this.singletonRepository = sr;
        this.vendorRepository = vr;
        this.errorPopup = er;
    }

    @Override
    public boolean createSingleton(String userToken) {
        IVendor vendor = vendorRepository.getVendorFromToken(userToken);
        if(vendor != null) {
            return true;
        }
        return false;
    }
}
