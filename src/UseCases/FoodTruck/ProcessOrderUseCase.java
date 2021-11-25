package UseCases.FoodTruck;

import Entities.Interfaces.IOrder;
import Entities.Interfaces.IShop;
import Entities.Interfaces.IVendor;
import Entities.VendorOrderBook;
import UseCases.DataAccessInterfaces.FoodTruckRepository;
import UseCases.DataAccessInterfaces.VendorRepository;
import UseCases.OutputBoundary.ErrorPopup;
import UseCases.OutputBoundary.FoodTruckModel;

public class ProcessOrderUseCase implements ProcessOrderInputBoundary {
    VendorRepository vendorRepository;
    FoodTruckRepository foodTruckRepository;
    ErrorPopup errorDisplayer;
    FoodTruckModel foodTruckModel;

    @Override
    public Boolean processOrder(String userToken, String shopID) {
        IVendor vendor = (IVendor) vendorRepository.getUserFromToken(userToken);
        if(vendor != null){
            IShop foodtruck = foodTruckRepository.getFoodTruck(shopID);
            if(foodtruck != null){
                VendorOrderBook orderbook = (VendorOrderBook) foodtruck.getOrderBook(); //TODO: Does this casting make sense?
                IOrder order = orderbook.getNextOrder();
                order.setStatus(true); //TODO: We don't have Status codes yet. Its just true and false rn.
                foodTruckModel.updateFoodTruck(foodtruck);
                return foodTruckRepository.save(foodtruck);
            }
           else{
                errorDisplayer.displayError("Invalid ShopID");
            }

        }
        errorDisplayer.displayError("User must be logged in as vendor.");
        return false;
    }
}
