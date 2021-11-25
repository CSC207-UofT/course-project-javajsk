package UseCases.Menu;

import Entities.Interfaces.IFood;
import Entities.Menu;
import UseCases.DataAccessInterfaces.FoodRepository;
import UseCases.DataAccessInterfaces.FoodTruckRepository;
import UseCases.OutputBoundary.MenuModel;

public class editMenuItemUseCase implements editMenuItemInputBoundary{
    FoodRepository foodData;
    FoodTruckRepository foodTruckData;
    MenuModel menuModel;

    /**
     * Create a use case for editing menu items
     * @param foodData the repository containing all the food data
     * @param menuModel the output boundary for the menu
     */
    public editMenuItemUseCase(FoodRepository foodData, MenuModel menuModel, FoodTruckRepository foodTruckData){
        this.menuModel = menuModel;
        this.foodTruckData = foodTruckData;
        this.foodData = foodData;
    }

    /**
     * A method for editing an item of a menu
     * @param shopId the shop's id for the shop's menu hat needs changes
     * @param foodId the id of the food being changed
     * @param price the new price of the item
     * @param availability the updated availability of the item
     * @return true if the menu has been changed and false if it hasn't
     */
    public boolean editMenuItem(String shopId, String foodId, float price, boolean availability) {
        IFood food = foodData.getFood(foodId);
        Menu menu = foodTruckData.getFoodTruck(shopId).getMenu();
        if(menu.getContents().containsKey(food)){
            menu.setFood(food, price, availability);
            this.menuModel.updateMenu(menu);
            return true;
        }
        return false;
    }
}

