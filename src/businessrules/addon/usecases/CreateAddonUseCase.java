package businessrules.addon.usecases;

import businessrules.addon.inputboundaries.CreateAddonInputBoundary;
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

    public CreateAddonUseCase(AddonRepository addonRepository, VendorRepository vendorRepository,
                              ShopRepository shopRepository, ErrorModel errorHandler, AddonModel addonView) {
        this.addonRepository = addonRepository;
        this.vendorRepository = vendorRepository;
        this.shopRepository = shopRepository;
        this.errorHandler = errorHandler;
        this.addonView = addonView;
        this.vendorLoader = new VendorLoader(vendorRepository, errorHandler);
        this.addonLoader = new AddonLoader(addonRepository, errorHandler);
    }

    @Override
    public boolean createAddon(String vendorToken, JSONObject data) {
        Vendor vendor = vendorLoader.loadVendorFromToken(vendorToken);
        if(vendor == null){
            return false;
        }

        Addon addon;
        try{
            addon = AddonLoader.loadAddon(data);
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
