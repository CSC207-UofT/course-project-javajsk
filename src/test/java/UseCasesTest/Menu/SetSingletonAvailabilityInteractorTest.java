package UseCasesTest.Menu;

import UseCasesTest.TestBoundaries.RAMFoodObjectBoundary;
import UseCasesTest.TestBoundaries.RAMMenuObjectBoundary;
import UseCasesTest.TestBoundaries.RAMRepositoryBoundary;
import UseCasesTest.TestBoundaries.RAMVendorBoundary;
import UseCasesTest.daitesters.RAMFoodRepository;
import UseCasesTest.daitesters.RAMShopRepository;
import UseCasesTest.daitesters.RAMSingletonRepository;
import UseCasesTest.daitesters.RAMVendorRepository;
import businessrules.food.usecases.AddSingletonInteractor;
import businessrules.menu.usecases.AddAddonToMenuInteractor;
import businessrules.menu.usecases.AddFoodToMenuInteractor;
import businessrules.menu.usecases.SetAddonAvailabilityInteractor;
import businessrules.menu.usecases.SetSingletonAvailabilityInteractor;
import businessrules.outputboundaries.RepositoryBoundary;
import businessrules.outputboundaries.VendorBoundary;
import entities.Food;
import entities.Singleton;
import entities.Vendor;
import org.junit.jupiter.api.Test;

class SetSingletonAvailabilityInteractorTest {
    RAMVendorRepository vendorRepository;
    RAMShopRepository shopRepository;
    VendorBoundary vendorBoundary;
    RepositoryBoundary repositoryBoundary;
    RAMMenuObjectBoundary menuObjectBoundary;
    AddSingletonInteractor addSingletonInteractor;
    RAMFoodRepository foodRepository;
    RAMFoodObjectBoundary foodObjectBoundary;
    RAMSingletonRepository singletonRepository;
    SetSingletonAvailabilityInteractor setSingletonAvailabilityInteractor;
    AddFoodToMenuInteractor addFoodToMenuInteractor;

    @Test
    void setSingletonAvailability() {
        Vendor vendor = new AddAddonToMenuInteractorTest().SetUp();
        Singleton[] components = new Singleton[1];
        components[0] = new Singleton("id111", 34, "singleton", null, null, null, true, vendor.getShop().getId());
        Food food = new Food("id1", "food", null, 12, components, vendor.getShop().getId());
        singletonRepository = new RAMSingletonRepository(components[0]);
        foodObjectBoundary = new RAMFoodObjectBoundary();
        foodRepository = new RAMFoodRepository(food);
        vendorRepository = new RAMVendorRepository(vendor);
        repositoryBoundary = new RAMRepositoryBoundary();
        vendorBoundary = new RAMVendorBoundary();
        shopRepository = new RAMShopRepository(vendor.getShop());
        menuObjectBoundary = new RAMMenuObjectBoundary();
        addFoodToMenuInteractor = new AddFoodToMenuInteractor(vendorRepository,repositoryBoundary,vendorBoundary,shopRepository,menuObjectBoundary);
        addFoodToMenuInteractor.addFood(vendor.getId(),food);
        addSingletonInteractor = new AddSingletonInteractor(vendorRepository, foodRepository, repositoryBoundary,foodObjectBoundary, vendorBoundary);
        setSingletonAvailabilityInteractor = new SetSingletonAvailabilityInteractor(menuObjectBoundary,vendorRepository,repositoryBoundary,vendorBoundary,singletonRepository, shopRepository);
        addSingletonInteractor.addSingleton(vendor.getId(),food.getId(), components[0]);
        assertTrue(vendor.getShop().getMenu().getFood(food).getComponents()[0].isAvailable());
        setSingletonAvailabilityInteractor.setSingletonAvailability(vendor.getId(), components[0], false);
        assertFalse(vendor.getShop().getMenu().getFood(food).getComponents()[0].isAvailable());

    }
}