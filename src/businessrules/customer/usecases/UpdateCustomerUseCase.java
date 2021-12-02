package businessrules.customer.usecases;

import businessrules.customer.inputboundaries.UpdateCustomerInputBoundary;
import businessrules.dai.CustomerRepository;
import businessrules.loaders.CartLoader;
import businessrules.loaders.OrderBookLoader;
import businessrules.outputboundary.CustomerModel;
import entities.Cart;
import entities.Customer;
import entities.OrderBook;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * The type Update customer use case.
 */
public class UpdateCustomerUseCase implements UpdateCustomerInputBoundary {
    CustomerRepository customerRepository;
    CustomerModel customerModel;

    /**
     * Instantiates a new Update customer use case.
     *
     * @param cR the customer repository
     * @param cM      the customer model
     */
    public UpdateCustomerUseCase(CustomerRepository cR, CustomerModel cM) {
        this.customerRepository = cR;
        this.customerModel = cM;
    }

    @Override
    public JSONObject updateCustomer(String userToken, JSONObject newData) {
        JSONObject customerInfo = customerRepository.readUserFromToken(userToken);

        if(!customerInfo.getString("id").equals(newData.getString("id"))){
            return customerModel.displayError("Incorrect id value passed.");
        }

        Customer customer;
        try{
            String id = newData.getString("id");
            String username = newData.getString("username");
            String password = newData.getString("password");
            Cart cart = CartLoader.loadCart(newData.getJSONObject("cart"));
            if(cart == null){
                return customerModel.displayError("Cart was unable to load.");
            }
            OrderBook book = OrderBookLoader.loadOrderBook(newData.getJSONObject("orderhistory"));

            customer = new Customer(id,username,password,book, cart);

        }catch (JSONException e){
            return customerModel.displayError("Unable to generate customer object.");
        }

        if(!customerRepository.updateCustomer(customer.getId(), customer.jsonify())){
            return customerModel.displayError("Failed to update customer object in repository.");
        }

       return customerModel.displayCustomer(customer.jsonify());
    }
}
