package com.javajsk.uoftruck.controllers;

import adapters.dam.entityrepoitories.AddonDB;
import adapters.dam.entityrepoitories.VendorDB;
import businessrules.addon.inputboundaries.CreateAddon;
import businessrules.addon.inputboundaries.GetShopAddons;
import businessrules.addon.inputboundaries.ModifyAddon;
import businessrules.addon.usecases.CreateAddonInteractor;
import businessrules.addon.usecases.GetShopAddonsInteractor;
import businessrules.addon.usecases.ModifyAddonInteractor;
import businessrules.dai.Repository;
import businessrules.dai.VendorRepository;
import businessrules.outputboundaries.ObjectBoundary;
import businessrules.outputboundaries.RepositoryBoundary;
import businessrules.outputboundaries.ResponseObject;
import businessrules.outputboundaries.VendorBoundary;
import entities.Addon;
import framework.MongoDB;
import org.json.JSONObject;
import org.springframework.web.bind.annotation.*;
import presenters.ObjectPresenter;
import presenters.RepositoryPresenter;
import presenters.VendorPresenter;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class AddonController {
    CreateAddon createAddon;
    GetShopAddons getShopAddons;
    ModifyAddon modifyAddon;
    GetAddonTypes getAddonTypes;
    MongoDB db;
    AddonDB addonRepository;
    VendorRepository vendorRepository;
    VendorBoundary vendorBoundary = new VendorPresenter();
    RepositoryBoundary repositoryBoundary = new RepositoryPresenter();
    ObjectBoundary<Addon> addonObjectBoundary = new ObjectPresenter<Addon>();


    public AddonController() {
        this.db = new MongoDB();
        this.getAddonTypes = new GetAddonTypesInteractor();
        this.addonRepository = new AddonDB(db);
        this.vendorRepository = new VendorDB(db);
        this.createAddon = new CreateAddonInteractor(addonRepository, vendorRepository, vendorBoundary,
                repositoryBoundary, addonObjectBoundary);
        this.getShopAddons = new GetShopAddonsInteractor(addonRepository, repositoryBoundary, addonObjectBoundary);
        this.modifyAddon = new ModifyAddonInteractor(addonRepository, addonObjectBoundary,
                repositoryBoundary, vendorRepository, vendorBoundary);
    }

    @PostMapping("/CreateAddon/{vendorToken}")
    public ResponseObject runCreateAddon(@PathVariable String vendorToken, @RequestBody String addon){
        Addon addon1 = addonRepository.loadAddonFromJSON(new JSONObject(addon));
        return createAddon.createAddon(vendorToken, addon1);
    }

    @GetMapping("/GetShopAddons/{shopId}")
    public ResponseObject runGetShopAddons(@PathVariable String shopId){
        return getShopAddons.getShopAddons(shopId);
    }


    @GetMapping("/GetAddonTypes/")
    public ResponseObject runGetAddonTypes(){

        return getAddonTypes.getAddonTypes();
    }

    @PutMapping("/ModifyAddon/{vendorToken}/{addonId}")
    public ResponseObject runModifyAddon(@PathVariable String vendorToken, @PathVariable String addonId,
                                @RequestBody Addon addon){
        return modifyAddon.modifyAddon(vendorToken, addonId, addon);
    }
}
