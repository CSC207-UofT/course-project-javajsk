package UseCasesTest.Menu;

import UseCasesTest.TestBoundaries.RAMAddonObjectBoundary;
import UseCasesTest.TestBoundaries.RAMMenuObjectBoundary;
import UseCasesTest.TestBoundaries.RAMRepositoryBoundary;
import UseCasesTest.daitesters.RAMAddonRepository;
import UseCasesTest.daitesters.RAMShopRepository;
import UseCasesTest.daitesters.RAMVendorRepository;
import businessrules.menu.usecases.AddAddonToMenuInteractor;
import businessrules.menu.usecases.GetAvailableAddonsInteractor;
import businessrules.outputboundaries.ObjectBoundary;
import businessrules.outputboundaries.RepositoryBoundary;
import businessrules.outputboundaries.ResponseObject;
import businessrules.outputboundaries.VendorBoundary;
import entities.Addon;
import entities.Menu;
import entities.Vendor;
import org.junit.jupiter.api.Test;

class GetAvailableAddonsInteractorTest {
    RAMVendorRepository vendorRepository;
    RAMShopRepository shopRepository;
    RAMAddonRepository addonRepository;
    VendorBoundary vendorBoundary;
    RepositoryBoundary repositoryBoundary;
    ObjectBoundary<Addon> addonObjectBoundary;
    RAMMenuObjectBoundary menuObjectBoundary;
    GetAvailableAddonsInteractor getAvailableAddonsInteractor;
    AddAddonToMenuInteractor addAddonToMenuInteractor;

    @Test
    void getAvailableAddons() {
        Vendor vendor = new AddAddonToMenuInteractorTest().SetUp();
        Menu menu = new Menu();
        shopRepository = new RAMShopRepository(vendor.getShop());
        repositoryBoundary = new RAMRepositoryBoundary();
        addonObjectBoundary = new RAMAddonObjectBoundary();
        menuObjectBoundary = new RAMMenuObjectBoundary();
        vendorRepository = new RAMVendorRepository(vendor);
        Addon addon1 = new Addon("12", "addon", 12, null,true, vendor.getShop().getId());
        Addon addon2 = new Addon("15", "not addon", 1,null, false, "no shop");
        addAddonToMenuInteractor = new AddAddonToMenuInteractor(vendorRepository, repositoryBoundary, vendorBoundary, shopRepository, menuObjectBoundary);
        getAvailableAddonsInteractor = new GetAvailableAddonsInteractor(shopRepository, repositoryBoundary, addonObjectBoundary);
        addAddonToMenuInteractor.addAddon(vendor.getId(), addon1);
        ResponseObject response = getAvailableAddonsInteractor.getAvailableAddons(vendor.getShop().getId());
        assertEquals("Test Works", response.getMessage());
       assertEquals(getAvailableAddonsInteractor.getAvailableAddons("no_shop").getMessage(), "No such shop found.");
    }
}