package businessrules.addon.usecases;

import businessrules.addon.inputboundaries.CreateAddonInputBoundary;
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

public class CreateAddonUseCase implements CreateAddonInputBoundary {

    AddonRepository addonRepository;
    VendorRepository vendorRepository;
    ShopRepository shopRepository;
    ErrorModel errorHandler;
    AddonModel addonView;
    VendorLoader vendorLoader;
    AddonLoader addonLoader;

    public CreateAddonUseCase(AddonRepository aR, VendorRepository vR,
                              ShopRepository sR, ShopLoader sL, ErrorModel error, AddonModel addonMod) {
        this.addonRepository = aR;
        this.vendorRepository = vR;
        this.shopRepository = sR;
        this.errorHandler = error;
        this.addonView = addonMod;
        this.vendorLoader = new VendorLoader(vR, sL, error);
        this.addonLoader = new AddonLoader(aR, error);
    }

    @Override
    public boolean createAddon(String vendorToken, JSONObject data) {
        Vendor vendor = vendorLoader.loadVendorFromToken(vendorToken);
        if(vendor == null){
            return false;
        }

        Addon addon;
        try{
            addon = addonLoader.loadAddon(data);
        }catch (JSONException e){
            errorHandler.displayError(e.getMessage());
            return false;
        }

        String id = addonRepository.createAddon(addon.jsonify());

        if(id == null) {
            errorHandler.displayError("Unable to create addon in the repository.");
            return false;
        }

        addon.setId(id);

        Shop shop = vendor.getShop();
        shop.getMenu().addAddon(addon);

        boolean success = shopRepository.updateShop(shop.getId(), shop.jsonify());

        if(!success){
            errorHandler.displayError("Unable to update shop in the repository.");
            return  false;
        }

        addonView.displayAddon(addon.jsonify());
        return true;
    }



}
