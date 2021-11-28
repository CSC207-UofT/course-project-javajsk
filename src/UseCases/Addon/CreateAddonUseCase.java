package UseCases.Addon;

import Entities.Interfaces.IAddon;
import Entities.Interfaces.IVendor;
import Entities.Regular.RegularAddon;

import UseCases.DataAccessInterfaces.AddonRepository;
import UseCases.Addon.ErrorPopup;
import UseCases.DataAccessInterfaces.VendorRepository;

import java.util.ArrayList;

public class CreateAddonUseCase implements CreateAddonInputBoundary {
    AddonRepository addonRepository;
    VendorRepository vendorRepository;
    ErrorPopup errorPopup;

    public Boolean createAddon(String vendorToken, String name, String description, float price,
                               ArrayList<Integer> types, boolean availability, String ID) {
        IVendor vendor = vendorRepository.getVendorFromToken(vendorToken);
        if (vendor != null) {
            IAddon addon = new RegularAddon(name, description, price, types, availability, ID);
            addonRepository.createAddon(addon);
            return addonRepository.save(addon);
        }

        errorPopup.displayError("User is not a vendor");
        return false;
    }
}
