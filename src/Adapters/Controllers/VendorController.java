package Adapters.Controllers;

import Adapters.JSONParser;
import Entities.Interfaces.IShop;
import Entities.Regular.RegularVendor;
import UseCases.OutputBoundary.VendorModel;
import UseCases.RegularVendor.AddShopInputBoundary;
import UseCases.RegularVendor.CreateVendorInputBoundary;
import org.json.JSONObject;

import java.util.ArrayList;

public class VendorController {
    AddShopInputBoundary addShopInputBoundary;
    CreateVendorInputBoundary createVendorInputBoundary;
    JSONParser parser;

    public VendorController(AddShopInputBoundary addShopInputBoundary,
                            CreateVendorInputBoundary createVendorInputBoundary,
                            JSONParser parser){
        this.addShopInputBoundary = addShopInputBoundary;
        this.createVendorInputBoundary = createVendorInputBoundary;
        this.parser = parser;
    }

    public boolean runAddShop(String raw_text){
        JSONObject data = this.parser.parse(raw_text);
        String token = data.getString("token");
        String shopId = data.getString("shopId");
        return addShopInputBoundary.addShop(shopId, token);
    }

    public RegularVendor runCreateVendor(String raw_text){
        JSONObject data = this.parser.parse(raw_text);
        String vendorId = data.getString("vendorId");
        return createVendorInputBoundary.createVendor(vendorId);
    }
}
