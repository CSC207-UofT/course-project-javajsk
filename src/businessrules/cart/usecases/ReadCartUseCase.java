package businessrules.cart.usecases;

import businessrules.cart.inputboundaries.ReadCartInputBoundary;
import businessrules.loaders.CustomerLoader;
import businessrules.outputboundary.CustomerModel;
import entities.Cart;
import entities.Customer;
import org.json.JSONException;
import org.json.JSONObject;

public class ReadCartUseCase implements ReadCartInputBoundary {

    CustomerModel customerModel;
    CustomerLoader customerLoader;

    public ReadCartUseCase(CustomerModel cM, CustomerLoader cL) {
        this.customerModel = cM;
        this.customerLoader = cL;
    }

    @Override
    public JSONObject readCart(String customerToken) {
        Customer customer = customerLoader.loadCustomerFromToken(customerToken);
        Cart cart;
        try {
            cart = customer.getCurrentCart();
        }catch (JSONException e){
            return customerModel.displayError(e.getMessage());
        }
        return customerModel.displayCustomer(cart.jsonify());
    }
}
