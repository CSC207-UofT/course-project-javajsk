package UseCases.FoodTruck;

import Entities.FoodTruck;
import Entities.Interfaces.*;
import Entities.Menu;
import UseCases.DataAccessInterfaces.CustomerRepository;
import UseCases.DataAccessInterfaces.FoodTruckRepository;
import UseCases.DataAccessInterfaces.VendorRepository;
import UseCases.OutputBoundary.ErrorPopup;
import UseCases.OutputBoundary.FoodTruckModel;

public class CreateFoodTruckUseCase implements CreateFoodTruckInputBoundary{
    FoodTruckRepository foodTruckRepository;
    VendorRepository vendorRepository;
    ErrorPopup errorDisplayer;
    FoodTruckModel foodTruckModel;

    @Override
    public Boolean createFoodTruck(String userToken, String name, Menu menu, String status, IOrderbook orderbook, String location) {
        IVendor vendor = vendorRepository.getUserFromToken(userToken);
        if(vendor != null) {
            FoodTruck foodTruck = new FoodTruck(menu, orderbook, status, name);
            foodTruckRepository.createFoodTruck(foodTruck);
            vendor.addShop(foodTruck);
            foodTruckModel.displayFoodTruck(foodTruck);
            return vendorRepository.save(vendor);
        }

        errorDisplayer.displayError("User must be logged in as a vendor.");
        return false;
    }
}
