package businessrules.cart.usecases;

import businessrules.cart.inputboundaries.ReadCartInputBoundary;
import businessrules.loaders.CustomerLoader;
import businessrules.outputboundary.CartModel;
import entities.Cart;
import entities.Customer;
import org.json.JSONException;
import org.json.JSONObject;

public class ReadCartUseCase implements ReadCartInputBoundary {

    CartModel cartModel;
    CustomerLoader customerLoader;

    public ReadCartUseCase(CartModel cM, CustomerLoader cL) {
        this.cartModel = cM;
        this.customerLoader = cL;
    }

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
