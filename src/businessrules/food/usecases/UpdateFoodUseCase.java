package businessrules.food.usecases;

import businessrules.dai.FoodRepository;
import businessrules.dai.ShopRepository;
import businessrules.dai.VendorRepository;
import businessrules.food.inputboundaries.UpdateFoodInputBoundary;
import businessrules.loaders.FoodLoader;
import businessrules.loaders.ShopLoader;
import businessrules.loaders.SingletonLoader;
import businessrules.loaders.VendorLoader;
import businessrules.outputboundary.FoodModel;
import businessrules.outputboundary.ErrorModel;
import entities.Food;
import entities.Shop;
import entities.Vendor;
import org.json.JSONException;
import org.json.JSONObject;

public class UpdateFoodUseCase implements UpdateFoodInputBoundary {
    FoodRepository FoodRepository;
    ErrorModel errorHandler;
    ShopRepository shopRepository;
    VendorRepository vendorRepository;
    FoodModel FoodView;
    VendorLoader vendorLoader;
    SingletonLoader singletonLoader;
    FoodLoader foodLoader;

    public UpdateFoodUseCase(FoodRepository FoodRepository, ErrorModel errorHandler, ShopRepository shopRepository,
                             SingletonLoader singleLoad, ShopLoader sL, VendorRepository vendorRepository, FoodModel FoodView) {
        this.FoodRepository = FoodRepository;
        this.errorHandler = errorHandler;
        this.shopRepository = shopRepository;
        this.singletonLoader = singleLoad;
        this.vendorRepository = vendorRepository;
        this.FoodView = FoodView;
        this.foodLoader = new FoodLoader(FoodRepository, this.singletonLoader, errorHandler);
        this.vendorLoader = new VendorLoader(vendorRepository, sL, errorHandler);
    }

    @SuppressWarnings("DuplicatedCode")
    @Override
    public boolean updateFood(String vendorToken, String FoodId, JSONObject object) {
        Vendor vendor = vendorLoader.loadVendorFromToken(vendorToken);
        Food food = foodLoader.loadFoodFromId(FoodId);

        if(vendor == null || food == null){
            errorHandler.displayError("Incorrect vendorToken or FoodId.");
            return false;
        }


        Shop shop = vendor.getShop();
        Food newFood;
        try{
            newFood = foodLoader.loadFood(object);
        }catch (JSONException e){
            errorHandler.displayError("Unable to generate new food from given data.");
            return false;
        }
        boolean success = shop.getMenu().updateFood(FoodId, newFood);

        if(!success){
            errorHandler.displayError("No such Food in the shop's menu.");
            return false;
        }

        if(!FoodRepository.updateFood(FoodId, newFood.jsonify())){
            errorHandler.displayError("Unable to save modified Food in repository.");
            return false;
        }

        if(!shopRepository.updateShop(shop.getId(), shop.jsonify())){
            errorHandler.displayError("Unable to update shop's menu and update Food.");
            return false;
        }

        FoodView.updateFood(FoodId, object);
        return true;


    }
}
