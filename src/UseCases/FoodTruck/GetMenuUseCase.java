package UseCases.FoodTruck;

import Entities.Interfaces.IShop;
import Entities.Interfaces.IUser;
import Entities.Menu;
import UseCases.DataAccessInterfaces.ShopRepository;
import UseCases.DataAccessInterfaces.UserRepository;
import UseCases.OutputBoundary.ErrorPopup;

public class GetMenuUseCase implements GetMenuInputBoundary{
    ShopRepository shopRepository;
    UserRepository userRepository;
    ErrorPopup errorDisplayer;
    public GetMenuUseCase(ShopRepository shopRepository,
                          ErrorPopup errorDisplayer, UserRepository userRepository){
        this.userRepository = userRepository;
        this.errorDisplayer = errorDisplayer;
        this.shopRepository = shopRepository;
    }
    @Override
    public Menu getMenu(String userToken, String shopid) {
        IUser user = userRepository.getUserFromToken(userToken);
        if(user != null){
            IShop foodTruck = shopRepository.getShop(shopid);
            if(foodTruck != null){
                return foodTruck.getMenu();
            }
            else{
                errorDisplayer.displayError("Invalid ShopID");
            }
        }
        errorDisplayer.displayError("Invalid UserID");
        return null;
    }
}
