package UseCasesTest.Menu;

import UseCasesTest.TestBoundaries.RAMMenuObjectBoundary;
import UseCasesTest.TestBoundaries.RAMRepositoryBoundary;
import UseCasesTest.TestBoundaries.RAMVendorBoundary;
import UseCasesTest.daitesters.RAMAddonRepository;
import UseCasesTest.daitesters.RAMShopRepository;
import UseCasesTest.daitesters.RAMVendorRepository;
import businessrules.menu.usecases.AddAddonToMenuInteractor;
import businessrules.menu.usecases.AddFoodToMenuInteractor;
import businessrules.menu.usecases.RemoveAddonFromMenuInteractor;
import businessrules.menu.usecases.RemoveFoodFromMenuInteractor;
import businessrules.outputboundaries.ObjectBoundary;
import businessrules.outputboundaries.RepositoryBoundary;
import businessrules.outputboundaries.VendorBoundary;
import entities.Addon;
import entities.Food;
import entities.Singleton;
import entities.Vendor;
import org.apache.catalina.authenticator.SingleSignOnEntry;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RemoveFoodFromMenuInteractorTest {
    RAMVendorRepository vendorRepository;
    RAMShopRepository shopRepository;
    RAMAddonRepository addonRepository;
    VendorBoundary vendorBoundary;
    RepositoryBoundary repositoryBoundary;
    ObjectBoundary<Addon> addonObjectBoundary;
    RAMMenuObjectBoundary menuObjectBoundary;
    AddFoodToMenuInteractor addFoodToMenuInteractor;
    RemoveFoodFromMenuInteractor removeFoodFromMenuInteractor;
    @Test
    void removeFood() {
        Vendor vendor = new AddAddonToMenuInteractorTest().SetUp();
        vendorRepository = new RAMVendorRepository(vendor);
        repositoryBoundary = new RAMRepositoryBoundary();
        vendorBoundary = new RAMVendorBoundary();
        shopRepository = new RAMShopRepository(vendor.getShop());
        menuObjectBoundary = new RAMMenuObjectBoundary();
        addFoodToMenuInteractor = new AddFoodToMenuInteractor(vendorRepository,repositoryBoundary,vendorBoundary,shopRepository,menuObjectBoundary);
        removeFoodFromMenuInteractor = new RemoveFoodFromMenuInteractor(vendorRepository,repositoryBoundary,vendorBoundary,shopRepository,menuObjectBoundary);
        Singleton singleton = new Singleton("singleton1", 12, "name", null, null, null, true, vendor.getShop().getId());
        Singleton[] components = new Singleton[1];
        components[0] = singleton;
        Food food = new Food("food2", "regular food", null, 444, components, vendor.getShop().getId());
        addFoodToMenuInteractor.addFood(vendor.getId(), food);
        assertTrue(vendor.getShop().getMenu().getFood(food).isAvailable());
        removeFoodFromMenuInteractor.removeFood(vendor.getId(),food);
        assertFalse(vendor.getShop().getMenu().getFoods().contains(food));


    }
}