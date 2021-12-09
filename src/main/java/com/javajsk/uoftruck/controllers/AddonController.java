package com.javajsk.uoftruck.controllers;

import adapters.dam.AddonDB;
import adapters.dam.VendorDB;
import businessrules.addon.inputboundaries.*;
import businessrules.addon.usecases.*;
import businessrules.addon.inputboundaries.CreateAddon;
import businessrules.addon.inputboundaries.GetAddonTypes;
import businessrules.addon.inputboundaries.GetShopAddons;
import businessrules.addon.inputboundaries.ModifyAddon;
import businessrules.addon.usecases.CreateAddonInteractor;
import businessrules.addon.usecases.GetAddonTypesInteractor;
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
import adapters.presenters.ObjectPresenter;
import adapters.presenters.RepositoryPresenter;
import adapters.presenters.VendorPresenter;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class AddonController {
    CreateAddon createAddon;
    GetShopAddons getShopAddons;
    ModifyAddon modifyAddon;
    GetAddon getAddon;
    GetAddonTypes getAddonTypes;
    MongoDB db;
    AddonDB addonRepository;
    VendorRepository vendorRepository;
    VendorBoundary vendorBoundary = new VendorPresenter();
    RepositoryBoundary repositoryBoundary = new RepositoryPresenter();
    ObjectBoundary<Addon> addonObjectBoundary = new ObjectPresenter<>();


    public AddonController() {
        this.db = new MongoDB();
        this.addonRepository = new AddonDB(db);
        this.vendorRepository = new VendorDB(db);
        this.createAddon = new CreateAddonInteractor(addonRepository, vendorRepository, vendorBoundary,
                repositoryBoundary, addonObjectBoundary);
        this.getShopAddons = new GetShopAddonsInteractor(addonRepository, repositoryBoundary, addonObjectBoundary);
        this.modifyAddon = new ModifyAddonInteractor(addonRepository, addonObjectBoundary,
                repositoryBoundary, vendorRepository, vendorBoundary);
        this.getAddonTypes = new GetAddonTypesInteractor(addonRepository);
        this.getAddon = new GetAddonInteractor(addonRepository, repositoryBoundary, addonObjectBoundary);
    }

    /**
     * @param vendorToken The token given to the vendor when logging in
     * @param addon The addon object that needs to be created in the database in JSON format
     * @return A responseObject with a JSON of the object if status is 200, error message otherwise
     */

    @PostMapping("/CreateAddon/{vendorToken}")
    public ResponseObject runCreateAddon(@PathVariable String vendorToken, @RequestBody String addon){
        Addon addon1 = addonRepository.loadAddonFromJSON(new JSONObject(addon));
        return createAddon.createAddon(vendorToken, addon1);
    }

    /**
     * @param shopId The shopId corresponding to the shop we want to get addons from
     * @return A ResponseObjects with the addons data along with status codes
     */
    @GetMapping("/GetShopAddons/{shopId}")
    public ResponseObject runGetShopAddons(@PathVariable String shopId){
        return getShopAddons.getShopAddons(shopId);
    }

    /**
     * @param addonId The addonId associated with the addon w want to get from the databse
     * @return A ResponseObjects with the addon data along with status codes
     */
    @GetMapping("GetAddon/{addonId}")
    public ResponseObject runGetAddon(@PathVariable String addonId) {
        return getAddon.getAddon(addonId);
    }

    /**
     * @return A ResponseObjects with all of the Addon Types in the database along with status codes
     */
    @GetMapping("/GetAddonTypes/")
    public ResponseObject runGetAddonTypes(){
        return getAddonTypes.getAddonTypes();
    }

    /**
     * @param vendorToken The Vendor token of the vendor who owns that addon
     * @param addonId The id of the addon wanting to be modified
     * @param addon The new addon that replaces the old addon
     * @return A ResponseObjects with the addon data along with status codes
     */
    @PutMapping("/ModifyAddon/{vendorToken}/{addonId}")
    public ResponseObject runModifyAddon(@PathVariable String vendorToken, @PathVariable String addonId,
                                         @RequestBody Addon addon){
        return modifyAddon.modifyAddon(vendorToken, addonId, addon);
    }

}
