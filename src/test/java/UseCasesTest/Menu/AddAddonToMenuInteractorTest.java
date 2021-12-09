package UseCasesTest.Menu;

import UseCasesTest.TestBoundaries.RAMAddonObjectBoundary;
import UseCasesTest.TestBoundaries.RAMMenuObjectBoundary;
import UseCasesTest.TestBoundaries.RAMRepositoryBoundary;
import UseCasesTest.TestBoundaries.RAMVendorBoundary;
import UseCasesTest.daitesters.RAMAddonRepository;
import UseCasesTest.daitesters.RAMShopRepository;
import UseCasesTest.daitesters.RAMVendorRepository;
import businessrules.menu.usecases.AddAddonToMenuInteractor;
import businessrules.outputboundaries.ObjectBoundary;
import businessrules.outputboundaries.RepositoryBoundary;
import businessrules.outputboundaries.ResponseObject;
import businessrules.outputboundaries.VendorBoundary;
import entities.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;



class AddAddonToMenuInteractorTest {
    RAMVendorRepository vendorRepository;
    RAMShopRepository shopRepository;
    RAMAddonRepository addonRepository;
    VendorBoundary vendorBoundary;
    RepositoryBoundary repositoryBoundary;
    ObjectBoundary<Addon> addonObjectBoundary;
    RAMMenuObjectBoundary menuObjectBoundary;
    AddAddonToMenuInteractor addAddonToMenuInteractor;

    Vendor SetUp(){
        Menu menu = new Menu();
        OrderBook orderBook = new OrderBook();
        Shop shop2 = new Shop( "id1", "shop1","Bloor", true,  menu, orderBook);
        Vendor vendor = new Vendor("id1", "vendor1", "password", shop2);
        Addon addon = new Addon("ID5", "addon", 10, null, true,shop2.getId());
        menuObjectBoundary = new RAMMenuObjectBoundary();
        repositoryBoundary = new RAMRepositoryBoundary();
        shopRepository = new RAMShopRepository(shop2);
        addonObjectBoundary = new RAMAddonObjectBoundary();
        vendorRepository = new RAMVendorRepository(vendor);
        shopRepository = new RAMShopRepository(shop2);
        addonRepository = new RAMAddonRepository(addon);
        vendorBoundary = new RAMVendorBoundary();
        addAddonToMenuInteractor = new AddAddonToMenuInteractor(vendorRepository,repositoryBoundary, vendorBoundary, shopRepository, menuObjectBoundary);
        return vendor;
    }
    @Test
    void addAddon() {
        Vendor vendor = SetUp();
        Addon addon_new = new Addon("ID10", "newAddon", 10, null, true,"false shop");
        ResponseObject response = addAddonToMenuInteractor.addAddon(vendor.getId(), addon_new);
        assertEquals("This addon does not belong to this shop.", response.getMessage());
        Addon addon_new1 = new Addon("ID11", "newAddon", 10, null, true, vendor.getShop().getId());
        addAddonToMenuInteractor.addAddon(vendor.getId(), addon_new1);
        assertTrue(vendor.getShop().getMenu().getAddons().contains(addon_new1));
    }
}
