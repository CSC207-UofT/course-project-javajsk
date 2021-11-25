package UseCases.Addon;

import Entities.Interfaces.IAddon;
import Entities.Interfaces.IVendor;
import UseCases.DataAccessInterfaces.AddonRepository;
import UseCases.DataAccessInterfaces.VendorRepository;

public class DeleteAddonUseCase implements DeleteAddonInputBoundary{
    ErrorPopup errorPopup;
    VendorRepository vendorRepository;
    AddonRepository addonRepository;

    @Override
    public boolean deleteAddon(String userToken, String AddonID) {
        IVendor vendor = (IVendor) vendorRepository.getUserFromToken(userToken);
        if(vendor != null) {
            IAddon addon = addonRepository.getAddon(AddonID);
            if(addon != null) {
                addonRepository.deleteAddon(AddonID);
                return true;
            } else { errorPopup.displayError("Invalid ID"); }
        }
        errorPopup.displayError("User is not Vendor");
        return false;
    }


    }
