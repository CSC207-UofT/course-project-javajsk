package UseCasesTest.Menu;

import UseCasesTest.TestBoundaries.RAMAddonObjectBoundary;
import UseCasesTest.TestBoundaries.RAMFoodObjectBoundary;
import UseCasesTest.TestBoundaries.RAMMenuObjectBoundary;
import UseCasesTest.TestBoundaries.RAMRepositoryBoundary;
import UseCasesTest.daitesters.RAMAddonRepository;
import UseCasesTest.daitesters.RAMShopRepository;
import UseCasesTest.daitesters.RAMVendorRepository;
import businessrules.menu.usecases.AddAddonToMenuInteractor;
import businessrules.menu.usecases.AddFoodToMenuInteractor;
import businessrules.menu.usecases.GetAvailableAddonsInteractor;
import businessrules.menu.usecases.GetAvailableFoodsInteractor;
import businessrules.outputboundaries.ObjectBoundary;
import businessrules.outputboundaries.RepositoryBoundary;
import businessrules.outputboundaries.ResponseObject;
import businessrules.outputboundaries.VendorBoundary;
import entities.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GetAvailableFoodsInteractorTest {
    RAMVendorRepository vendorRepository;
    RAMShopRepository shopRepository;
    RAMAddonRepository addonRepository;
    VendorBoundary vendorBoundary;
    RepositoryBoundary repositoryBoundary;
    ObjectBoundary<Addon> addonObjectBoundary;
    RAMMenuObjectBoundary menuObjectBoundary;
    GetAvailableFoodsInteractor getAvailableFoodsInteractor;
    AddFoodToMenuInteractor addFoodToMenuInteractor;
    ObjectBoundary<Food> foodObjectBoundary;

    @Test
    void getAvailableFoods() {
        Vendor vendor = new AddAddonToMenuInteractorTest().SetUp();
        foodObjectBoundary = new RAMFoodObjectBoundary();
        shopRepository = new RAMShopRepository(vendor.getShop());
        repositoryBoundary = new RAMRepositoryBoundary();
        addonObjectBoundary = new RAMAddonObjectBoundary();
        menuObjectBoundary = new RAMMenuObjectBoundary();
        vendorRepository = new RAMVendorRepository(vendor);
        Singleton[] components = new Singleton[1];
        components[0] = new Singleton("1", 12, "test", null, null, null, true, vendor.getShop().getId());
        Food food = new Food("food1", "regular food", null, 111, components, vendor.getShop().getId());
        getAvailableFoodsInteractor = new GetAvailableFoodsInteractor(shopRepository, repositoryBoundary,foodObjectBoundary);
        addFoodToMenuInteractor = new AddFoodToMenuInteractor(vendorRepository, repositoryBoundary, vendorBoundary, shopRepository, menuObjectBoundary);
        addFoodToMenuInteractor.addFood(vendor.getId(), food);
        ResponseObject response = getAvailableFoodsInteractor.getAvailableFoods(vendor.getShop().getId());
        assertEquals("Test Works", response.getMessage());
        assertEquals("No such shop found.", getAvailableFoodsInteractor.getAvailableFoods("fakeshop").getMessage());
        Food food1 = new Food("222", "test2", null, 1, components, vendor.getShop().getId());
        addFoodToMenuInteractor.addFood(vendor.getId(), food1);
        ResponseObject response1 = getAvailableFoodsInteractor.getAvailableFoods(vendor.getShop().getId());
        assertEquals("Test Works", response1.getMessage());
    }
}