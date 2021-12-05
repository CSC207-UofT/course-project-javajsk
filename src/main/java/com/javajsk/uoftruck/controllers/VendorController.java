package com.javajsk.uoftruck.controllers;

import businessrules.vendor.inputboundaries.VendorLogin;
import businessrules.vendor.inputboundaries.VendorSignUp;
import businessrules.vendor.inputboundaries.ModifyVendor;
import businessrules.outputboundaries.ResponseObject;
import businessrules.vendor.usecases.ModifyVendorInteractor;
import businessrules.vendor.usecases.VendorLoginInteractor;
import businessrules.vendor.usecases.VendorSignUpInteractor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class VendorController {

    VendorLogin vendorLogin;
    VendorSignUp vendorSignUp;
    ModifyVendor modifyVendor;

    public VendorController() {
        this.vendorLogin = new VendorLoginInteractor();
        this.vendorSignUp = new VendorSignUpInteractor();
        this.modifyVendor = new ModifyVendorInteractor();
    }
    @PutMapping("/VendorLogin/{username}/{password}")
    public ResponseObject runVendorLogin(@PathVariable String username, @PathVariable String password){
        return vendorLogin.login(username, password);
    }
    @PutMapping("/VendorSignUp/{username}/{password}/{confirmed_password}/{shop_name}/{location}")
    public ResponseObject runVendorSignup(@PathVariable String username, @PathVariable String password,
                                  @PathVariable String confirmed_password, @PathVariable String location,
                                  @PathVariable String shop_name){
        System.out.println("Controller running");
        return vendorSignUp.signUp(username, password, confirmed_password, shop_name, location);
    }
    @PutMapping("/ModifyVendor/{userToken}/{username}/{password}/{confirmed_password}")
    public ResponseObject runModifyVendor(@PathVariable String username, @PathVariable String password,
                                      @PathVariable String confirmed_password, @PathVariable String userToken){
        return modifyVendor.modifyVendor(userToken, username, password, confirmed_password);

    }
}
