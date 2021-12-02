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

public class UpdateCartUseCase implements UpdateCartInputBoundary {

    CustomerRepository customerRepository;
    CartModel cartModel;
    CustomerLoader customerLoader;
    CartLoader cartLoader;

    public UpdateCartUseCase(CustomerRepository cR, CartModel cM, CustomerLoader cusL, CartLoader cL) {
        this.customerRepository = cR;
        this.cartModel = cM;
        this.customerLoader = cusL;
        this.cartLoader = cL;
    }

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
