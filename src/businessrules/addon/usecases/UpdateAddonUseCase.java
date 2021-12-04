package businessrules.addon.usecases;

import businessrules.addon.inputboundaries.UpdateAddonInputBoundary;
import businessrules.dai.AddonRepository;
import businessrules.dai.ShopRepository;
import businessrules.dai.VendorRepository;
import businessrules.loaders.AddonLoader;
import businessrules.loaders.VendorLoader;
import businessrules.outputboundary.AddonModel;

import entities.Addon;
import entities.Shop;
import entities.Vendor;

import org.json.JSONException;
import org.json.JSONObject;

public class UpdateAddonUseCase implements UpdateAddonInputBoundary {
    VendorRepository vendorRepository;
    AddonRepository addonRepository;
    ShopRepository shopRepository;
    AddonModel addonModel;
    VendorLoader vendorLoader;
    AddonLoader addonLoader;

    /**
     * Constructs updateAddonUseCase instance
     * @param vR vendor repository
     * @param aR addon repository
     * @param sR shop repository
     * @param aV addon model
     * @param vL vendor loader
     * @param aL addon loader
     */
    public UpdateAddonUseCase(VendorRepository vR, AddonRepository aR, ShopRepository sR,
                              AddonModel aV, VendorLoader vL, AddonLoader aL) {
        this.vendorRepository = vR;
        this.addonRepository = aR;
        this.shopRepository = sR;
        this.addonModel = aV;
        this.vendorLoader = vL;
        this.addonLoader = aL;
    }

    /**
     * A method that updates an addon with given id and data
     * @param vendorToken token of current vendor
     * @param addonId id of addon to update
     * @param object data to update addon
     * @return data containing error message or updated addon data
     */
    @SuppressWarnings("DuplicatedCode")
    @Override
    public JSONObject updateAddon(String vendorToken, String addonId, JSONObject object) {
        Vendor vendor = vendorLoader.loadVendorFromToken(vendorToken);
        Addon addon = addonLoader.loadAddonFromId(addonId);

        if(vendor == null || addon == null){
            return addonModel.displayError("Incorrect vendorToken or addonId.");
        }


        Shop shop = vendor.getShop();
        Addon newAddon;
        try{
            newAddon = addonLoader.loadAddon(object);
        }catch(JSONException e){
            return addonModel.displayError("Unable to generate new addon object from given data.");
        }

        boolean success = shop.getMenu().updateAddon(addonId, newAddon);

        if(!success){
            return addonModel.displayError("No such addon in the shop's menu.");
        }

        if(!addonRepository.updateAddon(addonId, newAddon.jsonify())){
            return addonModel.displayError("Unable to save modified addon in repository.");
        }

        if(!shopRepository.updateShop(shop.getId(), shop.jsonify())){
            return addonModel.displayError("Unable to update shop's menu and update addon.");
        }

       return addonModel.displayAddon(object);
    }
}
