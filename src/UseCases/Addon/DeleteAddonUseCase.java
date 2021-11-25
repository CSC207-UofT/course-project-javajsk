package UseCases.Addon;
import Entities.Interfaces.IShop;
import Entities.RegularAddon;
import Entities.Interfaces.IAddon;
import Entities.User;
import Entities.RegularCart;
import Entities.FoodTruck;
import Entities.Menu;
import UseCases.Addon.ErrorPopup;

public class DeleteAddonUseCase implements DeleteAddonInputBoundary{
    ErrorPopup errorPopup;
    @Override
    public boolean deleteAddon(String userToken, String AddonID) {
        IVendor vendor = vendorRepository.getVendorFromToken(userToken);
        if(vendor != null) {
            IAddon addon = AddonRepository.getAddon(AddonID);
            if(addon != null) {
                AddonRepository.deleteAddon(AddonID);
                return true;
            } else { errorPopup.displayError("Invalid ID"); }
        }
        errorPopup.displayError("User is not Vendor");
        return false;
    }


    }
