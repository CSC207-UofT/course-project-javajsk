package UseCasesTest.Menu;

import UseCasesTest.TestBoundaries.RAMMenuObjectBoundary;
import UseCasesTest.TestBoundaries.RAMRepositoryBoundary;
import UseCasesTest.TestBoundaries.RAMVendorBoundary;
import UseCasesTest.daitesters.RAMAddonRepository;
import UseCasesTest.daitesters.RAMShopRepository;
import UseCasesTest.daitesters.RAMVendorRepository;
import businessrules.menu.usecases.AddAddonToMenuInteractor;
import businessrules.menu.usecases.AddFoodToMenuInteractor;
import businessrules.outputboundaries.ObjectBoundary;
import businessrules.outputboundaries.RepositoryBoundary;
import businessrules.outputboundaries.VendorBoundary;
import entities.Addon;
import entities.Food;
import entities.Vendor;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class AddFoodToMenuInteractorTest {
    RAMVendorRepository vendorRepository;
    RAMShopRepository shopRepository;
    RAMAddonRepository addonRepository;
    VendorBoundary vendorBoundary;
    RepositoryBoundary repositoryBoundary;
    ObjectBoundary<Addon> addonObjectBoundary;
    RAMMenuObjectBoundary menuObjectBoundary;
    AddFoodToMenuInteractor addFoodToMenuInteractor;

    @Test
    void addFood() {
        Vendor vendor = new AddAddonToMenuInteractorTest().SetUp();
        vendorRepository = new RAMVendorRepository(vendor);
        repositoryBoundary = new RAMRepositoryBoundary();
        vendorBoundary = new RAMVendorBoundary();
        shopRepository = new RAMShopRepository(vendor.getShop());
        menuObjectBoundary = new RAMMenuObjectBoundary();
        addFoodToMenuInteractor = new AddFoodToMenuInteractor(vendorRepository, repositoryBoundary, vendorBoundary, shopRepository, menuObjectBoundary);
        Food food = new Food("id11", "regular food",null, 5,null, vendor.getShop().getId());
        addFoodToMenuInteractor.addFood(vendor.getId(), food);
        assertTrue(vendor.getShop().getMenu().getFoods().contains(food));
        Food food1 = new Food("id12", "not a regular food", null, 10, null, "fakeshop");
        assertSame("This food does not belong to this shop.", addFoodToMenuInteractor.addFood(vendor.getId(), food1).getMessage());
    }
}