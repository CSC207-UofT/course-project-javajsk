package UseCasesTest.Food;

import UseCasesTest.TestBoundaries.RAMFoodObjectBoundary;
import UseCasesTest.TestBoundaries.RAMRepositoryBoundary;
import UseCasesTest.TestBoundaries.RAMVendorBoundary;
import UseCasesTest.daitesters.RAMFoodRepository;
import UseCasesTest.daitesters.RAMVendorRepository;
import businessrules.dai.Repository;
import businessrules.dai.VendorRepository;
import businessrules.food.usecases.AddSingletonInteractor;
import businessrules.outputboundaries.ObjectBoundary;
import businessrules.outputboundaries.RepositoryBoundary;
import businessrules.outputboundaries.ResponseObject;
import businessrules.outputboundaries.VendorBoundary;
import entities.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class AddSingletonInteractorTest {

    VendorRepository vR;
    Repository<Food> fR;
    RepositoryBoundary rB;
    ObjectBoundary<Food> fOB;
    VendorBoundary vB;
    Vendor vendor;
    Shop shop;
    Food burger;
    Singleton singleton2;
    AddSingletonInteractor useCase;

    @BeforeEach
    void setUp() {
        //building burger
        ArrayList<Integer> type = new ArrayList<>();
        type.add(1);
        Addon addon = new Addon("1", "Ketchup", 12, type, true, "12345");
        HashMap<Addon, Integer> SingletonSelection = new HashMap<>();
        SingletonSelection.put(addon ,3);
        Selection selection1 = new Selection(SingletonSelection);
        List<Selection> selections = new ArrayList<>();
        Singleton singleton = new Singleton("3", 10, "fries", "Fries", type, selection1, true, "shop1");
        selections.add(selection1);
        Singleton[] singletons = new Singleton[1];
        singletons[0] = singleton;
        burger = new Food("123", "burger combo", "A burger combo", 20, singletons, "00001");

        //build another singleton
        List<Integer> allowedAddonTypes = new ArrayList<>();
        allowedAddonTypes.add(1);
        allowedAddonTypes.add(2);
        Addon addon1 = new Addon("00000", "Lettuce", 0.5f,
                allowedAddonTypes, true, "00001");
        Addon addon2 = new Addon("00003", "Tomato", 0.5f,
                allowedAddonTypes, true, "00001");
        HashMap<Addon, Integer> singletonSelection = new HashMap<>();
        singletonSelection.put(addon1, 1);
        singletonSelection.put(addon2, 2);
        Selection defaultSelection = new Selection(singletonSelection);
        singleton2 = new Singleton("12345", 15f, "Cheeseburger", "Burger with cheese",
                allowedAddonTypes, defaultSelection, true, "00001");

        shop = new Shop("00001", "JavaJShop", "Bay Street", true, new Menu(), new OrderBook());
        vendor = new Vendor("12345", "Username", "Password", shop);
        vR = new RAMVendorRepository(vendor);
        fR = new RAMFoodRepository(burger);
        rB = new RAMRepositoryBoundary();
        vB = new RAMVendorBoundary();
        fOB = new RAMFoodObjectBoundary();
        useCase = new AddSingletonInteractor(vR, fR, rB, fOB, vB);
    }

    @Test
    void addSingleton() {
        Food updatedFood = burger;
        updatedFood.addSingleton(singleton2);
        Food food = (Food) useCase.addSingleton("Username", burger.getId(), singleton2).getContents();
        assertEquals(updatedFood, food);
    }

    @Test
    void addSingletonInvalidVendor() {
        ResponseObject foodObj = useCase.addSingleton("invalidToken", burger.getId(), singleton2);
        assertEquals("No such vendor found.", foodObj.getMessage());
    }

    @Test
    void addSingletonInvalidFoodId() {
        Food updatedFood = burger;
        updatedFood.addSingleton(singleton2);
        ResponseObject foodObj = useCase.addSingleton(vendor.getUserName(), "invalidId", singleton2);
        assertEquals("No such food found.", foodObj.getMessage());
    }

    @Test
    void addSingletonNoVendorPermission() {
        Shop newShop = new Shop("2700", "truck", "St. George", true, new Menu(), new OrderBook());
        Vendor newVendor = new Vendor("0072", "username2", "password2", newShop);
        vR.create(newVendor);
        ResponseObject foodObj = useCase.addSingleton(newVendor.getUserName(), burger.getId(), singleton2);
        assertEquals("You do not have authority to modify this food.", foodObj.getMessage());
    }

    @Test
    void addSingletonNotShopSingleton() {
        singleton2.setShopId("2700");
        ResponseObject foodObj = useCase.addSingleton(vendor.getUserName(), burger.getId(), singleton2);
        assertEquals("The singleton does not belong to your shop.", foodObj.getMessage());
    }

}