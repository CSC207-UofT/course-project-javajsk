package UseCases.FoodTruck;

import Entities.Interfaces.ICustomer;
import Entities.Interfaces.IShop;
import Entities.Interfaces.IVendor;
import Entities.Menu;
import UseCases.DataAccessInterfaces.FoodTruckRepository;
import UseCases.DataAccessInterfaces.VendorRepository;
import UseCases.OutputBoundary.ErrorPopup;

public class ChangeMenuUseCase implements ChangeMenuInputBoundary{
    VendorRepository vendorRepository;
    FoodTruckRepository foodTruckRepository;
    ErrorPopup errorDisplayer;


    @Override
    public boolean changeMenu(String userToken, String shopID, Menu menu) {
        IVendor vendor = vendorRepository.getUserFromToken(userToken);
        IShop foodtruck  = foodTruckRepository.getFoodTruck(shopID);
        if(vendor != null){
            foodtruck.setMenu(menu);
            return foodTruckRepository.save(foodtruck);
        }
        errorDisplayer.displayError("User must be logged in as vendor");
        return false;
    }
}
