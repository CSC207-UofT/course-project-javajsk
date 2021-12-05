package com.javajsk.uoftruck.controllers;

import adapters.dam.SHA512Hasher;
import adapters.dam.entityrepoitories.ShopDB;
import adapters.dam.entityrepoitories.VendorDB;
import businessrules.dai.Hasher;
import businessrules.dai.Repository;
import businessrules.dai.VendorRepository;
import businessrules.outputboundaries.ObjectBoundary;
import businessrules.outputboundaries.RepositoryBoundary;
import businessrules.outputboundaries.VendorBoundary;
import businessrules.vendor.inputboundaries.VendorLogin;
import businessrules.vendor.inputboundaries.VendorSignUp;
import businessrules.vendor.inputboundaries.ModifyVendor;
import businessrules.outputboundaries.ResponseObject;
import businessrules.vendor.usecases.ModifyVendorInteractor;
import businessrules.vendor.usecases.VendorLoginInteractor;
import businessrules.vendor.usecases.VendorSignUpInteractor;
import entities.Shop;
import entities.Vendor;
import framework.MongoDB;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;
import presenters.ObjectPresenter;
import presenters.RepositoryPresenter;
import presenters.VendorPresenter;

@RestController
public class VendorController {
    VendorLogin vendorLogin;
    VendorSignUp vendorSignUp;
    ModifyVendor modifyVendor;
    MongoDB db = new MongoDB();
    VendorRepository vendorRepository = new VendorDB(db);
    Hasher hasher = new SHA512Hasher();
    RepositoryBoundary repositoryBoundary = new RepositoryPresenter();
    ObjectBoundary<Vendor> vendorObjectBoundary = new ObjectPresenter<Vendor>();
    Repository<Shop> shopRepository = new ShopDB(db);
    VendorBoundary vendorBoundary = new VendorPresenter();


    public VendorController() {
        this.vendorLogin = new VendorLoginInteractor(vendorRepository, vendorBoundary, repositoryBoundary, hasher);
        this.vendorSignUp = new VendorSignUpInteractor(vendorRepository, hasher, repositoryBoundary,
                vendorObjectBoundary, shopRepository, vendorBoundary);
        this.modifyVendor = new ModifyVendorInteractor(vendorRepository, repositoryBoundary, vendorBoundary,
                vendorObjectBoundary, hasher);
    }
    @PutMapping("/VendorLogin/{username}/{password}")
    public ResponseObject runVendorLogin(@PathVariable String username, @PathVariable String password){
        return vendorLogin.login(username, password);
    }
    @PutMapping("/VendorSignUp/{username}/{password}/{confirmed_password}/{shop_name}/{location}")
    public ResponseObject runVendorSignup(@PathVariable String username, @PathVariable String password,
                                          @PathVariable String confirmed_password, @PathVariable String location,
                                          @PathVariable String shop_name){
        return vendorSignUp.signUp(username, password, confirmed_password, shop_name, location);
    }
    @PutMapping("/ModifyVendor/{userToken}/{username}/{password}/{confirmed_password}")
    public ResponseObject runModifyVendor(@PathVariable String username, @PathVariable String password,
                                          @PathVariable String confirmed_password, @PathVariable String userToken){
        return modifyVendor.modifyVendor(userToken, username, password, confirmed_password)
    }
}
