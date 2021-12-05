package com.javajsk.uoftruck.controllers;

import businessrules.addon.inputboundaries.CreateAddon;
import businessrules.addon.inputboundaries.GetShopAddons;
import businessrules.addon.inputboundaries.ModifyAddon;
import businessrules.outputboundaries.ResponseObject;
import entities.Addon;
import org.springframework.web.bind.annotation.*;

public class AddonController {
    CreateAddon createAddon;
    GetShopAddons getShopAddons;
    ModifyAddon modifyAddon;

    public AddonController(CreateAddon createAddon, GetShopAddons getShopAddons, ModifyAddon modifyAddon) {
        this.createAddon = createAddon;
        this.getShopAddons = getShopAddons;
        this.modifyAddon = modifyAddon;
    }

    @PostMapping("/CreateAddon/{vendorToken}")
    public ResponseObject runCreateAddon(@PathVariable String vendorToken, @RequestBody Addon addon){
        return createAddon.createAddon(vendorToken, addon);
    }

    @GetMapping("/GetShopAddons/{shopId}")
    public ResponseObject runGetShopAddons(@PathVariable String shopId){
        return getShopAddons.getShopAddons(shopId);
    }

    @PutMapping("/ModifyAddonInteractor/{vendorToken}/{addonId}")
    public ResponseObject runModifyAddon(@PathVariable String vendorToken, @PathVariable String addonId,
                                @RequestBody Addon addon){
        return modifyAddon.modifyAddon(vendorToken, addonId, addon);
    }
}
