package com.javajsk.uoftruck.controllers;

import adapters.SHA512Hasher;
import adapters.dam.ShopDB;
import adapters.dam.VendorDB;
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
import businessrules.vendor.inputboundaries.ViewVendor;
import businessrules.vendor.usecases.ModifyVendorInteractor;
import businessrules.vendor.usecases.VendorLoginInteractor;
import businessrules.vendor.usecases.VendorSignUpInteractor;
import businessrules.vendor.usecases.ViewVendorInteractor;
import entities.Shop;
import entities.Vendor;
import framework.MongoDB;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;
import adapters.presenters.ObjectPresenter;
import adapters.presenters.RepositoryPresenter;
import adapters.presenters.VendorPresenter;

/**
 * Controller for vendor use cases
 */
@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class VendorController {
    /**
     * The Vendor login input boundary.
     */
    VendorLogin vendorLogin;
    /**
     * The Vendor sign up input boundary.
     */
    VendorSignUp vendorSignUp;
    /**
     * The Modify vendor input boundary.
     */
    ModifyVendor modifyVendor;
    /**
     * The View vendor input boundary.
     */
    ViewVendor viewVendor;
    /**
     * The database.
     */
    MongoDB db = new MongoDB();
    /**
     * The Vendor repository.
     */
    VendorRepository vendorRepository = new VendorDB(db);
    /**
     * The Hasher.
     */
    Hasher hasher = new SHA512Hasher();
    /**
     * The Repository output boundary.
     */
    RepositoryBoundary repositoryBoundary = new RepositoryPresenter();
    /**
     * The Vendor object output boundary.
     */
    ObjectBoundary<Vendor> vendorObjectBoundary = new ObjectPresenter<>();
    /**
     * The Shop output repository.
     */
    Repository<Shop> shopRepository = new ShopDB(db);
    /**
     * The Vendor output boundary.
     */
    VendorBoundary vendorBoundary = new VendorPresenter();


    /**
     * Instantiates a new Vendor controller.
     */
    public VendorController() {
        this.vendorLogin = new VendorLoginInteractor(vendorRepository, vendorBoundary, repositoryBoundary, hasher);
        this.vendorSignUp = new VendorSignUpInteractor(vendorRepository, hasher, repositoryBoundary,
                vendorObjectBoundary, shopRepository, vendorBoundary);
        this.modifyVendor = new ModifyVendorInteractor(vendorRepository, repositoryBoundary, vendorBoundary,
                vendorObjectBoundary, hasher);
        this.viewVendor = new ViewVendorInteractor(vendorRepository, vendorObjectBoundary);
    }

    /**
     * Runs vendor login use cases.
     *
     * @param username the username
     * @param password the password
     * @return response object containing data to display to user
     */
    @PutMapping("/VendorLogin/{username}/{password}")
    public ResponseObject runVendorLogin(@PathVariable String username, @PathVariable String password){
        return vendorLogin.login(username, password);
    }

    /**
     * Runs vendor signup use cases.
     *
     * @param username           the username
     * @param password           the password
     * @param confirmed_password the confirmed password
     * @param location           the location
     * @param shop_name          the shop name
     * @return response object containing data to display to user
     */
    @PutMapping("/VendorSignUp/{username}/{password}/{confirmed_password}/{shop_name}/{location}")
    //TODO: We pass passwords through URLs which is obviously bad practice.
    public ResponseObject runVendorSignup(@PathVariable String username, @PathVariable String password,
                                          @PathVariable String confirmed_password, @PathVariable String location,
                                          @PathVariable String shop_name){
        return vendorSignUp.signUp(username, password, confirmed_password, shop_name, location);
    }

    /**
     * Runs modify vendor use case.
     *
     * @param username           the username
     * @param password           the password
     * @param confirmed_password the confirmed password
     * @param userToken          the user token
     * @return response object containing data to display to user
     */
    @PutMapping("/ModifyVendor/{userToken}/{username}/{password}/{confirmed_password}")
    public ResponseObject runModifyVendor(@PathVariable String username, @PathVariable String password,
                                          @PathVariable String confirmed_password, @PathVariable String userToken){
        return modifyVendor.modifyVendor(userToken, username, password, confirmed_password);
    }

    /**
     * Runs view vendor use case.
     *
     * @param vendorId the vendor id
     * @return response object containing data to display to user
     */
    @GetMapping("/ViewVendor/{vendorId}")
    public ResponseObject viewVendor(@PathVariable String vendorId){
        return viewVendor.viewVendor(vendorId);
    }
}
