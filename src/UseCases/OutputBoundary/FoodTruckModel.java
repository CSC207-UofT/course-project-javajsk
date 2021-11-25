package UseCases.OutputBoundary;

import Entities.Interfaces.ICart;
import Entities.Interfaces.IShop;

public interface FoodTruckModel {
    void displayFoodTruck(IShop foodtruck);

    void updateFoodTruck(IShop foodtruck);
}
