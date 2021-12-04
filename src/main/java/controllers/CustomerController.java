package controllers;

import businessrules.customer.inputboundaries.CustomerLogin;
import businessrules.customer.inputboundaries.CustomerSignUp;
import businessrules.customer.inputboundaries.ModifyCustomer;
import businessrules.outputboundaries.ResponseObject;
import entities.Customer;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

public class CustomerController {

    CustomerLogin customerLogin;
    CustomerSignUp customerSignUp;
    ModifyCustomer modifyCustomer;

    public CustomerController(CustomerLogin customerLogin, CustomerSignUp customerSignUp, ModifyCustomer modifyCustomer) {

        this.customerLogin = customerLogin;
        this.customerSignUp = customerSignUp;
        this.modifyCustomer = modifyCustomer;
    }
    @PutMapping("/CustomerLogin/{username}/{password}")
    public Customer runCustomerLogin(@PathVariable String username, @PathVariable String password){
        ResponseObject response = customerLogin.login(username, password);
        return (Customer) response.getContents();
    }
    @PutMapping("/CustomerLogin/{username}/{password}/{confirmed_password}")
    public Customer runCustomerSignup(@PathVariable String username, @PathVariable String password,
                                      @PathVariable String confirmed_password){
        ResponseObject response = customerSignUp.signUp(username, password, confirmed_password);
        return (Customer) response.getContents();
    }
    @PutMapping("/ModifyCustomer/{userToken}/{username}/{password}/{confirmed_password}")
    public Customer runModifyCustomer(@PathVariable String username, @PathVariable String password,
                                      @PathVariable String confirmed_password, @PathVariable String userToken){
        ResponseObject response = modifyCustomer.modify(userToken, username, password, confirmed_password);
        return (Customer) response.getContents();

    }
}
