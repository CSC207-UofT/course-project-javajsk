package Adapters.dbmanager;

import Adapters.DatabaseGateway;
import Adapters.TokenManager;
import businessrules.dai.VendorRepository;
import org.json.JSONObject;

public class VendorDB implements VendorRepository {
    DatabaseGateway databaseGateway;
    TokenManager tokenManager;
    String TABLE = "vendor";
    @Override
    public boolean updateUser(String id, JSONObject data) {
        return databaseGateway.updateEntry(TABLE, id, data);
    }

    @Override
    public JSONObject readUserFromToken(String userToken) {
        return tokenManager.parseToken(userToken);
    }

    @Override
    public String getUserToken(String username, String password) {
        return tokenManager.generateToken(username,password);
    }

    @Override
    public boolean isUsernameUnique(String username) {
        return databaseGateway.isColUnique(TABLE, "username", username);
    }
}
