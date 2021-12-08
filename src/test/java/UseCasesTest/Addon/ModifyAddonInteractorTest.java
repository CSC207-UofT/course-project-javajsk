package UseCasesTest.Addon;

import UseCasesTest.TestBoundaries.RAMAddonObjectBoundary;
import UseCasesTest.TestBoundaries.RAMVendorBoundary;
import UseCasesTest.daitesters.RAMAddonRepository;
import UseCasesTest.daitesters.RAMShopRepository;
import UseCasesTest.daitesters.RAMVendorRepository;
import businessrules.addon.usecases.ModifyAddonInteractor;
import businessrules.outputboundaries.ObjectBoundary;
import businessrules.outputboundaries.RepositoryBoundary;
import businessrules.outputboundaries.VendorBoundary;
import entities.*;
import org.junit.jupiter.api.Test;

class ModifyAddonInteractorTest {
    RAMVendorRepository vendorRepository;
    RAMShopRepository shopRepository;
    RAMAddonRepository addonRepository;
    ModifyAddonInteractor modifyAddonInteractor;
    VendorBoundary vendorBoundary;
    RepositoryBoundary repositoryBoundary;
    ObjectBoundary<Addon> addonObjectBoundary;

    @Test
    void modifyAddon() {
        Vendor vendor = new CreateAddonInteractorTest().setUp();
        vendorBoundary = new RAMVendorBoundary();
        addonObjectBoundary = new RAMAddonObjectBoundary();
        vendorRepository = new RAMVendorRepository(vendor);
        shopRepository = new RAMShopRepository(vendor.getShop());
        Addon oldaddon = new Addon("id1", "oldaddon", 199, null, true, vendor.getShop().toString());
        addonRepository = new RAMAddonRepository(oldaddon);
        modifyAddonInteractor = new ModifyAddonInteractor(addonRepository,addonObjectBoundary,repositoryBoundary, vendorRepository, vendorBoundary);
        Addon newaddon = new Addon("id33", "newaddon", 1222, null, false, vendor.getShop().toString());
        modifyAddonInteractor.modifyAddon(vendor.getId(), oldaddon.getId(), newaddon);
        //assertNull(addonRepository.read(oldaddon.getId()));
        //assertEquals(newaddon, addonRepository.read("id33"));
    }
}