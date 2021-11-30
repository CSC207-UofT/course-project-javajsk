package UseCases.Addon;

import Entities.Interfaces.IVendor;
import Entities.Interfaces.IAddon;

import UseCases.Addon.ErrorPopup;
import UseCases.DataAccessInterfaces.AddonRepository;
import UseCases.DataAccessInterfaces.VendorRepository;

/**
 * Use Case for deleting an addon
 */
public class DeleteAddonUseCase implements DeleteAddonInputBoundary{
    ErrorPopup errorPopup;
    VendorRepository vendorRepository;
    AddonRepository addonRepository;

    /**
     * A method that deleted a given Addon
     * @param userToken token of current user
     * @param AddonId id of addon to delete
     * @return whether addon was successfully deleted
     */
    @Override
    public boolean deleteAddon(String userToken, String AddonId) {
        IVendor vendor = (IVendor) vendorRepository.getUserFromToken(userToken);
        if(vendor != null) {
            IAddon addon = addonRepository.getAddon(AddonId);
            if(addon != null) {
                addonRepository.deleteAddon(AddonId);
                return true;
            } else { errorPopup.displayError("Invalid ID"); }
        }
        errorPopup.displayError("User is not Vendor");
        return false;
    }
}
