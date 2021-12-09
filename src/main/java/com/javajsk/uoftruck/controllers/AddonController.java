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


/**
 * Controller for addon use cases
 */
@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class AddonController {
    /**
     * The Create addon input boundary.
     */
    CreateAddon createAddon;
    /**
     * The Get shop addons input boundary.
     */
    GetShopAddons getShopAddons;
    /**
     * The Modify addon input bounary.
     */
    ModifyAddon modifyAddon;
    /**
     * The Get addon input boundary.
     */
    GetAddon getAddon;
    /**
     * The Get addon types input boundary.
     */
    GetAddonTypes getAddonTypes;
    /**
     * The database.
     */
    MongoDB db;
    /**
     * The Addon repository.
     */
    AddonDB addonRepository;
    /**
     * The Vendor repository.
     */
    VendorRepository vendorRepository;
    /**
     * The Vendor output boundary.
     */
    VendorBoundary vendorBoundary = new VendorPresenter();
    /**
     * The Repository output boundary.
     */
    RepositoryBoundary repositoryBoundary = new RepositoryPresenter();
    /**
     * The Addon object output boundary.
     */
    ObjectBoundary<Addon> addonObjectBoundary = new ObjectPresenter<>();


    /**
     * Instantiates a new Addon controller.
     */
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
     * Runs create addon use case
     *
     * @param vendorToken The token given to the vendor when logging in
     * @param addon       The addon object that needs to be created in the database in JSON format
     * @return A responseObject with a JSON of the object if status is 200, error message otherwise
     */
    @PostMapping("/CreateAddon/{vendorToken}")
    public ResponseObject runCreateAddon(@PathVariable String vendorToken, @RequestBody String addon) {
        Addon addon1 = addonRepository.loadAddonFromJSON(new JSONObject(addon));
        return createAddon.createAddon(vendorToken, addon1);
    }

    /**
     * Runs get shop addons use case.
     *
     * @param shopId The shopId corresponding to the shop we want to get addons from
     * @return A ResponseObjects with the addons data along with status codes
     */
    @GetMapping("/GetShopAddons/{shopId}")
    public ResponseObject runGetShopAddons(@PathVariable String shopId) {
        return getShopAddons.getShopAddons(shopId);
    }

    /**
     * Run get addon use case.
     *
     * @param addonId The addonId associated with the addon w want to get from the databse
     * @return A ResponseObjects with the addon data along with status codes
     */
    @GetMapping("GetAddon/{addonId}")
    public ResponseObject runGetAddon(@PathVariable String addonId) {
        return getAddon.getAddon(addonId);
    }

    /**
     * Run get addon types use case.
     *
     * @return A ResponseObjects with all of the Addon Types in the database along with status codes
     */
    @GetMapping("/GetAddonTypes/")
    public ResponseObject runGetAddonTypes() {
        return getAddonTypes.getAddonTypes();
    }

    /**
     * Run modify addon use case.
     *
     * @param vendorToken The Vendor token of the vendor who owns that addon
     * @param addonId     The id of the addon wanting to be modified
     * @param addon       The new addon that replaces the old addon
     * @return A ResponseObjects with the addon data along with status codes
     */
    @PutMapping("/ModifyAddon/{vendorToken}/{addonId}")
    public ResponseObject runModifyAddon(@PathVariable String vendorToken, @PathVariable String addonId,
                                         @RequestBody Addon addon) {
        return modifyAddon.modifyAddon(vendorToken, addonId, addon);
    }

}
