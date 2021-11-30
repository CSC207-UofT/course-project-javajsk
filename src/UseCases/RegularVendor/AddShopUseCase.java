package UseCases.RegularVendor;

import Entities.Interfaces.IShop;
import Entities.Regular.RegularVendor;
import UseCases.DataAccessInterfaces.FoodTruckRepository;
import UseCases.DataAccessInterfaces.VendorRepository;
import UseCases.OutputBoundary.ErrorPopup;
import UseCases.OutputBoundary.VendorModel;

public class AddShopUseCase implements AddShopInputBoundary {
    VendorRepository vendorRepository;
    FoodTruckRepository foodTruckRepository;
    ErrorPopup errorDisplayer;
    VendorModel vendorModel;

    public AddShopUseCase(VendorRepository vendorRepository){
        this.vendorRepository = vendorRepository;
    }

    /**
     *
     * @param shopId The id of the shop that we want to add to the vendor's shoplist.
     * @param token The token of the vendor.
     * @return A boolean of whether the method successfully added the shop.
     */
    @Override
    public boolean addShop(String shopId, String token) {
        RegularVendor curr_ven = vendorRepository.getUserFromToken(token);
        IShop shop = foodTruckRepository.getFoodTruck(shopId);
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
