package businessrules.customer.usecases;

import businessrules.customer.inputboundaries.SignUpCustomerInputBoundary;
import businessrules.dai.CustomerRepository;
import businessrules.dai.Hasher;
import businessrules.outputboundary.CustomerModel;
import businessrules.outputboundary.ErrorModel;
import entities.Cart;
import entities.Customer;
import entities.Order;
import entities.OrderBook;
import org.json.JSONObject;

public class SignUpUseCase implements SignUpCustomerInputBoundary {
    CustomerRepository customerRepository;
    ErrorModel errorHandler;
    Hasher passwordHasher;
    CustomerModel customerModel;

    public SignUpUseCase(CustomerRepository customerRepository, ErrorModel errorHandler, Hasher passwordHasher,
                         CustomerModel customerModel) {
        this.customerRepository = customerRepository;
        this.errorHandler = errorHandler;
        this.passwordHasher = passwordHasher;
        this.customerModel = customerModel;
    }

    @Override
    public boolean signUp(JSONObject data) {
        String username = data.getString("username");
        String password = data.getString("password");
        String passwordConfirmation = data.getString("passwordConfirmation");


        if(!customerRepository.isUsernameUnique(username)){
            errorHandler.displayError("Username is already taken!");
            return false;
        }

        if(!password.equals(passwordConfirmation)){
            errorHandler.displayError("Passwords must match!");
            return false;
        }

        // Strategy design pattern.
        String hashedPassword = passwordHasher.hash(password);

        Customer customer = new Customer("", username, password, new OrderBook(), null);

        if(!customerRepository.createCustomer(username, hashedPassword)){
            errorHandler.displayError("Unable to save customer information in repository.");
        }

        customerModel.displayCustomer(customer.jsonify());
        return true;


    }
}
