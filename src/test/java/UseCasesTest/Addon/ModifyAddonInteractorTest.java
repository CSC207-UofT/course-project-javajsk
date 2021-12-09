package UseCasesTest.Addon;

import UseCasesTest.TestBoundaries.RAMAddonObjectBoundary;
import UseCasesTest.TestBoundaries.RAMMenuObjectBoundary;
import UseCasesTest.TestBoundaries.RAMVendorBoundary;
import UseCasesTest.daitesters.RAMAddonRepository;
import UseCasesTest.daitesters.RAMShopRepository;
import UseCasesTest.daitesters.RAMVendorRepository;
import businessrules.addon.usecases.CreateAddonInteractor;
import businessrules.addon.usecases.ModifyAddonInteractor;
import businessrules.menu.usecases.AddAddonToMenuInteractor;
import businessrules.outputboundaries.ObjectBoundary;
import businessrules.outputboundaries.RepositoryBoundary;
import businessrules.outputboundaries.ResponseObject;
import businessrules.outputboundaries.VendorBoundary;
import entities.*;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ModifyAddonInteractorTest {
    RAMVendorRepository vendorRepository;
    RAMShopRepository shopRepository;
    RAMAddonRepository addonRepository;
    ModifyAddonInteractor modifyAddonInteractor;
    VendorBoundary vendorBoundary;
    RAMMenuObjectBoundary menuObjectBoundary;
    RepositoryBoundary repositoryBoundary;
    ObjectBoundary<Addon> addonObjectBoundary;
    AddAddonToMenuInteractor addAddonToMenuInteractor;

    @Test
    void modifyAddon() {
        Vendor vendor = new CreateAddonInteractorTest().setUp();
        vendorBoundary = new RAMVendorBoundary();
        menuObjectBoundary = new RAMMenuObjectBoundary();
        addonObjectBoundary = new RAMAddonObjectBoundary();
        vendorRepository = new RAMVendorRepository(vendor);
        shopRepository = new RAMShopRepository(vendor.getShop());
        Addon oldaddon = new Addon("id1", "oldaddon", 199, null, true, vendor.getShop().getId());
        addonRepository = new RAMAddonRepository(oldaddon);
        addAddonToMenuInteractor = new AddAddonToMenuInteractor(vendorRepository,repositoryBoundary,vendorBoundary,shopRepository,menuObjectBoundary);
        addAddonToMenuInteractor.addAddon(vendor.getId(), oldaddon);
        modifyAddonInteractor = new ModifyAddonInteractor(addonRepository,addonObjectBoundary,repositoryBoundary, vendorRepository, vendorBoundary);
        Addon newaddon = new Addon("id1", "newaddon", 1222, null, false, vendor.getShop().getId());
        ResponseObject response = modifyAddonInteractor.modifyAddon(vendor.getId(), oldaddon.getId(), newaddon);
        assertSame(newaddon, response.getContents());
    }
}