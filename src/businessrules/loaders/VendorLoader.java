package businessrules.loaders;

import businessrules.dai.VendorRepository;
import businessrules.outputboundary.ErrorModel;
import entities.Shop;
import entities.Vendor;
import org.json.JSONException;
import org.json.JSONObject;

public class VendorLoader {
    VendorRepository vendorRepository;
    ShopLoader shopLoader;
    ErrorModel errorHandler;

    public VendorLoader(VendorRepository vr, ShopLoader sL, ErrorModel em){
        this.vendorRepository = vr;
        this.shopLoader = sL;
        this.errorHandler = em;
    }

    public Vendor loadVendor(JSONObject data) throws JSONException {
        String id = data.getString("id");
        String username = data.getString("username");
        String password = data.getString("password");
        Shop shop = shopLoader.loadShop(data.getJSONObject("shop"));
        return new Vendor(id,username, password, shop);
    }

    public Vendor loadVendorFromToken(String vendorToken) throws JSONException {

        JSONObject vendorRaw = vendorRepository.readUserFromToken(vendorToken);

        if(vendorRaw == null){
            errorHandler.displayError("Unable to find vendor with such token.");
            return null;
        }

        try{
            return loadVendor(vendorRaw);
        }catch( JSONException e){
            errorHandler.displayError("Unable to load vendor from repository information.");
        }
        return null;
    }


}
