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
    public Vendor runVendorLogin(@PathVariable String username, @PathVariable String password){
        ResponseObject response = vendorLogin.login(username, password);
        return (Vendor) response.getContents();
    }
    @PutMapping("/VendorLogin/{username}/{password}/{confirmed_password}/{shop_name}/{location}")
    public Vendor runVendorSignup(@PathVariable String username, @PathVariable String password,
                                  @PathVariable String confirmed_password, @PathVariable String location,
                                  @PathVariable String shop_name){
        ResponseObject response = vendorSignUp.signUp(username, password, confirmed_password, shop_name, location);
        return (Vendor) response.getContents();
    }
    @PutMapping("/ModifyVendor/{userToken}/{username}/{password}/{confirmed_password}")
    public Vendor runModifyVendor(@PathVariable String username, @PathVariable String password,
                                      @PathVariable String confirmed_password, @PathVariable String userToken){
        ResponseObject response = modifyVendor.modifyVendor(userToken, username, password, confirmed_password);
        return (Vendor) response.getContents();

    }
}
