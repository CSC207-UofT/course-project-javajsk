package adapters.dam.entityrepoitories;

import adapters.dam.DBGateway;
import adapters.dam.TokenSigner;
import businessrules.dai.VendorRepository;
import entities.Customer;
import entities.Shop;
import entities.User;
import entities.Vendor;
import framework.MongoDB;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class VendorDB implements VendorRepository {
    DBGateway databaseConnector;
    final String tableName = "Vendor";
    TokenSigner tokenSigner;

    public VendorDB(DBGateway db) {
        this.databaseConnector = db;
    }

    @Override
    public Vendor read(String id) {
        return loadVendorFromJSON(databaseConnector.read(tableName, id));
    }

    @Override
    public boolean update(String id, Vendor item) {
        return databaseConnector.update(tableName, id, loadJSONFromVendor(item));

    }

    @Override
    public String create(Vendor item) {
        return databaseConnector.create(tableName, loadJSONFromVendor(item));
    }

    @Override
    public List<Vendor> readMultiple(String parameter, String needle) {
        List<Vendor> vendorList = new ArrayList<>();
        List<JSONObject> rawVendors = databaseConnector.readMultiple(tableName, parameter, needle);
        for(JSONObject rawVendor: rawVendors){
            vendorList.add(loadVendorFromJSON(rawVendor));
        }
        return vendorList;
    }

    @Override
    public Vendor findOneByFieldName(String fieldName, String needle) {
        return loadVendorFromJSON(databaseConnector.readOne(tableName,fieldName, needle));
    }

    @Override
    public Vendor getUserFromToken(String userToken) {
        String userId = tokenSigner.getIdFromToken(userToken);
        if(userId.contains("ERROR")){
            return null;
        }
        return read(userId);
    }

    @Override
    public String authenticateUser(String username, String password) {
        Vendor vendor = findOneByFieldName("username", username);
        if(vendor == null){
            return null;
        }
        if(!vendor.getHashedPassword().equals(password)){
            return null;
        }
        return tokenSigner.generateToken(vendor.getId());
    }

    public Vendor loadVendorFromJSON(JSONObject jsonObject) {
        ShopDB shopLoader = new ShopDB(databaseConnector);
        try{
            String id = jsonObject.getString("id");
            String username = jsonObject.getString("username");
            String hashedPassword = jsonObject.getString("password");
            String shopId = jsonObject.getString("shopId");
            Shop shop = shopLoader.read(shopId);
            return new Vendor(id, username, hashedPassword, shop);
        }catch (JSONException e){
            return null;
        }
    }

    public static JSONObject loadJSONFromVendor(Vendor vendor){
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("id", vendor.getId());
        jsonObject.put("username", vendor.getUserName());
        jsonObject.put("password", vendor.getHashedPassword());
        jsonObject.put("shopId", vendor.getShop().getId());
        return jsonObject;
    }


}
