package businessrules.loaders;

import entities.Shop;
import entities.Vendor;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

public class VendorLoader {

    public static Vendor loadVendor(JSONObject data) throws JSONException {
        String id = data.getString("id");
        String username = data.getString("username");
        String password = data.getString("password");
        Shop shop = ShopLoader.loadShop(data.getJSONObject("shop"));
        return new Vendor(id,username, password, shop);
    }
}
