package UseCases.FoodTruck;

import Entities.Interfaces.IOrderbook;
import Entities.Menu;

public interface CreateFoodTruckInputBoundary {

    Boolean createFoodTruck(String userToken, String name, Menu menu, String status, IOrderbook orderbook, String location);
}
