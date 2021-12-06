package com.javajsk.uoftruck.controllers;

import adapters.dam.entityrepoitories.SingletonDB;
import adapters.dam.entityrepoitories.VendorDB;
import businessrules.dai.Repository;
import businessrules.dai.VendorRepository;
import businessrules.outputboundaries.RepositoryBoundary;
import businessrules.outputboundaries.ResponseObject;
import businessrules.singleton.inputboundaries.CreateSingleton;
import businessrules.singleton.inputboundaries.GetShopSingletons;
import businessrules.singleton.inputboundaries.ModifySingleton;
import businessrules.singleton.usecases.CreateSingietonInteractor;
import entities.Singleton;
import framework.MongoDB;
import org.json.JSONObject;
import org.springframework.web.bind.annotation.*;
@RestController
public class SingletonController {

    CreateSingleton createSingleton;
    GetShopSingletons getShopSingletons;

    VendorRepository vendorRepository;
    ModifySingleton modifySingleton;
    RepositoryBoundary repositoryBoundary;
    MongoDB db = new MongoDB();

    Repository<Singleton> singletonRepository = new SingletonDB(db);

    SingletonDB sdb = new SingletonDB(db);

    public SingletonController() {
        this.getShopSingletons = getShopSingletons;
        this.modifySingleton = modifySingleton;
    }

    @PostMapping("/CreateSingleton/{vendorToken}")
    public ResponseObject runCreateSingleton(@PathVariable String vendorToken, @RequestBody String singleton){
        Singleton singleton1 = sdb.loadSingletonFromJSON(new JSONObject(singleton));
        return createSingleton.createSingleton(vendorToken, singleton1);
    }

    @GetMapping("/GetShopSingletons/{shopId}")
    public ResponseObject runGetShopSingletons(@PathVariable String shopId){
        return getShopSingletons.getShopSingletons(shopId);
    }

    @PutMapping("/ModifySingleton/{vendorToken}/{singletonId}")
    public ResponseObject runModifySingleton(@PathVariable String vendorToken, @PathVariable String singletonId,
                                        @RequestBody Singleton singleton){
        return modifySingleton.modifySingleton(vendorToken, singletonId, singleton);
    }
}
