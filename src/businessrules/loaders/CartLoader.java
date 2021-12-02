package businessrules.loaders;

import businessrules.dai.CustomerRepository;
import businessrules.dai.FoodRepository;
import entities.Cart;
import entities.Customer;
import entities.Food;
import entities.Selection;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class CartLoader {

    CustomerRepository customerRepository;
    FoodRepository foodRepository;
    CustomerLoader customerLoader;
    FoodLoader foodLoader;
    SelectionLoader selectionLoader;



    public CartLoader(CustomerRepository cR, FoodRepository fR, CustomerLoader cL, FoodLoader fL, SelectionLoader sL){
        this.customerRepository = cR;
        this.foodRepository = fR;
        this.customerLoader = cL;
        this.foodLoader = fL;
        this.selectionLoader = sL;
    }

    public Cart loadCart(JSONObject rawData) throws JSONException {
        String id = rawData.getString("id");
        String shopId = rawData.getString("shopId");
        JSONObject cartContents = rawData.getJSONObject("contents");
        return new Cart(id, shopId, getCartHashMap(cartContents));
    }

    public Cart loadCartFromCustomerToken(String customerToken) {
        JSONObject customerData = customerRepository.readUserFromToken(customerToken);
        Customer customer = CustomerLoader.loadCustomer(customerData);
        return customer.getCurrentCart();
    }


    public HashMap<Food, List<Selection[]>> getCartHashMap(JSONObject cartContents){
        HashMap<Food, List<Selection[]>> cart = new HashMap<>();
        for(String foodId: cartContents.keySet()){
            JSONObject food = foodRepository.readFood(foodId);
            cart.put(foodLoader.loadFood(food), getListFromJSONArray(cartContents.getJSONArray(foodId)));
        }
        return cart;
    }

    public List<Selection[]> getListFromJSONArray(JSONArray array){
        ArrayList<Selection[]> arr = new ArrayList<>();
        for(int i = 0; i< array.length();i++){
            JSONArray nested = array.getJSONArray(i);
            Selection[] selArr = new Selection[nested.length()];
            for(int x = 0; x < nested.length(); x++){
                selArr[i] = selectionLoader.loadSelection(nested.getJSONObject(x));
            }
            arr.add(selArr);
        }
        return arr;
    }
}
