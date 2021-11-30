package UseCases.FoodTruck;

import Entities.FoodTruck;
import Entities.Interfaces.*;
import Entities.Menu;
import UseCases.DataAccessInterfaces.ShopRepository;
import UseCases.DataAccessInterfaces.VendorRepository;
import UseCases.OutputBoundary.ErrorPopup;
import UseCases.OutputBoundary.FoodTruckModel;

import java.util.HashMap;

public class CreateFoodTruckUseCase implements CreateFoodTruckInputBoundary{
    ShopRepository shopRepository;
    VendorRepository vendorRepository;
    ErrorPopup errorDisplayer;
    FoodTruckModel foodTruckModel;
    public CreateFoodTruckUseCase(VendorRepository vendorRepository, ShopRepository shopRepository,
                                  ErrorPopup errorDisplayer, FoodTruckModel foodTruckModel ){
        this.vendorRepository = vendorRepository;
        this.errorDisplayer = errorDisplayer;
        this.shopRepository = shopRepository;
        this.foodTruckModel = foodTruckModel;
    }
    @Override
    public Boolean createFoodTruck(String userToken, String name, Menu menu, String status, HashMap<IAddon, Boolean> addAvail,
                                   IOrderbook orderbook, String location, Boolean isOpen) {
        IVendor vendor = (IVendor) vendorRepository.getUserFromToken(userToken);
        if(vendor != null) {

            FoodTruck foodTruck = new FoodTruck(menu, orderbook, location, name,addAvail,isOpen);
            shopRepository.createShop(foodTruck);

            vendor.addShop(foodTruck);
            foodTruckModel.displayFoodTruck(foodTruck);
            return vendorRepository.save(vendor);
        }

        errorDisplayer.displayError("User must be logged in as a vendor.");
        return false;
    }
}
