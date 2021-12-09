package com.javajsk.uoftruck.controllers;

import adapters.SHA512Hasher;
import adapters.dam.CustomerDB;
import adapters.dam.FoodDB;
import businessrules.customer.inputboundaries.CustomerLogin;
import businessrules.customer.inputboundaries.CustomerSignUp;
import businessrules.customer.inputboundaries.ModifyCustomer;
import businessrules.customer.inputboundaries.ViewCustomer;
import businessrules.customer.usecases.CustomerLoginInteractor;
import businessrules.customer.usecases.CustomerSignUpInteractor;
import businessrules.customer.usecases.ModifyCustomerInteractor;
import businessrules.customer.usecases.ViewCustomerInteractor;
import businessrules.dai.CustomerRepository;
import businessrules.dai.Hasher;
import businessrules.dai.Repository;
import businessrules.outputboundaries.*;
import entities.Customer;
import entities.Food;
import framework.MongoDB;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;
import adapters.presenters.CustomerPresenter;
import adapters.presenters.ObjectPresenter;
import adapters.presenters.RepositoryPresenter;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class CustomerController {

    CustomerLogin customerLogin;
    CustomerSignUp customerSignUp;
    ModifyCustomer modifyCustomer;

    ViewCustomer viewCustomer;

    MongoDB db;
    Repository<Food> foodRepository;
    CustomerRepository customerRepository;
    CustomerBoundary customerBoundary = new CustomerPresenter();
    RepositoryBoundary repositoryBoundary = new RepositoryPresenter();
    Hasher hasher = new SHA512Hasher();
    ObjectBoundary<Customer> customerObjectBoundary = new ObjectPresenter<>();

    public CustomerController() {
        this.db = new MongoDB();
        this.foodRepository = new FoodDB(db);
        this.customerRepository = new CustomerDB(db);
        this.customerLogin = new CustomerLoginInteractor(customerRepository,
                customerBoundary, repositoryBoundary,hasher);
        this.customerSignUp = new CustomerSignUpInteractor(customerRepository,
                repositoryBoundary, customerBoundary, customerObjectBoundary, hasher);
        this.modifyCustomer = new ModifyCustomerInteractor(customerRepository,
                customerObjectBoundary, repositoryBoundary, customerBoundary, hasher);



        this.viewCustomer = new ViewCustomerInteractor(customerRepository,customerObjectBoundary);
    }

    /**
     * @param username Username of customer trying to login
     * @param password Password of customer trying to login
     * @return A ResponseObject with the customer token associated with that log in session, with status codes
     */
    @PutMapping("/CustomerLogin/{username}/{password}")
    public ResponseObject runCustomerLogin(@PathVariable String username, @PathVariable String password){
        return customerLogin.login(username, password);
    }

    /**
     * @param username Username of customer trying to make a new account
     * @param password Password of customer trying to make a new account
     * @param confirmed_password Password confirmation of customer trying to make a new account
     * @return A ResponseObject with the customer object just created, with status codes
     */
    @PutMapping("/CustomerSignUp/{username}/{password}/{confirmed_password}")
    public ResponseObject runCustomerSignup(@PathVariable String username, @PathVariable String password,
                                      @PathVariable String confirmed_password){
        return customerSignUp.signUp(username, password, confirmed_password);
    }

    /**
     * @param username New Username of customer trying to change account details
     * @param password New password of customer trying to change account details
     * @param confirmed_password New confirmed_password of customer trying to change account details
     * @param userToken usertoken of user trying to change details, to make sure they're logged in first
     * @return A ResponseObject with the new customer object just modified, with status codes
     */
    @PutMapping("/ModifyCustomer/{userToken}/{username}/{password}/{confirmed_password}")
    public ResponseObject runModifyCustomer(@PathVariable String username, @PathVariable String password,
                                      @PathVariable String confirmed_password, @PathVariable String userToken){
        return modifyCustomer.modify(userToken, username, password, confirmed_password);

    }

    /**
     * @param customerId id of the customer wanting to be viewed
     * @return A ResponseObject with the customer object with the given id, with status codes
     */
    @GetMapping("/ViewCustomer/{customerId}")
    public ResponseObject viewCustomer(@PathVariable String customerId){
        return viewCustomer.viewCustomer(customerId);
    }
}
