package adapters.dam.entityrepoitories;

import adapters.dam.DBGateway;
import adapters.dam.TokenSigner;
import businessrules.dai.CustomerRepository;
import entities.*;
import framework.JWTSigner;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class CustomerDB implements CustomerRepository {
    DBGateway databaseConnector;
    TokenSigner tokenSigner;
    final String tableName = "Customer";

    public CustomerDB(DBGateway databaseConnector) {
        this.tokenSigner = new JWTSigner();
        this.databaseConnector = databaseConnector;
    }

    @Override
    public Customer read(String id) {
        return loadCustomerFromJSON(databaseConnector.read(tableName, id));
    }

    @Override
    public boolean update(String id, Customer item) {
        return databaseConnector.update(tableName, id, loadJSONFromCustomer(item));

    }

    @Override
    public String create(Customer item) {
        return databaseConnector.create(tableName, loadJSONFromCustomer(item));
    }

    @Override
    public List<Customer> readMultiple(String parameter, String needle) {
        List<Customer> customerList = new ArrayList<>();
        List<JSONObject> rawCustomers = databaseConnector.readMultiple(tableName, parameter, needle);
        for(JSONObject rawCustomer: rawCustomers){
            customerList.add(loadCustomerFromJSON(rawCustomer));
        }
        return customerList;
    }

    @Override
    public Customer findOneByFieldName(String fieldName, String needle) {
        JSONObject customerData = databaseConnector.readOne(tableName,fieldName, needle);
        if (customerData == null){
            return null;
        }
        return loadCustomerFromJSON(customerData);
    }


    @Override
    public User getUserFromToken(String userToken) {
        String info = tokenSigner.getIdFromToken(userToken);
        String userId = info.split(",")[0];
        System.out.println(userId);
        if(userId.contains("ERROR")){
            return null;
        }
        return read(userId);
    }

    @Override
    public String authenticateUser(String username, String password) {
        Customer customer = findOneByFieldName("username", username);
        if(customer == null){
            return null;
        }
        if(!customer.getHashedPassword().equals(password)){
            return null;
        }
        String token_parameter = customer.getId() + "," +customer.getUserName();
        return tokenSigner.generateToken(token_parameter);
    }

    public static JSONObject loadJSONFromCustomer(Customer customer){
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("id", customer.getId());
        jsonObject.put("cart", CartDB.loadJSONFromCart(customer.getCurrentCart()));
        jsonObject.put("password", customer.getHashedPassword());
        jsonObject.put("username",customer.getUserName());
        return jsonObject;
    }


    public Customer loadCustomerFromJSON(JSONObject jsonObject){
        CartDB cartLoader = new CartDB(databaseConnector);
        OrderDB orderLoader = new OrderDB(databaseConnector);
        try{
            String id = jsonObject.getString("id");
            String username = jsonObject.getString("username");
            String hashedPassword = jsonObject.getString("password");
            Cart cart = cartLoader.loadCartFromJSON(jsonObject.getJSONObject("cart"));
            OrderBook custOrderbook = new OrderBook();
            return new Customer(id, username, hashedPassword, custOrderbook, cart);
        }catch (JSONException e){
            return null;
        }
    }

}
