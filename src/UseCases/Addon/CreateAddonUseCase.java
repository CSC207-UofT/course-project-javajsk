package UseCases.Addon;

import Entities.Interfaces.IAddon;
import Entities.Interfaces.IVendor;
import Entities.Regular.RegularAddon;

import UseCases.DataAccessInterfaces.AddonRepository;
import UseCases.Addon.ErrorPopup;
import UseCases.DataAccessInterfaces.VendorRepository;

import java.util.ArrayList;

/**
 * Use Case for creating a new addon
 */
public class CreateAddonUseCase implements CreateAddonInputBoundary {
    AddonRepository addonRepository;
    VendorRepository vendorRepository;
    ErrorPopup errorPopup;

    /**
     * Creates a new addon and returns whether it was successfully done
     * @param vendorToken token of current vendor logged in
     * @param name name of addon
     * @param price price of addon
     * @param types list of types that addon is
     * @param availability whether addon is available
     * @param id id of addon
     * @return whether addon was successfully created
     */
    public Boolean createAddon(String vendorToken, String name, float price,
                               ArrayList<Integer> types, boolean availability, String id) {
        IVendor vendor = vendorRepository.getVendorFromToken(vendorToken);
        if (vendor != null) {
            IAddon addon = new RegularAddon(name, price, types, availability, id);
            addonRepository.createAddon(addon);
            return addonRepository.save(addon);
        }

        errorPopup.displayError("User is not a vendor");
        return false;
    }
}
