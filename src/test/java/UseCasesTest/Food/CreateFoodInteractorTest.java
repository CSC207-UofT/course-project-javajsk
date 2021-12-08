package UseCasesTest.Food;

import UseCasesTest.TestBoundaries.RAMFoodObjectBoundary;
import UseCasesTest.TestBoundaries.RAMRepositoryBoundary;
import UseCasesTest.daitesters.RAMFoodRepository;
import UseCasesTest.daitesters.RAMVendorRepository;
import businessrules.dai.Repository;
import businessrules.dai.VendorRepository;
import businessrules.food.usecases.CreateFoodInteractor;
import businessrules.outputboundaries.ObjectBoundary;
import businessrules.outputboundaries.RepositoryBoundary;
import entities.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;


class CreateFoodInteractorTest {

    VendorRepository vR;
    Repository<Food> fR;
    RepositoryBoundary rB;
    ObjectBoundary<Food> fOB;
    Vendor vendor;
    Food burger;
    Shop shop;
    CreateFoodInteractor useCase;
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
    fR = new RAMFoodRepository();
    rB = new RAMRepositoryBoundary();
    fOB = new RAMFoodObjectBoundary();
    useCase = new CreateFoodInteractor(vR, fR, rB, fOB);
    }

    @Test
    void createFood() {
        List<Food> foods = (List<Food>) useCase.createFood("Username", burger).getContents();
        assertEquals(burger, foods.get(0));
    }

    @Test
    void createFoodInvalidVendor() {
        assertEquals("Unable to find such a vendor.", useCase.createFood("invalidToken", burger).getMessage());
    }

}