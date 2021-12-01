package businessrules.food.usecases;

import businessrules.dai.FoodRepository;
import businessrules.dai.ShopRepository;
import businessrules.dai.VendorRepository;
import businessrules.food.inputboundaries.CreateFoodInputBoundary;
import businessrules.loaders.FoodLoader;
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
    ErrorModel errorHandler;


    public CreateFoodUseCase(FoodRepository foodRepository, VendorRepository vendorRepository, FoodModel foodView,
                             ShopRepository shopRepository, ErrorModel errorHandler) {
        this.foodRepository = foodRepository;
        this.vendorRepository = vendorRepository;
        this.foodView = foodView;
        this.shopRepository = shopRepository;
        this.errorHandler = errorHandler;
        this.vendorLoader = new VendorLoader(vendorRepository, errorHandler);
    }

    @Override
    public boolean createFood(String vendorToken, JSONObject data) {
        Vendor vendor = vendorLoader.loadVendorFromToken(vendorToken);

        if(vendor == null){
            return false;
        }

        Food food;

        try{
            food = FoodLoader.loadFood(data);
        }catch (JSONException e){
            errorHandler.displayError(e.getMessage());
            return false;
        }

        String id = foodRepository.createFood(food.jsonify());

        if(id == null){
            errorHandler.displayError("Unable to create addon in the repository.");
            return false;
        }

        food.setId(id);

        Shop shop = vendor.getShop();
        shop.getMenu().addFood(food);

        if(!shopRepository.updateShop(shop.getId(), shop.jsonify())){
            errorHandler.displayError("Unable to update shop in the repository.");
            return  false;
        }

        foodView.displayFood(food.jsonify());
        return true;
    }
}
