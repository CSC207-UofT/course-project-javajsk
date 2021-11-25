package UseCases.FoodTruck;

import Entities.VendorOrderBook;

public interface ProcessOrderInputBoundary {

    Boolean processOrder(String userToken, String shopID);
}
