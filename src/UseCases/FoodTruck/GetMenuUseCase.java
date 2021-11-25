package UseCases.FoodTruck;

import Entities.Interfaces.ICustomer;
import Entities.Interfaces.IShop;
import Entities.Interfaces.IVendor;
import Entities.Menu;
import UseCases.DataAccessInterfaces.FoodTruckRepository;
import UseCases.DataAccessInterfaces.VendorRepository;
import UseCases.OutputBoundary.ErrorPopup;

public class GetMenuUseCase implements GetMenuInputBoundary{
    FoodTruckRepository foodTruckRepository;
    VendorRepository vendorRepository;
    ErrorPopup errorDisplayer;

    @Override
    public Menu getMenu(String userToken, String shopid) {
        IVendor vendor = vendorRepository.getUserFromToken(userToken);
        //TODO: This should work for both vendors and customers
        if(vendor != null){
            IShop foodtruck = foodTruckRepository.getFoodTruck(shopid);
            if(foodtruck != null){
                return foodtruck.getMenu();
            }
            else{
                errorDisplayer.displayError("Invalid ShopID");
            }

        }
        errorDisplayer.displayError("Invalid UserID");
        return null;




    }
}
