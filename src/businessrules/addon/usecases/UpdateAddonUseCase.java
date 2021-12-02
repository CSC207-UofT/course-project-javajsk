package businessrules.addon.usecases;

import businessrules.addon.inputboundaries.UpdateAddonInputBoundary;
import businessrules.dai.AddonRepository;
import businessrules.dai.ShopRepository;
import businessrules.dai.VendorRepository;
import businessrules.loaders.AddonLoader;
import businessrules.loaders.ShopLoader;
import businessrules.loaders.VendorLoader;
import businessrules.outputboundary.AddonModel;
import businessrules.outputboundary.ErrorModel;

import entities.Addon;
import entities.Shop;
import entities.Vendor;

import org.json.JSONException;
import org.json.JSONObject;

public class UpdateAddonUseCase implements UpdateAddonInputBoundary {
    AddonRepository addonRepository;
    ErrorModel errorHandler;
    ShopRepository shopRepository;
    VendorRepository vendorRepository;
    AddonModel addonView;
    VendorLoader vendorLoader;
    AddonLoader addonLoader;

    public UpdateAddonUseCase(AddonRepository addonRepository, ErrorModel errorHandler, ShopRepository shopRepository,
                              VendorRepository vendorRepository, AddonModel addonView, ShopLoader sL) {
        this.addonRepository = addonRepository;
        this.errorHandler = errorHandler;
        this.shopRepository = shopRepository;
        this.vendorRepository = vendorRepository;
        this.addonView = addonView;
        this.addonLoader = new AddonLoader(addonRepository, errorHandler);
        this.vendorLoader = new VendorLoader(vendorRepository, sL, errorHandler);
    }

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
        Addon newAddon;
        try{
            newAddon = addonLoader.loadAddon(object);
        }catch(JSONException e){
            errorHandler.displayError("Unable to generate new addon object from given data.");
            return false;
        }

        boolean success = shop.getMenu().updateAddon(addonId, newAddon);

        if(!success){
            errorHandler.displayError("No such addon in the shop's menu.");
            return false;
        }

        if(!addonRepository.updateAddon(addonId, newAddon.jsonify())){
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
