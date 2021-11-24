package UseCases.FoodTruck;

import Entities.Interfaces.IOrder;
import Entities.Interfaces.IShop;
import Entities.Interfaces.IVendor;
import Entities.VendorOrderBook;
import UseCases.DataAccessInterfaces.FoodTruckRepository;
import UseCases.DataAccessInterfaces.VendorRepository;
import UseCases.OutputBoundary.ErrorPopup;

public class ProcessOrderUseCase implements ProcessOrderInputBoundary {
    VendorRepository vendorRepository;
    FoodTruckRepository foodTruckRepository;
    ErrorPopup errorDisplayer;

    @Override
    public Boolean processOrder(String userToken, String shopID) {
        IVendor vendor = vendorRepository.getUserFromToken(userToken);
        if(vendor != null){
            IShop foodtruck = foodTruckRepository.getFoodTruck(shopID);
            VendorOrderBook orderbook = (VendorOrderBook) foodtruck.getOrderBook(); //TODO: Does this casting make sense?
            IOrder order = orderbook.getNextOrder();
            order.setStatus(true); //TODO: We don't have Status codes yet. Its just true and false rn.
            return foodTruckRepository.save(foodtruck);


        }
        errorDisplayer.displayError("User must be logged in as vendor.");
        return false;
    }
}
