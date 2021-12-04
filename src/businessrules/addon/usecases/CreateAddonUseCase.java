package businessrules.addon.usecases;

import businessrules.addon.inputboundaries.CreateAddonInputBoundary;
import businessrules.dai.AddonRepository;
import businessrules.dai.ShopRepository;
import businessrules.dai.VendorRepository;
import businessrules.loaders.AddonLoader;
import businessrules.loaders.ShopLoader;
import businessrules.loaders.VendorLoader;
import businessrules.outputboundary.AddonModel;
import businessrules.outputboundary.VendorModel;
import entities.Addon;
import entities.Shop;
import entities.Vendor;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Use Case for creating an Addon entity and updating to repository
 */
public class CreateAddonUseCase implements CreateAddonInputBoundary {

    AddonRepository addonRepository;
    VendorRepository vendorRepository;
    ShopRepository shopRepository;
    AddonModel addonModel;
    VendorModel vendorModel;
    VendorLoader vendorLoader;
    AddonLoader addonLoader;

    /**
     * Constructs an instance of the CreateAddonUseCase
     * @param aR addon repository instance
     * @param vR vendor repository instance
     * @param sR shop repository instance
     * @param aM addon model
     * @param vM vendor model
     * @param vL vendor loader
     * @param aL addon loader
     */
    public CreateAddonUseCase(AddonRepository aR, VendorRepository vR, ShopRepository sR,
                              AddonModel aM, VendorModel vM,
                              VendorLoader vL, AddonLoader aL) {
        this.addonRepository = aR;
        this.vendorRepository = vR;
        this.shopRepository = sR;
        this.addonModel = aM;
        this.vendorModel = vM;
        this.vendorLoader = vL;
        this.addonLoader = aL;
    }

    /**
     * A method that creates an Addon entity and returns its JSONObject representation
     * @param vendorToken token of current vendor
     * @param data information to create the addon with
     * @return JSONObject representing the addon object
     */
    @Override
    public JSONObject createAddon(String vendorToken, JSONObject data) {
        Vendor vendor = vendorLoader.loadVendorFromToken(vendorToken);
        if(vendor == null){
            return addonModel.displayError("Invalid vendor.");
        }

        Addon addon;
        try{
            addon = addonLoader.loadAddon(data);
        }catch (JSONException e){
            return addonModel.displayError("No addon with given data.");
        }

        String id = addonRepository.createAddon(addon.jsonify());

        if(id == null) {
            return addonModel.displayError("Unable to save addon to repository.");
        }

        addon.setId(id);

        Shop shop = vendor.getShop();
        shop.getMenu().addAddon(addon);

        boolean success = shopRepository.updateShop(shop.getId(), shop.jsonify());

        if(!success){
            return addonModel.displayError("Unable to update shop in repository");
        }

        return addonModel.displayAddon(addon.jsonify());
    }



}
