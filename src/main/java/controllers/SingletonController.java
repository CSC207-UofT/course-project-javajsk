package controllers;

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
    public Singleton runCreateSingleton(@PathVariable String vendorToken, @RequestBody Singleton singleton){
        ResponseObject response = createSingleton.createSingleton(vendorToken, singleton);
        return (Singleton) response.getContents();
    }

    @GetMapping("/GetShopSingletons/{shopId}")
    public Singleton runGetShopSingletons(@PathVariable String shopId){
        ResponseObject response = getShopSingletons.getShopSingletons(shopId);
        return (Singleton) response.getContents();
    }

    @PutMapping("/ModifySingleton/{vendorToken}/{singletonId}")
    public Singleton runModifySingleton(@PathVariable String vendorToken, @PathVariable String singletonId,
                                        @RequestBody Singleton singleton){
        ResponseObject response = modifySingleton.modifySingleton(vendorToken, singletonId, singleton);
        return (Singleton) response.getContents();
    }
}
