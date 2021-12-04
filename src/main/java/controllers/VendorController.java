package controllers;

import businessrules.vendor.inputboundaries.VendorLogin;
import businessrules.vendor.inputboundaries.VendorSignUp;
import businessrules.vendor.inputboundaries.ModifyVendor;
import businessrules.outputboundaries.ResponseObject;
import entities.Vendor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

public class VendorController {

    VendorLogin vendorLogin;
    VendorSignUp vendorSignUp;
    ModifyVendor modifyVendor;

    public VendorController(VendorLogin vendorLogin, VendorSignUp vendorSignUp, ModifyVendor modifyVendor) {

        this.vendorLogin = vendorLogin;
        this.vendorSignUp = vendorSignUp;
        this.modifyVendor = modifyVendor;
    }
    @PutMapping("/VendorLogin/{username}/{password}")
    public ResponseObject runVendorLogin(@PathVariable String username, @PathVariable String password){
        return vendorLogin.login(username, password);
    }
    @PutMapping("/VendorLogin/{username}/{password}/{confirmed_password}/{shop_name}/{location}")
    public ResponseObject runVendorSignup(@PathVariable String username, @PathVariable String password,
                                  @PathVariable String confirmed_password, @PathVariable String location,
                                  @PathVariable String shop_name){
        return vendorSignUp.signUp(username, password, confirmed_password, shop_name, location);
    }
    @PutMapping("/ModifyVendor/{userToken}/{username}/{password}/{confirmed_password}")
    public ResponseObject runModifyVendor(@PathVariable String username, @PathVariable String password,
                                      @PathVariable String confirmed_password, @PathVariable String userToken){
        return modifyVendor.modifyVendor(userToken, username, password, confirmed_password);

    }
}
