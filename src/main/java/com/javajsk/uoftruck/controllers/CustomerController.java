package com.javajsk.uoftruck.controllers;

import businessrules.customer.inputboundaries.CustomerLogin;
import businessrules.customer.inputboundaries.CustomerSignUp;
import businessrules.customer.inputboundaries.ModifyCustomer;
import businessrules.outputboundaries.ResponseObject;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CustomerController {

    CustomerLogin customerLogin;
    CustomerSignUp customerSignUp;
    ModifyCustomer modifyCustomer;


    @PutMapping("/CustomerLogin/{username}/{password}")
    public ResponseObject runCustomerLogin(@PathVariable String username, @PathVariable String password){
        return customerLogin.login(username, password);
    }
    @PutMapping("/CustomerLogin/{username}/{password}/{confirmed_password}")
    public ResponseObject runCustomerSignup(@PathVariable String username, @PathVariable String password,
                                      @PathVariable String confirmed_password){
        return customerSignUp.signUp(username, password, confirmed_password);
    }
    @PutMapping("/ModifyCustomer/{userToken}/{username}/{password}/{confirmed_password}")
    public ResponseObject runModifyCustomer(@PathVariable String username, @PathVariable String password,
                                      @PathVariable String confirmed_password, @PathVariable String userToken){
        return modifyCustomer.modify(userToken, username, password, confirmed_password);

    }
}
