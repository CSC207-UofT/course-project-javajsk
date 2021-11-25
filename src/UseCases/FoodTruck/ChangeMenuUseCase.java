package UseCases.FoodTruck;

import Entities.Interfaces.ICustomer;
import Entities.Interfaces.IShop;
import Entities.Interfaces.IVendor;
import Entities.Menu;
import UseCases.DataAccessInterfaces.FoodTruckRepository;
import UseCases.DataAccessInterfaces.VendorRepository;
import UseCases.OutputBoundary.ErrorPopup;
import UseCases.OutputBoundary.FoodTruckModel;

public class ChangeMenuUseCase implements ChangeMenuInputBoundary{
    VendorRepository vendorRepository;
    FoodTruckRepository foodTruckRepository;
    ErrorPopup errorDisplayer;
    FoodTruckModel foodTruckModel;


    @Override
    public boolean changeMenu(String userToken, String shopID, Menu menu) {
        IVendor vendor = (IVendor) vendorRepository.getUserFromToken(userToken);

        if(vendor != null){
            IShop foodtruck  = foodTruckRepository.getFoodTruck(shopID);
            if(foodtruck != null){
                foodtruck.setMenu(menu);
                foodTruckModel.updateFoodTruck(foodtruck);
                return foodTruckRepository.save(foodtruck);
            }
            else{
                errorDisplayer.displayError("Invalid ShopID");
            }

        }
        errorDisplayer.displayError("User must be logged in as vendor");
        return false;
    }
}
