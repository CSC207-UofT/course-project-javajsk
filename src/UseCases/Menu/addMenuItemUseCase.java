package UseCases.Menu;

import Entities.Menu;
import UseCases.DataAccessInterfaces.FoodRepository;
import UseCases.OutputBoundary.MenuModel;

public class addMenuItemUseCase implements addMenuItemInputBoundary{
    FoodRepository foodData;
    MenuModel menuModel;

    /**
     * Creates a use case for adding new items to a menu
     * @param foodData the repository containing all the food items
     * @param menuModel the output boundary for menu
     */
    public addMenuItemUseCase(FoodRepository foodData, MenuModel menuModel){
        this.foodData = foodData;
        this.menuModel = menuModel;
    }

    /**
     * A method for adding a food item to a menu
     * @param menu the menu to change
     * @param foodId the id of the food item to add
     * @param price the price of the new food
     * @param availability the availability of the new food
     * @return true if the new item has been added and false if not
     */
    public boolean addMenuItem(Menu menu, String foodId, float price, boolean availability) {
        menu.addFood(foodData.getFood(foodId), price, availability);
        menuModel.updateMenu(menu);
        return true;
    }
}
