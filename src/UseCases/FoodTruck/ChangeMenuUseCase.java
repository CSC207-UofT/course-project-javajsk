package UseCases.FoodTruck;

import Entities.Interfaces.IShop;
import Entities.Interfaces.IVendor;
import Entities.Menu;
import UseCases.DataAccessInterfaces.ShopRepository;
import UseCases.DataAccessInterfaces.VendorRepository;
import UseCases.OutputBoundary.ErrorPopup;
import UseCases.OutputBoundary.FoodTruckModel;

public class ChangeMenuUseCase implements ChangeMenuInputBoundary{
    VendorRepository vendorRepository;
    ShopRepository shopRepository;
    ErrorPopup errorDisplayer;
    FoodTruckModel foodTruckModel;

    public ChangeMenuUseCase(VendorRepository vendorRepository, ShopRepository shopRepository,
                             ErrorPopup errorDisplayer, FoodTruckModel foodTruckModel ){
        this.vendorRepository = vendorRepository;
        this.errorDisplayer = errorDisplayer;
        this.shopRepository = shopRepository;
        this.foodTruckModel = foodTruckModel;
    }
    @Override
    public boolean changeMenu(String userToken, String shopID, Menu menu) {
        IVendor vendor = (IVendor) vendorRepository.getUserFromToken(userToken);

        if(vendor != null){
            IShop foodtruck  = shopRepository.getShop(shopID);
            if(foodtruck != null){
                foodtruck.setMenu(menu);
                foodTruckModel.updateFoodTruck(foodtruck);
                return shopRepository.save(foodtruck);
            }
            else{
                errorDisplayer.displayError("Invalid ShopID");
            }

        }
        errorDisplayer.displayError("User must be logged in as vendor");
        return false;
    }
}
