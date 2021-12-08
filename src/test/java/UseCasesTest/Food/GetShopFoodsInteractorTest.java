package UseCasesTest.Food;

import UseCasesTest.TestBoundaries.RAMFoodObjectBoundary;
import UseCasesTest.daitesters.RAMFoodRepository;
import businessrules.dai.Repository;
import businessrules.food.usecases.GetShopFoodsInteractor;
import businessrules.outputboundaries.ObjectBoundary;
import entities.Addon;
import entities.Food;
import entities.Selection;
import entities.Singleton;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class GetShopFoodsInteractorTest {

    Repository<Food> fR;
    ObjectBoundary<Food> fOB;
    GetShopFoodsInteractor useCase;
    Food burger;

    @BeforeEach
    void setUp() {
        //create a food entity
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

        fR = new RAMFoodRepository(burger);
        fOB = new RAMFoodObjectBoundary();
        useCase = new GetShopFoodsInteractor(fR, fOB);

    }

    @Test
    void getShopFoods() {
        List<Food> foods = (List<Food>) useCase.getShopFoods("00001").getContents();
        assertEquals(burger, foods.get(0));
    }
}