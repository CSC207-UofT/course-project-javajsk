package UseCases.Menu;

import Entities.Menu;
import UseCases.DataAccessInterfaces.FoodTruckRepository;
import UseCases.OutputBoundary.MenuModel;


public class createMenuUseCase implements createMenuInputBoundary{
    FoodTruckRepository foodTruckData;
    MenuModel menuModel;

    public createMenuUseCase(FoodTruckRepository foodTruckData, MenuModel menuModel){
        this.foodTruckData = foodTruckData;
        this.menuModel = menuModel;
    }

    public boolean createMenu(String shopId) {
        Menu menu = new Menu();
        this.foodTruckData.getFoodTruck(shopId).setMenu(menu);
        return true;
    }
}
