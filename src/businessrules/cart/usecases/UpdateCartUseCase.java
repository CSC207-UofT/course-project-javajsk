package businessrules.cart.usecases;

import businessrules.cart.inputboundaries.UpdateCartInputBoundary;
import businessrules.dai.AddonRepository;
import businessrules.dai.CustomerRepository;
import businessrules.dai.FoodRepository;
import businessrules.loaders.CartLoader;
import businessrules.loaders.CustomerLoader;
import businessrules.outputboundary.CartModel;
import businessrules.outputboundary.CustomerModel;
import entities.Cart;
import entities.Customer;
import org.json.JSONObject;

/**
 * Use case for updating cart
 */
public class UpdateCartUseCase implements UpdateCartInputBoundary {

    CustomerRepository customerRepository;
    CartModel cartModel;
    CustomerLoader customerLoader;
    CartLoader cartLoader;

    /**
     * Instantiates an UpdateCartUseCase
     * @param cR customer repository
     * @param cM cart model
     * @param cusL customer loader
     * @param cL cart loader
     */
    public UpdateCartUseCase(CustomerRepository cR, CartModel cM, CustomerLoader cusL, CartLoader cL) {
        this.customerRepository = cR;
        this.cartModel = cM;
        this.customerLoader = cusL;
        this.cartLoader = cL;
    }

    /**
     * A method that updates the given customer's cart with the new cart information
     * @param customerToken token of current customer
     * @param newCart information for new cart
     * @return JSON object containing updated cart information or error message information
     */
    @Override
    public JSONObject updateCart(String customerToken, JSONObject newCart) {
        Customer customer = customerLoader.loadCustomerFromToken(customerToken);
        Cart cart = cartLoader.loadCart(newCart);
        if(customer == null){
            return cartModel.displayError("Invalid customer token");
        }
        customer.setCurrentCart(cart);
        JSONObject customerData = customer.jsonify();

        if(!customerRepository.updateCustomer(customer.getId(), customerData)){
            return cartModel.displayError("Unable to update customer in repository");
        }

        return cartModel.displayCart(cart.jsonify());
    }
}
