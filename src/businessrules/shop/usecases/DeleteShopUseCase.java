package businessrules.shop.usecases;

import businessrules.dai.ShopRepository;
import businessrules.dai.VendorRepository;
import businessrules.loaders.ShopLoader;
import businessrules.loaders.VendorLoader;
import businessrules.outputboundary.ShopModel;
import businessrules.outputboundary.VendorModel;
import businessrules.shop.inputboundaries.DeleteShopInputBoundary;
import entities.Shop;
import entities.Vendor;

public class DeleteShopUseCase implements DeleteShopInputBoundary {
    VendorRepository vendorRepository;
    ShopRepository shopRepository;
    VendorLoader vendorLoader;
    ShopLoader shopLoader;
    ShopModel shopModel;
    VendorModel vendorModel;
    ErrorModel errorHandler;

    public DeleteShopUseCase(VendorRepository vendorRepo, ShopRepository shopRepo,
                             VendorLoader vendorLoad, ShopLoader shopLoad, ShopModel shopMod,
                             VendorModel vendMod, ErrorModel er) {
        this.vendorRepository = vendorRepo;
        this.shopRepository = shopRepo;
        this.vendorLoader = vendorLoad;
        this.shopLoader = shopLoad;
        this.shopModel = shopMod;
        this.vendorModel = vendMod;
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

        //TODO: is it necessary to check shop id if any vendor only has one shop
        boolean deleteSuccess = vendor.deleteShop(shopId);
        if(!deleteSuccess){
            errorHandler.displayError("No such shop is owned by you.");
            return false;
        }

        deleteSuccess = shopRepository.deleteShop(shopId);
        if(!deleteSuccess){
            errorHandler.displayError("Unable to delete shop from repository.");
            return false;
        }

        deleteSuccess = vendorRepository.updateUser(vendor.getId(), vendor.jsonify());
        if(!deleteSuccess){
            errorHandler.displayError("Unable to update vendor.");
            return false;
        }

        vendorModel.displayVendor(vendor.jsonify());
        return true;
    }
}
