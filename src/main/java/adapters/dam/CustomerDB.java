package adapters.dam;

import adapters.TokenSigner;
import adapters.DBGateway;
import businessrules.dai.CustomerRepository;
import entities.*;
import framework.JWTSigner;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class
CustomerDB implements CustomerRepository {
    DBGateway databaseConnector;
    TokenSigner tokenSigner;
    final String tableName = "Customer";

    public CustomerDB(DBGateway databaseConnector) {
        this.tokenSigner = new JWTSigner();
        this.databaseConnector = databaseConnector;
    }

    /**
     * @param id the id of the entry to read from the database
     * @return the constructed entitity from the database
     */
    @Override
    public Customer read(String id) {
        return loadCustomerFromJSON(databaseConnector.read(tableName, id));
    }

    /**
     * @param id   the id of the entry to update
     * @param item the new object to replace the old object with
     * @return The status of the update. True if it went through,false if it didn't
     */
    @Override
    public boolean update(String id, Customer item) {
        return databaseConnector.update(tableName, id, loadJSONFromCustomer(item));

    }

    /**
     * @param item the entity to add to the database, the ID will initially be null
     * @return A String representation of the object that was created. This will have the ID the database generates for it
     */
    @Override
    public String create(Customer item) {
        return databaseConnector.create(tableName, loadJSONFromCustomer(item));
    }

    /**
     * @param parameter the parameter to search by
     * @param needle    the value of the parameter to find
     * @return A list of constructed customer entities
     */
    @Override
    public List<Customer> readMultiple(String parameter, String needle) {
        List<Customer> customerList = new ArrayList<>();
        List<JSONObject> rawCustomers = databaseConnector.readMultiple(tableName, parameter, needle);
        for(JSONObject rawCustomer: rawCustomers){
            customerList.add(loadCustomerFromJSON(rawCustomer));
        }
        return customerList;
    }

    /**
     * @param fieldName the field to search by
     * @param needle    the value of the field to find
     * @return A constructed customer object
     */
    @Override
    public Customer findOneByFieldName(String fieldName, String needle) {
        JSONObject customerData = databaseConnector.readOne(tableName,fieldName, needle);
        if (customerData == null){
            return null;
        }
        return loadCustomerFromJSON(customerData);
    }


    /**
     * @param userToken the user token the user received when logging in
     * @return A constructed user object linked to that token.
     */
    @Override
    public User getUserFromToken(String userToken) {
        String info = tokenSigner.getIdFromToken(userToken);
        String userId = info.split(",")[0];
        if(userId.contains("ERROR")){
            return null;
        }
        return read(userId);
    }

    /**
     * @param username the user's username
     * @param password the user's password
     * @return Token associated with that user session
     */
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

    /**
     * @param customer Customer entity that we want to create a JSON of
     * @return The JSONObject representation of that object
     */
    public static JSONObject loadJSONFromCustomer(Customer customer){
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("id", customer.getId());
        jsonObject.put("cart", CartDB.loadJSONFromCart(customer.getCurrentCart()));
        jsonObject.put("password", customer.getHashedPassword());
        jsonObject.put("username",customer.getUserName());
        return jsonObject;
    }


    /**
     * @param jsonObject JSON Object representation of Customer
     * @return A constructed customer object
     */
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
