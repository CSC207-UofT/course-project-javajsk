package businessrules.cart.usecases;

import businessrules.cart.inputboundaries.ClearCartInputBoundary;
import businessrules.dai.CustomerRepository;
import businessrules.loaders.CustomerLoader;
import businessrules.outputboundary.CustomerModel;
import entities.Cart;
import entities.Customer;
import org.json.JSONObject;

import java.util.HashMap;

public class ClearCartUseCase implements ClearCartInputBoundary {

    CustomerRepository customerRepository;
    CustomerModel customerModel;
    CustomerLoader customerLoader;

    public ClearCartUseCase(CustomerRepository customerRepository, CustomerModel customerModel) {
        this.customerRepository = customerRepository;
        this.customerModel = customerModel;
        this.customerLoader = new CustomerLoader(customerRepository);
    }

    @Override
    public JSONObject clearCart(String customerToken) {
        Customer customer = customerLoader.loadCustomerFromToken(customerToken);
        if (customer == null){
            return customerModel.displayError("Invalid customer token");
        }
        Cart cart = customer.getCurrentCart();
        cart.setContents(new HashMap<>());
        customer.setCurrentCart(cart);
        JSONObject customerData = customer.jsonify();
        customerRepository.updateCustomer(customer.getId(), customerData);
        return customerModel.displayCustomer(customerData);
    }
}
