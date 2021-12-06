package UseCasesTest.Addon;

import UseCasesTest.TestBoundaries.RAMAddonObjectBoundary;
import UseCasesTest.TestBoundaries.RAMRepositoryBoundary;
import UseCasesTest.TestBoundaries.RAMVendorBoundary;
import UseCasesTest.daitesters.RAMAddonRepository;
import UseCasesTest.daitesters.RAMShopRepository;
import UseCasesTest.daitesters.RAMVendorRepository;
import businessrules.addon.usecases.GetShopAddonsInteractor;
import businessrules.addon.usecases.ModifyAddonInteractor;
import businessrules.outputboundaries.ObjectBoundary;
import businessrules.outputboundaries.RepositoryBoundary;
import businessrules.outputboundaries.ResponseObject;
import businessrules.outputboundaries.VendorBoundary;
import entities.Addon;
import entities.Vendor;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import UseCasesTest.daitesters.RAMAddonRepository;
import UseCasesTest.daitesters.RAMVendorRepository;
import UseCasesTest.daitesters.RAMShopRepository;

class GetShopAddonsInteractorTest {
    RAMVendorRepository vendorRepository;
    RAMShopRepository shopRepository;
    RAMAddonRepository addonRepository;
   GetShopAddonsInteractor getShopAddonsInteractor;
    VendorBoundary vendorBoundary;
    RepositoryBoundary repositoryBoundary;
    ObjectBoundary<Addon> addonObjectBoundary;
    List<Addon> addons;

    @Test
    void GetShopAddons(){
        Vendor vendor = new CreateAddonInteractorTest().setUp();
        //addons = new ArrayList<Addon>();
        repositoryBoundary = new RAMRepositoryBoundary();
        vendorBoundary = new RAMVendorBoundary();
        addonObjectBoundary = new RAMAddonObjectBoundary();
        vendorRepository = new RAMVendorRepository(vendor);
        shopRepository = new RAMShopRepository(vendor.getShop());
        Addon oldaddon = new Addon("id1", "oldaddon", 199, null, true, vendor.getShop().toString());
        addonRepository = new RAMAddonRepository(oldaddon);
        //his.addons = new ArrayList<Addon>();
        getShopAddonsInteractor = new GetShopAddonsInteractor(addonRepository, repositoryBoundary, addonObjectBoundary );
        Object addons= getShopAddonsInteractor.getShopAddons(vendor.getShop().getId()).getContents();
        //assertEquals(addons, addonRepository);


    }

}