package Adapters.dbmanager;

import Adapters.DatabaseGateway;
import Adapters.TokenManager;
import businessrules.dai.CustomerRepository;
import org.json.JSONObject;

public class CustomerDB implements CustomerRepository {
    DatabaseGateway databaseGateway;
    TokenManager tokenManager;
    String TABLE = "customers";
    @Override
    public boolean createCustomer(String username, String hashedPassword) {
        // TODO: This needs fixing.
        JSONObject store = new JSONObject();
        store.put("username", username);
        store.put("password", hashedPassword);
        databaseGateway.createEntry(TABLE, store);
        return true;
    }

    @Override
    public boolean updateCustomer(String id, JSONObject info) {
        return updateUser(id, info);
    }

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
