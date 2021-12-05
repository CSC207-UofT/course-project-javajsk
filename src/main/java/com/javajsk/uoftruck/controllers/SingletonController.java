package com.javajsk.uoftruck.controllers;

import businessrules.outputboundaries.ResponseObject;
import businessrules.singleton.inputboundaries.CreateSingleton;
import businessrules.singleton.inputboundaries.GetShopSingletons;
import businessrules.singleton.inputboundaries.ModifySingleton;
import entities.Singleton;
import org.springframework.web.bind.annotation.*;

public class SingletonController {

    CreateSingleton createSingleton;
    GetShopSingletons getShopSingletons;
    ModifySingleton modifySingleton;

    public SingletonController(CreateSingleton createSingleton, GetShopSingletons getShopSingletons,
                               ModifySingleton modifySingleton) {
        this.createSingleton = createSingleton;
        this.getShopSingletons = getShopSingletons;
        this.modifySingleton = modifySingleton;
    }

    @PostMapping("/CreateSingleton/{vendorToken}")
    public ResponseObject runCreateSingleton(@PathVariable String vendorToken, @RequestBody Singleton singleton){
        return createSingleton.createSingleton(vendorToken, singleton);
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
