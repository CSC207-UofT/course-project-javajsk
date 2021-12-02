package businessrules.customer.usecases;

import businessrules.customer.inputboundaries.SignUpCustomerInputBoundary;
import businessrules.dai.CustomerRepository;
import businessrules.dai.Hasher;
import businessrules.outputboundary.CustomerModel;
import entities.Customer;
import entities.OrderBook;
import org.json.JSONObject;

public class SignUpUseCase implements SignUpCustomerInputBoundary {
    CustomerRepository customerRepository;
    Hasher passwordHasher;
    CustomerModel customerModel;

    public SignUpUseCase(CustomerRepository cR, Hasher pH, CustomerModel cM) {
        this.customerRepository = cR;
        this.passwordHasher = pH;
        this.customerModel = cM;
    }

    @Override
    public JSONObject signUp(JSONObject data) {
        String username = data.getString("username");
        String password = data.getString("password");
        String passwordConfirmation = data.getString("passwordConfirmation");


        if(!customerRepository.isUsernameUnique(username)){
            return customerModel.displayError("Username is already taken.");
        }

        if(!password.equals(passwordConfirmation)){
            return customerModel.displayError("Passwords do not match.");
        }

        // Strategy design pattern.
        String hashedPassword = passwordHasher.hash(password);

        Customer customer = new Customer("", username, password, new OrderBook(), null);

        if(!customerRepository.createCustomer(username, hashedPassword)){
            return customerModel.displayError("Unable to save customer information in repository.");
        }

        return customerModel.displayCustomer(customer.jsonify());
    }
}
