package UseCasesTest.Addon;

import UseCasesTest.TestBoundaries.RAMAddonObjectBoundary;
import UseCasesTest.daitesters.*;
import businessrules.addon.usecases.CreateAddonInteractor;
import businessrules.outputboundaries.ObjectBoundary;
import businessrules.outputboundaries.RepositoryBoundary;
import businessrules.outputboundaries.VendorBoundary;
import entities.Addon;
import entities.Menu;
import entities.Shop;
import entities.Vendor;
import org.junit.jupiter.api.Test;
import entities.OrderBook;

import static org.junit.jupiter.api.Assertions.*;

class CreateAddonInteractorTest{
    RAMVendorRepository vendorRepository;
    RAMShopRepository shopRepository;
    RAMAddonRepository addonRepository;
    CreateAddonInteractor createAddonInteractor;
    VendorBoundary vendorBoundary;
    RepositoryBoundary repositoryBoundary;
    ObjectBoundary<Addon> addonObjectBoundary;
    Vendor setUp(){
        Menu menu = new Menu();
        OrderBook orderBook = new OrderBook();
        Shop shop2 = new Shop( "id1", "shop1","Bloor", true,  menu, orderBook);
        Vendor vendor = new Vendor("id1", "vendor1", "password", shop2);
        Addon addon = new Addon("ID1", "addon", 10, null, true,shop2.getId());
        addonObjectBoundary = new RAMAddonObjectBoundary();
        vendorRepository = new RAMVendorRepository(vendor);
        shopRepository = new RAMShopRepository(shop2);
        addonRepository = new RAMAddonRepository(addon);
        createAddonInteractor = new CreateAddonInteractor(addonRepository, vendorRepository, vendorBoundary, repositoryBoundary, addonObjectBoundary);
        return vendor;
    }
    @Test
    void createAddon() {
        Vendor vendor = setUp();
        Shop shop = vendor.getShop();
        Addon addon = new Addon("addon500", "addon", 15, null, true, "shop1");
        createAddonInteractor.createAddon(vendor.getId(), addon);
        assertEquals(addonRepository.read("addon500"), addon);
        Addon addon1 = new Addon("addon2", "addon2", 25, null, false, "shop1");
        createAddonInteractor.createAddon(vendor.getId(), addon1);
        assertEquals(addon1, addonRepository.read(addon1.id));
        assertEquals(addonRepository.create(addon1), "Item already exists");
    }
}