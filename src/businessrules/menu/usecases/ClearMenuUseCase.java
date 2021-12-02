package businessrules.menu.usecases;

import businessrules.dai.ShopRepository;
import businessrules.dai.VendorRepository;
import businessrules.loaders.ShopLoader;
import businessrules.loaders.VendorLoader;
import businessrules.menu.inputboundaries.ClearMenuInputBoundary;
import businessrules.outputboundary.ErrorModel;
import businessrules.outputboundary.MenuModel;
import entities.Food;
import entities.Menu;
import entities.Vendor;

import java.util.ArrayList;
import java.util.List;

/**
 * Use Case for clearing a menu (i.e. deleting all items from menu)
 *
 * Assuming: all shops will have/must have a menu attribute so actual menu is never
 * deleted, but its contents can be cleared
 */
public class ClearMenuUseCase implements ClearMenuInputBoundary {

    VendorRepository vendorRepository;
    ShopRepository shopRepository;
    MenuModel menuModel;
    ShopLoader shopLoader;
    VendorLoader vendorLoader;
    ErrorModel errorHandler;

    public ClearMenuUseCase(VendorRepository vendorRepo, ShopRepository shopRepo, MenuModel menuMod,
                             ShopLoader shopLoad, VendorLoader vendorLoad, ErrorModel error){
        this.vendorRepository = vendorRepo;
        this.shopRepository = shopRepo;
        this.menuModel = menuMod;
        this.shopLoader = shopLoad;
        this.vendorLoader = vendorLoad;
        this.errorHandler = error;
    }

    /**
     * A method that creates a new menu object for the given vendor with the given data
     * @param vendorToken token of vendor that menu is for
     * @return whether menu was successfully created
     */
    @Override
    public boolean clearMenu(String vendorToken) {

        Vendor vendor = vendorLoader.loadVendorFromToken(vendorToken);
        if(vendor == null){
            errorHandler.displayError("Error. Could not identify vendor.");
            return false;
        }

        //Clear menu in entity objects
        List<Food> emptyFoodList = new ArrayList<>();
        List<Food> emptyAddonList = new ArrayList<>();

        Menu menu = vendor.getShop().getMenu();
        menu.setFoods(emptyFoodList);
        menu.setFoods(emptyAddonList);

        //Clear menu in repository
        boolean isCleared = shopRepository.updateShop(vendor.getShop().getId(), vendor.getShop().jsonify());
        if(!isCleared){
            errorHandler.displayError("Error. Unable to clear menu.");
        }

        menuModel.displayMenu(menu.jsonify());
        return true;
    }
}
