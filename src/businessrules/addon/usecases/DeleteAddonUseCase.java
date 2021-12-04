package businessrules.addon.usecases;

import businessrules.addon.inputboundaries.DeleteAddonInputBoundary;
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
import org.json.JSONObject;

/**
 * Use case for deleting an addon instance
 */
public class DeleteAddonUseCase implements DeleteAddonInputBoundary {
    AddonRepository addonRepository;
    VendorRepository vendorRepository;
    ShopRepository shopRepository;
    AddonModel addonModel;
    VendorModel vendorModel;
    VendorLoader vendorLoader;
    ShopLoader shopLoader;
    AddonLoader addonLoader;

    /**
     * Constructs an instance of the CreateAddonUseCase
     * @param aR addon repository instance
     * @param vr vendor repository instance
     * @param sr shop repository instance
     * @param am addon model
     * @param vm vendor model
     * @param vl vendor loader
     * @param al addon loader
     */
    public DeleteAddonUseCase(AddonRepository aR, VendorRepository vr, ShopRepository sr,
                              VendorModel vm, AddonModel am, VendorLoader vl, ShopLoader sl, AddonLoader al){
        this.addonRepository = aR;
        this.vendorRepository = vr;
        this.shopRepository = sr;
        this.vendorModel = vm;
        this.addonModel = am;
        this.vendorLoader = vl;
        this.shopLoader = sl;
        this.addonLoader = al;
    }

    /**
     * A method that deletes an instance/entry of an addon
     * @param vendorToken token of current vendor
     * @param id id of addon
     * @return JSONObject containing empty data or error message for display
     */
    @SuppressWarnings("DuplicatedCode")
    @Override
    public JSONObject deleteAddon(String vendorToken, String id) {
        Vendor vendor = vendorLoader.loadVendorFromToken(vendorToken);
        Addon addon = addonLoader.loadAddonFromId(id);
        if(vendor == null || addon == null ){
            return addonModel.displayError("Incorrect vendorToken or addonId.");
        }
        Shop shop = vendor.getShop();

        boolean deleteSuccess = shop.getMenu().deleteAddon(addon);

        if(!deleteSuccess){
            return addonModel.displayError("No such addon in any shop owned by you.");
        }

        boolean updateSuccess = shopRepository.updateShop(shop.getId(), shop.jsonify());
        if(!updateSuccess){
            return addonModel.displayError("Unable to update shop's menu and remove addon.");
        }

        if(addonRepository.deleteAddon(id)){
            JSONObject emptyData = new JSONObject();
            return addonModel.displayAddon(emptyData);
        }
        return addonModel.displayError("Unable to delete addon from repository.");
    }
}
