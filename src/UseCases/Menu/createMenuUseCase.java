package UseCases.Menu;

import Entities.Menu;
import UseCases.DataAccessInterfaces.ShopRepository;
import UseCases.OutputBoundary.MenuModel;


public class createMenuUseCase implements createMenuInputBoundary{
    ShopRepository foodTruckData;
    MenuModel menuModel;

    public createMenuUseCase(ShopRepository foodTruckData, MenuModel menuModel){
        this.foodTruckData = foodTruckData;
        this.menuModel = menuModel;
    }

    public boolean createMenu(String shopId) {
        Menu menu = new Menu();
        this.foodTruckData.getShop(shopId).setMenu(menu);
        return true;
    }
}
