package UseCases.FoodTruck;

import Entities.Interfaces.ICustomer;
import Entities.Interfaces.IShop;
import Entities.Interfaces.IVendor;
import Entities.Menu;
import UseCases.DataAccessInterfaces.FoodTruckRepository;
import UseCases.DataAccessInterfaces.VendorRepository;

public class GetMenuUseCase implements GetMenuInputBoundary{
    FoodTruckRepository foodTruckRepository;
    VendorRepository vendorRepository;
    @Override
    public Menu getMenu(String userToken, String shopid) {
        IVendor vendor = vendorRepository.getUserFromToken(userToken);
        //TODO: This should work for both vendors and customers
        IShop foodtruck = foodTruckRepository.getFoodTruck(shopid);
        return foodtruck.getMenu();

    }
}
