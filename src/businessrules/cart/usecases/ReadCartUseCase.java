package businessrules.cart.usecases;

import businessrules.cart.inputboundaries.ReadCartInputBoundary;
import businessrules.loaders.CustomerLoader;
import businessrules.outputboundary.CartModel;
import entities.Cart;
import entities.Customer;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Use case for reading cart information - indicates the methods and parameters that ClearCartUseCase must implement
 */
public class ReadCartUseCase implements ReadCartInputBoundary {

    CartModel cartModel;
    CustomerLoader customerLoader;

    /**
     * Instantiates and read cart use case
     * @param cM a cart model
     * @param cL a customer loader
     */
    public ReadCartUseCase(CartModel cM, CustomerLoader cL) {
        this.cartModel = cM;
        this.customerLoader = cL;
    }

    /**
     * A method for reading the information contained in a given customer's cart
     * @param customerToken token of current customer
     * @return JSON object containing information on the customer's cart of error messages
     */
    @Override
    public JSONObject readCart(String customerToken) {
        Customer customer = customerLoader.loadCustomerFromToken(customerToken);
        Cart cart;
        try {
            cart = customer.getCurrentCart();
        }catch (JSONException e){
            return cartModel.displayError(e.getMessage());
        }
        return cartModel.displayCart(cart.jsonify());
    }
}
