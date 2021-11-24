package UseCases.Singleton;

import Entities.Interfaces.IAddon;
import Entities.Interfaces.ISingleton;
import Entities.Regular.RegularSingleton;
import Entities.Interfaces.IVendor;
import UseCases.DataAccessInterfaces.SingletonRepository;
import UseCases.DataAccessInterfaces.AddonRepository;
import UseCases.DataAccessInterfaces.VendorRepository;
import UseCases.OutputBoundary.SingletonModel;
import UseCases.OutputBoundary.ErrorPopup;
import java.util.ArrayList;
import java.util.List;

public class CreateSingletonUseCase implements CreateSingletonInputBoundary {
    SingletonRepository singletonRepository;
    VendorRepository vendorRepository;
    SingletonModel singletonModel;
    ErrorPopup errorPopup;

    @Override
    public boolean createSingleton(String userToken, String id, String name,
                                   String description, float price, List<IAddon> add_ons) {
        IVendor vendor = vendorRepository.getVendorFromToken(userToken);
        if(vendor != null) {
            RegularSingleton singleton = new RegularSingleton(id, name, description, price, add_ons);
            singletonRepository.createSingleton(singleton);

            // TODO: make sure singleton is unique (somehow)

            singletonModel.displaySingleton(singleton);
            return singletonRepository.save(singleton);
        }
        errorPopup.displayError("User is not Vendor");
        return false;
    }
}