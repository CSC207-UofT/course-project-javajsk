package UseCasesTest.Menu;

import UseCasesTest.TestBoundaries.RAMMenuObjectBoundary;
import UseCasesTest.TestBoundaries.RAMRepositoryBoundary;
import UseCasesTest.TestBoundaries.RAMVendorBoundary;
import UseCasesTest.daitesters.RAMAddonRepository;
import UseCasesTest.daitesters.RAMShopRepository;
import UseCasesTest.daitesters.RAMVendorRepository;
import businessrules.menu.usecases.AddAddonToMenuInteractor;
import businessrules.menu.usecases.RemoveAddonFromMenuInteractor;
import businessrules.outputboundaries.ObjectBoundary;
import businessrules.outputboundaries.RepositoryBoundary;
import businessrules.outputboundaries.VendorBoundary;
import entities.Addon;
import entities.Vendor;
import org.junit.jupiter.api.Test;

class RemoveAddonFromMenuInteractorTest {
    RAMVendorRepository vendorRepository;
    RAMShopRepository shopRepository;
    RAMAddonRepository addonRepository;
    VendorBoundary vendorBoundary;
    RepositoryBoundary repositoryBoundary;
    ObjectBoundary<Addon> addonObjectBoundary;
    RAMMenuObjectBoundary menuObjectBoundary;
    AddAddonToMenuInteractor addAddonToMenuInteractor;
    RemoveAddonFromMenuInteractor removeAddonFromMenuInteractor;
    @Test
    void removeAddon() {
        Vendor vendor = new AddAddonToMenuInteractorTest().SetUp();
        vendorRepository = new RAMVendorRepository(vendor);
        repositoryBoundary = new RAMRepositoryBoundary();
        vendorBoundary = new RAMVendorBoundary();
        shopRepository = new RAMShopRepository(vendor.getShop());
        menuObjectBoundary = new RAMMenuObjectBoundary();
        addAddonToMenuInteractor = new AddAddonToMenuInteractor(vendorRepository,repositoryBoundary,vendorBoundary,shopRepository,menuObjectBoundary);
        removeAddonFromMenuInteractor = new RemoveAddonFromMenuInteractor(vendorRepository,repositoryBoundary,vendorBoundary,shopRepository,menuObjectBoundary);
        Addon addon = new Addon("id1", "addon22", 44, null, true, vendor.getShop().getId());
        addAddonToMenuInteractor.addAddon(vendor.getId(),addon);
        assertTrue(vendor.getShop().getMenu().getAddons().contains(addon));
        removeAddonFromMenuInteractor.removeAddon(vendor.getId(),addon);
        assertFalse(vendor.getShop().getMenu().getAddons().contains(addon));
    }
}