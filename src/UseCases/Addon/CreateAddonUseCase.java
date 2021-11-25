package UseCases.Addon;

import Entities.Interfaces.IAddon;
import Entities.RegularAddon;
import UseCases.DataAccessInterfaces.AddonRepository;
import UseCases.Addon.ErrorPopup;

public class CreateAddonUseCase implements CreateAddonInputBoundary {
    AddonRepository addonRepository;
    VendorRepository vendorRepository;
    ErrorPopup errorPopup;

    public boolean createAddon(String vendorToken, String id, String name, String description, float price) {
        IVendor vendor = vendorRepository.getVendorfromToken(vendorToken);
        if (vendor != null) {
            RegularAddon addon = new RegularAddon(id, name, description, price);
            AddonRespository.createAddon(addon);
            return true;
        }
        return AddonRepository.save(addon);
        ErrorPopup.displayError("User is not a vendor");
        return false;
    }
}
