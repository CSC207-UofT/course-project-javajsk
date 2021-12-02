package businessrules.food.usecases;

import businessrules.dai.FoodRepository;
import businessrules.dai.ShopRepository;
import businessrules.dai.VendorRepository;
import businessrules.food.inputboundaries.CreateFoodInputBoundary;
import businessrules.loaders.FoodLoader;
import businessrules.loaders.ShopLoader;
import businessrules.loaders.VendorLoader;
import businessrules.outputboundary.ErrorModel;
import businessrules.outputboundary.FoodModel;
import entities.Food;
import entities.Shop;
import entities.Vendor;
import org.json.JSONException;
import org.json.JSONObject;

public class CreateFoodUseCase implements CreateFoodInputBoundary {
    FoodRepository foodRepository;
    VendorRepository vendorRepository;
    FoodModel foodView;
    ShopRepository shopRepository;
    VendorLoader vendorLoader;
    FoodLoader foodLoader;
    ErrorModel errorHandler;
    FoodModel foodModel;


    public CreateFoodUseCase(FoodRepository foodRepository, VendorRepository vendorRepository, FoodModel foodView,
                             FoodLoader fL, ShopLoader sL, ShopRepository shopRepository, ErrorModel errorHandler,
                             FoodModel foodModel) {
        this.foodRepository = foodRepository;
        this.vendorRepository = vendorRepository;
        this.foodView = foodView;
        this.shopRepository = shopRepository;
        this.errorHandler = errorHandler;
        this.foodLoader = fL;
        this.foodModel = foodModel;
        this.vendorLoader = new VendorLoader(vendorRepository, sL, errorHandler);
    }

    @Override
    public JSONObject createFood(String vendorToken, JSONObject data) {
        Vendor vendor = vendorLoader.loadVendorFromToken(vendorToken);

        if(vendor == null){
            return foodModel.displayError("Invalid vendorToken");
        }

        Food food;
        try{
            food = foodLoader.loadFood(data);
        }catch (JSONException e){
            return foodModel.displayError(e.getMessage());
        }

        String id = foodRepository.createFood(food.jsonify());

        if(id == null){
            return foodModel.displayError("Unable to create addon in the repository.");
        }

        food.setId(id);

        Shop shop = vendor.getShop();
        shop.getMenu().addFood(food);

        if(!shopRepository.updateShop(shop.getId(), shop.jsonify())){
            return foodModel.displayError("Unable to update shop in the repository.");
        }

        return foodModel.displayFood(food.jsonify());
    }
}
