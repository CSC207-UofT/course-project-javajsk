package businessrules.food.usecases;

import businessrules.dai.FoodRepository;
import businessrules.dai.ShopRepository;
import businessrules.dai.VendorRepository;
import businessrules.food.inputboundaries.DeleteFoodInputBoundary;
import businessrules.loaders.FoodLoader;
import businessrules.loaders.ShopLoader;
import businessrules.loaders.SingletonLoader;
import businessrules.loaders.VendorLoader;
import businessrules.outputboundary.FoodModel;
import businessrules.outputboundary.ErrorModel;
import entities.Food;
import entities.Shop;
import entities.Vendor;

public class DeleteFoodUseCase implements DeleteFoodInputBoundary {
    FoodRepository foodRepository;
    FoodModel foodModel;
    ErrorModel errorHandler;
    VendorRepository vendorRepository;
    ShopRepository shopRepository;
    VendorLoader vendorLoader;
    FoodLoader foodLoader;
    SingletonLoader singletonLoader;

    public DeleteFoodUseCase(FoodRepository arep, VendorRepository vr, ShopRepository sr, SingletonLoader sl,
                             ShopLoader sL, FoodModel fm, ErrorModel em){
        this.foodRepository = arep;
        this.foodModel = fm;
        this.vendorRepository = vr;
        this.errorHandler = em;
        this.shopRepository = sr;
        this.singletonLoader = sl;
        this.foodLoader = new FoodLoader(arep, this.singletonLoader, em);
        this.vendorLoader = new VendorLoader(vr, sL, em);
    }

    @SuppressWarnings("DuplicatedCode")
    @Override
    public boolean deleteFood(String vendorToken, String id) {
        Vendor vendor = vendorLoader.loadVendorFromToken(vendorToken);
        Food food = foodLoader.loadFoodFromId(id);
        if(vendor == null || food == null ){
            errorHandler.displayError("Incorrect vendorToken or FoodId.");
            return false;
        }
        Shop shop = vendor.getShop();

        boolean deleteSuccess = shop.getMenu().deleteFood(food);

        if(!deleteSuccess){
            errorHandler.displayError("No such Food in any shop owned by you.");
            return false;
        }

        boolean updateSuccess = shopRepository.updateShop(shop.getId(), shop.jsonify());
        if(!updateSuccess){
            errorHandler.displayError("Unable to update shop's menu and remove Food.");
            return false;
        }

        if(foodRepository.deleteFood(id)){
            foodModel.deleteFood(id);
            return true;
        }
        return false;
    }
}
