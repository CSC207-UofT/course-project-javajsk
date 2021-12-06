package com.javajsk.uoftruck.controllers;


import adapters.dam.DBGateway;
import adapters.dam.entityrepoitories.AddonDB;
import adapters.dam.entityrepoitories.SingletonDB;
import adapters.dam.entityrepoitories.VendorDB;
import businessrules.dai.Repository;
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

import presenters.ObjectPresenter;
import presenters.RepositoryPresenter;
import presenters.VendorPresenter;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class SingletonController {

    CreateSingleton createSingleton;
    GetShopSingletons getShopSingletons;

    VendorRepository vendorRepository;
    ModifySingleton modifySingleton;

    MongoDB db;
    SingletonDB singletonRepository;
    AddonDB addonRepository;
    RepositoryBoundary repositoryBoundary = new RepositoryPresenter();
    ObjectBoundary<Singleton> singletonObjectBoundary = new ObjectPresenter<>();
    VendorBoundary vendorBoundary = new VendorPresenter();

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

    @PostMapping("/CreateSingleton/{vendorToken}")
    public ResponseObject runCreateSingleton(@PathVariable String vendorToken, @RequestBody String singletonStr){
        System.out.println(singletonStr);

        JSONObject singletonJson = new JSONObject(singletonStr);

        Singleton singleton = singletonRepository.loadSingletonFromJSON(singletonJson);
        return createSingleton.createSingleton(vendorToken, singleton);
    }

    @GetMapping("/GetShopSingletons/{shopId}")
    public ResponseObject runGetShopSingletons(@PathVariable String shopId){
        return getShopSingletons.getShopSingletons(shopId);
    }

    @PutMapping("/ModifySingleton/{vendorToken}/{singletonId}")
    public ResponseObject runModifySingleton(@PathVariable String vendorToken, @PathVariable String singletonId,
                                        @RequestBody String singletonStr){
        JSONObject singletonJson = new JSONObject(singletonStr);
        Singleton singleton = singletonRepository.loadSingletonFromJSON(singletonJson);
        return modifySingleton.modifySingleton(vendorToken, singletonId, singleton);
    }
}
