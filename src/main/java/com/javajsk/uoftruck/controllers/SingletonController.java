package com.javajsk.uoftruck.controllers;


import adapters.dam.AddonDB;
import adapters.dam.SingletonDB;
import adapters.dam.VendorDB;
import businessrules.dai.VendorRepository;

import businessrules.outputboundaries.ObjectBoundary;
import businessrules.outputboundaries.RepositoryBoundary;
import businessrules.outputboundaries.ResponseObject;
import businessrules.outputboundaries.VendorBoundary;
import businessrules.singleton.inputboundaries.CreateSingleton;
import businessrules.singleton.inputboundaries.GetShopSingletons;
import businessrules.singleton.inputboundaries.ModifySingleton;
import businessrules.singleton.usecases.CreateSingletonInteractor;
import businessrules.singleton.usecases.GetShopSingletonsInteractor;
import businessrules.singleton.usecases.ModifySingletonInteractor;
import entities.Singleton;
import framework.MongoDB;
import org.json.JSONObject;
import org.springframework.web.bind.annotation.*;

import adapters.presenters.ObjectPresenter;
import adapters.presenters.RepositoryPresenter;
import adapters.presenters.VendorPresenter;

/**
 * Controller for singleton use cases
 */
@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class SingletonController {

    /**
     * The Create singleton input boundary.
     */
    CreateSingleton createSingleton;
    /**
     * The Get shop singletons input boundary.
     */
    GetShopSingletons getShopSingletons;

    /**
     * The Vendor repository input boundary.
     */
    VendorRepository vendorRepository;
    /**
     * The Modify singleton input boundary.
     */
    ModifySingleton modifySingleton;
    /**
     * The database.
     */
    MongoDB db;
    /**
     * The Singleton repository.
     */
    SingletonDB singletonRepository;
    /**
     * The Addon repository.
     */
    AddonDB addonRepository;
    /**
     * The Repository output boundary.
     */
    RepositoryBoundary repositoryBoundary = new RepositoryPresenter();
    /**
     * The Singleton object output boundary.
     */
    ObjectBoundary<Singleton> singletonObjectBoundary = new ObjectPresenter<>();
    /**
     * The Vendor output boundary.
     */
    VendorBoundary vendorBoundary = new VendorPresenter();

    /**
     * Instantiates a new Singleton controller.
     */
    public SingletonController() {
        this.db = new MongoDB();
        this.vendorRepository = new VendorDB(db);
        this.singletonRepository = new SingletonDB(db);
        this.addonRepository = new AddonDB(db);
        this.createSingleton = new CreateSingletonInteractor(vendorRepository, repositoryBoundary,
                                                            singletonRepository, singletonObjectBoundary,
                                                            vendorBoundary);
        this.getShopSingletons = new GetShopSingletonsInteractor(singletonRepository, singletonObjectBoundary);
        this.modifySingleton = new ModifySingletonInteractor(vendorRepository, singletonRepository,
                repositoryBoundary, vendorBoundary, singletonObjectBoundary);
    }

    /**
     * Runs create singleton use case.
     *
     * @param vendorToken  the vendor token
     * @param singletonStr the singleton represented as a string
     * @return response object containing data to display to user
     */
    @PostMapping("/CreateSingleton/{vendorToken}")
    public ResponseObject runCreateSingleton(@PathVariable String vendorToken, @RequestBody String singletonStr){

        JSONObject singletonJson = new JSONObject(singletonStr);

        Singleton singleton = singletonRepository.loadSingletonFromJSON(singletonJson);
        return createSingleton.createSingleton(vendorToken, singleton);
    }

    /**
     * Runs get shop singletons use case.
     *
     * @param shopId the shop id
     * @return response object containing data to display to user
     */
    @GetMapping("/GetShopSingletons/{shopId}")
    public ResponseObject runGetShopSingletons(@PathVariable String shopId){
        return getShopSingletons.getShopSingletons(shopId);
    }

    /**
     * Runs modify singleton use case.
     *
     * @param vendorToken  the vendor token
     * @param singletonId  the singleton id
     * @param singletonStr the singleton str
     * @return response object containing data to display to user
     */
    @PutMapping("/ModifySingleton/{vendorToken}/{singletonId}")
    public ResponseObject runModifySingleton(@PathVariable String vendorToken, @PathVariable String singletonId,
                                        @RequestBody String singletonStr){
        JSONObject singletonJson = new JSONObject(singletonStr);
        Singleton singleton = singletonRepository.loadSingletonFromJSON(singletonJson);
        return modifySingleton.modifySingleton(vendorToken, singletonId, singleton);
    }
}
