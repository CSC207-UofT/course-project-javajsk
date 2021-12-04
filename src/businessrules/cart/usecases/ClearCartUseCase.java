package businessrules.cart.usecases;

import businessrules.cart.inputboundaries.ClearCartInputBoundary;
import businessrules.dai.CustomerRepository;
import businessrules.loaders.CustomerLoader;
import businessrules.outputboundary.CartModel;
import businessrules.outputboundary.CustomerModel;
import entities.Cart;
import entities.Customer;
import org.json.JSONObject;

import java.util.HashMap;

/**
 * Use Case for clearing a cart
 */
public class ClearCartUseCase implements ClearCartInputBoundary {

    CustomerRepository customerRepository;
    CartModel cartModel;
    CustomerLoader customerLoader;

    /**
     * Instantiates a ClearCartUseCase
     * @param cR customer repository
     * @param cM cart model
     * @param cL customer loader
     */
    public ClearCartUseCase(CustomerRepository cR, CartModel cM, CustomerLoader cL) {
        this.customerRepository = cR;
        this.cartModel = cM;
        this.customerLoader = cL;
    }

    /**
     * A method for emptying the data in cart
     * @param customerToken token of current customer
     * @return JSON object containing error information or cart with empty components
     */
    @Override
    public JSONObject clearCart(String customerToken) {
        Customer customer = customerLoader.loadCustomerFromToken(customerToken);
        if (customer == null){
            return cartModel.displayError("Invalid customer token");
        }
        Cart cart = customer.getCurrentCart();
        cart.setContents(new HashMap<>());
        customer.setCurrentCart(cart);
        JSONObject customerData = customer.jsonify();
        if(!customerRepository.updateCustomer(customer.getId(), customerData)){
            return cartModel.displayError("Could not update customer's cart in the repository");
        }
        return cartModel.displayCart(cart.jsonify());
    }
}
