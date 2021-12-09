package UseCasesTest.Addon;

import UseCasesTest.TestBoundaries.RAMAddonObjectBoundary;
import UseCasesTest.TestBoundaries.RAMRepositoryBoundary;
import UseCasesTest.TestBoundaries.RAMVendorBoundary;
import UseCasesTest.daitesters.*;
import businessrules.addon.usecases.CreateAddonInteractor;
import businessrules.menu.usecases.AddAddonToMenuInteractor;
import businessrules.outputboundaries.ObjectBoundary;
import businessrules.outputboundaries.RepositoryBoundary;
import businessrules.outputboundaries.ResponseObject;
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
        repositoryBoundary = new RAMRepositoryBoundary();
        addonRepository = new RAMAddonRepository(addon);
        vendorRepository = new RAMVendorRepository(vendor);
       vendorBoundary = new RAMVendorBoundary();
        createAddonInteractor = new CreateAddonInteractor(addonRepository, vendorRepository, vendorBoundary, repositoryBoundary, addonObjectBoundary);
        return vendor;
    }
    @Test
    void createAddon() {
        Vendor vendor = setUp();
        Addon addon2 = new Addon("addon500", "addon", 15, null, true, "shop1");
        addonRepository = new RAMAddonRepository(addon2);
        repositoryBoundary = new RAMRepositoryBoundary();
        vendorRepository = new RAMVendorRepository(vendor);
        vendorBoundary = new RAMVendorBoundary();
        addonObjectBoundary = new RAMAddonObjectBoundary();
        createAddonInteractor = new CreateAddonInteractor(addonRepository, vendorRepository, vendorBoundary, repositoryBoundary, addonObjectBoundary);
        createAddonInteractor.createAddon(vendor.getId(), addon2);
        ResponseObject response = createAddonInteractor.createAddon(vendor.getId(), addon2);
        assertEquals(addon2, response.getContents());
        assertSame(addonRepository.read(addon2.getId()), addon2);
    }
}