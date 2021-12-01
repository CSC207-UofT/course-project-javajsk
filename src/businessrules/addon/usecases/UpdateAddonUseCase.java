package businessrules.addon.usecases;

import businessrules.addon.inputboundaries.UpdateAddonInputBoundary;
import businessrules.dai.AddonRepository;
import businessrules.dai.ShopRepository;
import businessrules.dai.VendorRepository;
import businessrules.loaders.AddonLoader;
import businessrules.loaders.VendorLoader;
import businessrules.outputboundary.AddonModel;
import businessrules.outputboundary.ErrorModel;

import entities.Addon;
import entities.Shop;
import entities.Vendor;

import org.json.JSONObject;

public class UpdateAddonUseCase implements UpdateAddonInputBoundary {
    AddonRepository addonRepository;
    ErrorModel errorHandler;
    ShopRepository shopRepository;
    VendorRepository vendorRepository;
    AddonModel addonView;
    VendorLoader vendorLoader;
    AddonLoader addonLoader;


    @SuppressWarnings("DuplicatedCode")
    @Override
    public boolean updateAddon(String vendorToken, String addonId, JSONObject object) {
        Vendor vendor = vendorLoader.loadVendorFromToken(vendorToken);
        Addon addon = addonLoader.loadAddonFromId(addonId);

        if(vendor == null || addon == null){
            errorHandler.displayError("Incorrect vendorToken or addonId.");
            return false;
        }


        Shop shop = vendor.getShop();
        boolean success = shop.getMenu().updateAddon(addonId, addon);

        if(!success){
            errorHandler.displayError("No such addon in the shop's menu.");
            return false;
        }

        if(!addonRepository.updateAddon(addonId, addon.jsonify())){
            errorHandler.displayError("Unable to save modified addon in repository.");
            return false;
        }

        if(!shopRepository.updateShop(shop.getId(), shop.jsonify())){
            errorHandler.displayError("Unable to update shop's menu and update addon.");
            return false;
        }

        addonView.updateAddon(addonId, object);
        return true;


    }
}
