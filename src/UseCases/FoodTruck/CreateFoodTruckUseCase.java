package UseCases.FoodTruck;

import Entities.FoodTruck;
import Entities.Interfaces.*;
import Entities.Menu;
import UseCases.DataAccessInterfaces.FoodTruckRepository;
import UseCases.DataAccessInterfaces.VendorRepository;
import UseCases.OutputBoundary.ErrorPopup;
import UseCases.OutputBoundary.FoodTruckModel;

import java.util.HashMap;

public class CreateFoodTruckUseCase implements CreateFoodTruckInputBoundary{
    FoodTruckRepository foodTruckRepository;
    VendorRepository vendorRepository;
    ErrorPopup errorDisplayer;
    FoodTruckModel foodTruckModel;
    public CreateFoodTruckUseCase(VendorRepository vendorRepository,FoodTruckRepository foodTruckRepository,
                             ErrorPopup errorDisplayer, FoodTruckModel foodTruckModel ){
        this.vendorRepository = vendorRepository;
        this.errorDisplayer = errorDisplayer;
        this.foodTruckRepository = foodTruckRepository;
        this.foodTruckModel = foodTruckModel;
    }
    @Override
    public Boolean createFoodTruck(String userToken, String name, Menu menu, String status, HashMap<IAddon, Boolean> addAvail,
                                   IOrderbook orderbook, String location, Boolean isOpen) {
        IVendor vendor = (IVendor) vendorRepository.getUserFromToken(userToken);
        if(vendor != null) {
            FoodTruck foodTruck = new FoodTruck(menu, orderbook, location, name,addAvail,isOpen);
            foodTruckRepository.createFoodTruck(foodTruck);
            vendor.addShop(foodTruck);
            foodTruckModel.displayFoodTruck(foodTruck);
            return vendorRepository.save(vendor);
        }

        errorDisplayer.displayError("User must be logged in as a vendor.");
        return false;
    }
}
