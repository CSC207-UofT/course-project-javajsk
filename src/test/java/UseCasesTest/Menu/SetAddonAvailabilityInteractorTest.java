package UseCasesTest.Menu;

import UseCasesTest.TestBoundaries.RAMMenuObjectBoundary;
import UseCasesTest.TestBoundaries.RAMRepositoryBoundary;
import UseCasesTest.TestBoundaries.RAMVendorBoundary;
import UseCasesTest.daitesters.RAMAddonRepository;
import UseCasesTest.daitesters.RAMShopRepository;
import UseCasesTest.daitesters.RAMVendorRepository;
import businessrules.menu.usecases.AddAddonToMenuInteractor;
import businessrules.menu.usecases.RemoveAddonFromMenuInteractor;
import businessrules.menu.usecases.SetAddonAvailabilityInteractor;
import businessrules.outputboundaries.ObjectBoundary;
import businessrules.outputboundaries.RepositoryBoundary;
import businessrules.outputboundaries.VendorBoundary;
import entities.Addon;
import entities.Vendor;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class SetAddonAvailabilityInteractorTest {
    RAMVendorRepository vendorRepository;
    RAMShopRepository shopRepository;
    VendorBoundary vendorBoundary;
    RepositoryBoundary repositoryBoundary;
    RAMMenuObjectBoundary menuObjectBoundary;
    AddAddonToMenuInteractor addAddonToMenuInteractor;
    SetAddonAvailabilityInteractor setAddonAvailabilityInteractor;

    @Test
    void setAddonAvailability() {
        Vendor vendor = new AddAddonToMenuInteractorTest().SetUp();
        vendorRepository = new RAMVendorRepository(vendor);
        repositoryBoundary = new RAMRepositoryBoundary();
        vendorBoundary = new RAMVendorBoundary();
        shopRepository = new RAMShopRepository(vendor.getShop());
        menuObjectBoundary = new RAMMenuObjectBoundary();
        addAddonToMenuInteractor = new AddAddonToMenuInteractor(vendorRepository,repositoryBoundary,vendorBoundary,shopRepository, menuObjectBoundary);
        setAddonAvailabilityInteractor = new SetAddonAvailabilityInteractor(menuObjectBoundary,vendorRepository, repositoryBoundary, vendorBoundary, shopRepository);
        Addon addon = new Addon("ID1", "addon", 12, null, true, vendor.getShop().getId());
        addAddonToMenuInteractor.addAddon(vendor.getId(), addon);
        assertTrue(vendor.getShop().getMenu().getAddons().get(0).getId().equalsIgnoreCase("id1"));
        assertTrue(vendor.getShop().getMenu().getAddon(addon).getAvailable());
        setAddonAvailabilityInteractor.setAddonAvailability(vendor.getId(),addon, false);
        assertFalse(vendor.getShop().getMenu().getAddon(addon).getAvailable());
    }
}