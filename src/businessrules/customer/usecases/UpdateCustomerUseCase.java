package businessrules.customer.usecases;

import businessrules.customer.inputboundaries.UpdateCustomerInputBoundary;
import businessrules.dai.CustomerRepository;
import businessrules.loaders.CartLoader;
import businessrules.loaders.OrderBookLoader;
import businessrules.outputboundary.CustomerModel;
import businessrules.outputboundary.ErrorModel;
import entities.Cart;
import entities.Customer;
import entities.OrderBook;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * The type Update customer use case.
 */
public class UpdateCustomerUseCase implements UpdateCustomerInputBoundary {
    /**
     * The Customer repository.
     */
    CustomerRepository customerRepository;
    /**
     * The Error handler.
     */
    ErrorModel errorHandler;
    /**
     * The Customer model.
     */
    CustomerModel customerModel;

    /**
     * Instantiates a new Update customer use case.
     *
     * @param customerRepository the customer repository
     * @param errorHandler       the error handler
     * @param customerModel      the customer model
     */
    public UpdateCustomerUseCase(CustomerRepository customerRepository, ErrorModel errorHandler,
                                 CustomerModel customerModel) {
        this.customerRepository = customerRepository;
        this.errorHandler = errorHandler;
        this.customerModel = customerModel;
    }

    @Override
    public boolean updateCustomer(String userToken, JSONObject newData) {
        JSONObject customerInfo = customerRepository.readUserFromToken(userToken);

        if(!customerInfo.getString("id").equals(newData.getString("id"))){
            errorHandler.displayError("Incorrect id value passed.");
            return false;
        }

        Customer customer;
        try{
            String id = newData.getString("id");
            String username = newData.getString("username");
            String password = newData.getString("password");
            Cart cart = CartLoader.loadCart(newData.getJSONObject("cart"));
            if(cart == null){
                errorHandler.displayError("Cart was unable to load.");
                return false;
            }
            OrderBook book = OrderBookLoader.loadOrderBook(newData.getJSONObject("orderhistory"));

            customer = new Customer(id,username,password,book, cart);


        }catch (JSONException e){
            errorHandler.displayError("Unable to generate customer object.");
            return false;
        }

        if(!customerRepository.updateCustomer(customer.getId(), customer.jsonify())){
            errorHandler.displayError("Failed to update customer object in repository.");
            return false;
        }

        customerModel.updateCustomer(customer.jsonify());
        return true;
    }
}
