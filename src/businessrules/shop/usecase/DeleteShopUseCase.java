package businessrules.shop.usecase;

import businessrules.dai.AddonRepository;
import businessrules.dai.ShopRepository;
import businessrules.dai.VendorRepository;
import businessrules.loaders.ShopLoader;
import businessrules.loaders.VendorLoader;
import businessrules.outputboundary.AddonModel;
import businessrules.outputboundary.ErrorModel;
import businessrules.outputboundary.ShopModel;
import businessrules.shop.inputboundary.DeleteShopInputBoundary;
import entities.Addon;
import entities.Shop;
import entities.Vendor;

public class DeleteShopUseCase implements DeleteShopInputBoundary {
    VendorRepository vendorRepository;
    ShopRepository shopRepository;
    VendorLoader vendorLoader;
    ShopLoader shopLoader;
    ShopModel shopModel;
    ErrorModel errorHandler;

    public DeleteShopUseCase(VendorRepository vendorRepo, ShopRepository shopRepo,
                             VendorLoader vendorLoad, ShopLoader shopLoad, ShopModel shopMod,
                             ErrorModel er) {
        this.vendorRepository = vendorRepo;
        this.shopRepository = shopRepo;
        this.vendorLoader = vendorLoad;
        this.shopLoader = shopLoad;
        this.shopModel = shopMod;
        this.errorHandler = er;
    }

    @Override
    public boolean deleteShop(String vendorToken, String shopId) {
        Vendor vendor = vendorLoader.loadVendorFromToken(vendorToken);
        Shop shop = shopLoader.loadShopFromId(shopId);

        if(vendor == null || shop == null ){
            errorHandler.displayError("Incorrect vendor token or shop id.");
            return false;
        }



    }
}
