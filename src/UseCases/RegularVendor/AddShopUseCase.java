package UseCases.RegularVendor;

import Entities.Interfaces.IShop;
import Entities.Regular.RegularVendor;
import UseCases.DataAccessInterfaces.VendorRepository;
import UseCases.OutputBoundary.ErrorPopup;
import UseCases.OutputBoundary.VendorModel;

public class AddShopUseCase implements AddShopInputBoundary {
    VendorRepository vendorRepository;
    ErrorPopup errorDisplayer;
    VendorModel vendorModel;

    public AddShopUseCase(VendorRepository vendorRepository){
        this.vendorRepository = vendorRepository;
    }

    /**
     *
     * @param shop The shop that we want to add to the vendor's shoplist.
     * @param token The token of the vendor.
     * @return A boolean of whether the method successfully added the shop.
     */
    @Override
    public boolean addShop(IShop shop, String token) {
        RegularVendor curr_ven = vendorRepository.getUserFromToken(token);
        if (curr_ven != null){
            curr_ven.addShop(shop);
            vendorModel.updateVendor(curr_ven);
            vendorRepository.save(curr_ven);
            return true;
        }
        errorDisplayer.displayError("User must be logged in as vendor");
        return false;
    }
}
