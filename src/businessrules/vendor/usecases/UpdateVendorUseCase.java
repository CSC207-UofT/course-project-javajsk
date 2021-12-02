package businessrules.vendor.usecases;

import businessrules.dai.VendorRepository;
import businessrules.loaders.ShopLoader;
import businessrules.outputboundary.VendorModel;
import businessrules.outputboundary.ErrorModel;
import entities.Shop;
import entities.Vendor;
import org.json.JSONException;
import org.json.JSONObject;
import businessrules.vendor.inputboundaries.UpdateVendorInputBoundary;

public class UpdateVendorUseCase implements UpdateVendorInputBoundary {
    VendorRepository vendorRepository;
    ErrorModel errorModel;
    VendorModel vendorModel;

    public UpdateVendorUseCase(VendorRepository vendorRepository, ErrorModel errorModel,
                                 VendorModel vendorModel) {
        this.vendorRepository = vendorRepository;
        this.errorModel = errorModel;
        this.vendorModel = vendorModel;
    }

    @Override
    public boolean updateVendor(String userToken, JSONObject newData) {
        JSONObject VendorInfo = vendorRepository.readUserFromToken(userToken);

        if(!VendorInfo.getString("id").equals(newData.getString("id"))){
            errorModel.displayError("Incorrect id value passed.");
            return false;
        }

        Vendor vendor;
        try{
            String id = newData.getString("id");
            String username = newData.getString("username");
            String password = newData.getString("password");
            Shop shop = ShopLoader.loadShop(newData.getJSONObject("shop"));
            if(shop == null){
                errorModel.displayError("Shop was unable to load.");
                return false;
            }

            vendor = new Vendor(id,username,password,shop);


        }catch (JSONException e){
            errorModel.displayError("Unable to generate Vendor object.");
            return false;
        }

        if(!vendorRepository.updateVendor(vendor.getId(), vendor.jsonify())){
            errorModel.displayError("Failed to update Vendor object in repository.");
            return false;
        }

        vendorModel.updateVendor(vendor.getId(), vendor.jsonify());
        return true;
    }
}
