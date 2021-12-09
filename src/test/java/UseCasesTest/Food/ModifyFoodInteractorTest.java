package UseCasesTest.Food;

import UseCasesTest.TestBoundaries.RAMFoodObjectBoundary;
import UseCasesTest.TestBoundaries.RAMRepositoryBoundary;
import UseCasesTest.TestBoundaries.RAMVendorBoundary;
import UseCasesTest.daitesters.RAMFoodRepository;
import UseCasesTest.daitesters.RAMVendorRepository;
import businessrules.dai.Repository;
import businessrules.dai.VendorRepository;
import businessrules.food.usecases.ModifyFoodInteractor;
import businessrules.outputboundaries.ObjectBoundary;
import businessrules.outputboundaries.RepositoryBoundary;
import businessrules.outputboundaries.VendorBoundary;
import entities.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class ModifyFoodInteractorTest {

    VendorRepository vR;
    Repository<Food> fR;
    RepositoryBoundary rB;
    ObjectBoundary<Food> fOB;
    VendorBoundary vB;
    ModifyFoodInteractor useCase;
    Vendor vendor;
    Shop shop;
    Food burger;
    Singleton[] singletons;

    @BeforeEach
    void setUp() {
        ArrayList<Integer> type = new ArrayList<>();
        type.add(1);
        Addon addon = new Addon("1", "Ketchup", 12, type, true, "12345");
        HashMap<Addon, Integer> SingletonSelection = new HashMap<>();
        SingletonSelection.put(addon ,3);
        Selection selection1 = new Selection(SingletonSelection);
        List<Selection> selections = new ArrayList<>();
        Singleton singleton = new Singleton("3", 10, "fries", "Fries", type, selection1, true, "shop1");
        selections.add(selection1);
        singletons = new Singleton[1];
        singletons[0] = singleton;
        burger = new Food("123", "burger combo", "A burger combo", 20, singletons, "00001");


        shop = new Shop("00001", "JavaJShop", "Bay Street", true, new Menu(), new OrderBook());
        vendor = new Vendor("12345", "Username", "Password", shop);
        vR = new RAMVendorRepository(vendor);
        fR = new RAMFoodRepository(burger);
        rB = new RAMRepositoryBoundary();
        fOB = new RAMFoodObjectBoundary();
        vB = new RAMVendorBoundary();

        useCase = new ModifyFoodInteractor(vR, fR, rB, fOB, vB);
    }

    @Test
    void modifyFood() {
        Food modifiedFood = new Food("123", "new burger", "A burger combo", 100, singletons, "00001");
        Food foodUpdated = (Food) useCase.modifyFood("12345", "123", modifiedFood).getContents();
        assertEquals("new burger", foodUpdated.getName());
        assertEquals(100, foodUpdated.getPrice());
    }

    @Test
    void modifyFoodInvalidVendor() {
        assertEquals("No such vendor found", useCase.modifyFood("InvalidToken", "123", burger).getMessage());
    }

    @Test
    void modifyFoodInvalidFood() {
        assertEquals("No such food found", useCase.modifyFood("12345", "invalidFood", burger).getMessage());
    }

    @Test
    void modifyFoodUnauthorizedAccess() {
        Shop newShop = new Shop("270", "Truck", "Robarts", true, new Menu(), new OrderBook());
        Vendor newVendor = new Vendor("072", "Truck", "TruckPass", newShop);
        vR.create(newVendor);
        assertEquals("You do not have access to modify this food.", useCase.modifyFood("072", "123", burger).getMessage());
    }

    @Test
    void modifyFoodChangeFoodId() {
        Food food =  new Food("72", "new food", "a nice combo", 20, singletons, "5");
        assertEquals("Food ids cannot be altered.", useCase.modifyFood("12345", "123", food).getMessage());
    }

    @Test
    void modifyFoodChangeFoodShopId() {
        Food food =  new Food("123", "new food", "a nice combo", 20, singletons, "22");
        assertEquals("ShopId cannot be altered.", useCase.modifyFood("12345", "123", food).getMessage());
    }

}