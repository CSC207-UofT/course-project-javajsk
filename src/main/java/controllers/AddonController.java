package controllers;

import businessrules.addon.inputboundaries.CreateAddon;
import businessrules.addon.inputboundaries.GetShopAddons;
import businessrules.addon.inputboundaries.ModifyAddon;
import businessrules.outputboundaries.ResponseObject;
import entities.Addon;
import entities.Food;
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
    public Addon runCreateAddon(@PathVariable String vendorToken, @RequestBody Addon addon){
        ResponseObject response = createAddon.createAddon(vendorToken, addon);
        return (Addon) response.getContents();
    }

    @GetMapping("/GetShopAddons/{shopId}")
    public Addon runGetShopAddons(@PathVariable String shopId){
        ResponseObject response = getShopAddons.getShopAddons(shopId);
        return (Addon) response.getContents();
    }

    @PutMapping("/ModifyAddonInteractor/{vendorToken}/{foodId}")
    public Addon runModifyAddon(@PathVariable String vendorToken, @PathVariable String addonId,
                                @RequestBody Addon addon){
        ResponseObject response = modifyAddon.modifyAddon(vendorToken, addonId, addon);
        return (Addon) response.getContents();
    }
}
