package UseCases.FoodTruck;

import Entities.Interfaces.IAddon;
import Entities.Interfaces.IOrderbook;
import Entities.Menu;

import java.util.HashMap;

public interface CreateFoodTruckInputBoundary {

    Boolean createFoodTruck(String userToken, String name, Menu menu, String status, HashMap<IAddon, Boolean> addAvail,
                            IOrderbook orderbook, String location, Boolean isOpen);
}
