package UseCases.Menu;


import Entities.Menu;
import UseCases.DataAccessInterfaces.FoodRepository;
import UseCases.DataAccessInterfaces.ShopRepository;
import UseCases.OutputBoundary.MenuModel;

public class removeMenuItemUseCase implements removeMenuItemInputBoundary{
    MenuModel menuModel;
    ShopRepository foodTruckData;
    FoodRepository foodData;

    /**
     * Creates a use case for removing items from a shop's menu
     * @param menuModel the output boundary for the menu
     * @param foodData the repository containing all the food data
     * @param foodTruckData the repository containing all the shop data
     */
    public removeMenuItemUseCase(MenuModel menuModel, FoodRepository foodData, ShopRepository foodTruckData){
        this.menuModel = menuModel;
        this.foodData = foodData;
        this.foodTruckData = foodTruckData;
    }

    /**
     * A method for removing an item from a shop's menu
     * @param shopId the shop id
     * @param foodId the id of the food item
     */
    public void removeMenuItem(String shopId, String foodId) {
        Menu menu = this.foodTruckData.getShop(shopId).getMenu();
        menu.removeFood(this.foodData.getFood(foodId));
        this.menuModel.updateMenu(menu);
    }
}
