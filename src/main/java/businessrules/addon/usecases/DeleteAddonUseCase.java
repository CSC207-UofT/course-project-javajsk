package businessrules.addon.usecases;

import businessrules.addon.inputboundaries.DeleteAddonInputBoundary;
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

public class DeleteAddonUseCase implements DeleteAddonInputBoundary {
    AddonRepository addonRepository;
    AddonModel addonModel;
    ErrorModel errorHandler;
    VendorRepository vendorRepository;
    ShopRepository shopRepository;
    VendorLoader vendorLoader;
    AddonLoader addonLoader;

    public DeleteAddonUseCase(AddonRepository arep, VendorRepository vr, ShopRepository sr,
                              AddonModel am, ErrorModel em){
        this.addonRepository = arep;
        this.vendorRepository = vr;
        this.errorHandler = em;
        this.shopRepository = sr;
        this.addonLoader = new AddonLoader(arep, em);
        this.vendorLoader = new VendorLoader(vr, em);
    }

    @SuppressWarnings("DuplicatedCode")
    @Override
    public boolean deleteAddon(String vendorToken, String id) {
        Vendor vendor = vendorLoader.loadVendorFromToken(vendorToken);
        Addon addon = addonLoader.loadAddonFromId(id);
        if(vendor == null || addon == null ){
            errorHandler.displayError("Incorrect vendorToken or addonId.");
            return false;
        }
        Shop shop = vendor.getShop();

        boolean deleteSuccess = shop.getMenu().deleteAddon(addon);

        if(!deleteSuccess){
            errorHandler.displayError("No such addon in any shop owned by you.");
            return false;
        }

        boolean updateSuccess = shopRepository.updateShop(shop.getId(), shop.jsonify());
        if(!updateSuccess){
            errorHandler.displayError("Unable to update shop's menu and remove addon.");
            return false;
        }

        if(addonRepository.deleteAddon(id)){
            addonModel.deleteAddon(id);
            return true;
        }
        return false;
    }
}
