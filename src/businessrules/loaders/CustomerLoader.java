package businessrules.loaders;

import businessrules.dai.CustomerRepository;
import entities.*;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;

public class CustomerLoader {
    CustomerRepository customerRepository;

    /**
     * Instantiates a customer loader
     * @param cR the customer repository
     */
    public CustomerLoader(CustomerRepository cR) {
        this.customerRepository = cR;
    }

    /**
     * Creates a customer object with repository data
     * @param data customer data
     * @return the customer object
     * @throws JSONException when the format of data is incorrect
     */
    public static Customer loadCustomer(JSONObject data) throws JSONException{
        String id = data.getString("id");
        String username = data.getString("username");
        String password = data.getString("password");
        Cart cart = (Cart) data.get("currCart");
        return new Customer(id, username, password, new OrderBook(new ArrayList<>()), cart);
    }

    /**
     * Creates customer object from a customer token
     * @param customerToken customer token
     * @return the customer object
     */
    public Customer loadCustomerFromToken(String customerToken) throws JSONException {

        JSONObject customerData = customerRepository.readUserFromToken(customerToken);

        if(customerData == null){
//            errorHandler.displayError("Unable to find customer with such token.");
            return null;
        }

        try{
            return CustomerLoader.loadCustomer(customerData);
        }catch( JSONException e){
//            errorHandler.displayError("Unable to load customer from repository information.");
        }
        return null;
    }
}
