package UseCasesTest.Selection;

import UseCasesTest.TestBoundaries.RAMCartObjectBoundary;
import UseCasesTest.TestBoundaries.RAMCustomerBoundary;
import UseCasesTest.daitesters.RAMCustomerRepository;
import UseCasesTest.daitesters.RAMFoodRepository;
import UseCasesTest.TestBoundaries.RAMRepositoryBoundary;
import businessrules.dai.CustomerRepository;
import businessrules.dai.Repository;
import businessrules.outputboundaries.CustomerBoundary;
import businessrules.outputboundaries.ObjectBoundary;
import businessrules.outputboundaries.RepositoryBoundary;
import businessrules.selection.usecases.ModifySelectionInCartInteractor;
import entities.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class ModifySelectionInCartInteractorTest {
    CustomerRepository cR;
    Repository<Food> fR;
    RepositoryBoundary rB;
    CustomerBoundary cB;
    ObjectBoundary<Cart> cOB;
    ModifySelectionInCartInteractor useCase;
    Cart cart;
    HashMap<Food, List<Selection[]>> contents;
    Selection defaultSelection;
    Food burger;
    List<Selection[]> selectionListList;

    @BeforeEach
    void setUp() {
        //create an addon and a list of integers for allowed addon types
        ArrayList<Integer> allowedTypes = new ArrayList<>();
        allowedTypes.add(1);
        Addon addon1 = new Addon("1", "Ketchup", 12, allowedTypes, true, "shop1");

        // build selection hash map of addon to integers (default selection)
        HashMap<Addon, Integer> selectionHash = new HashMap<>();
        selectionHash.put(addon1, 2);
        defaultSelection = new Selection(selectionHash);

        //build a singleton with the allowed addon types and the default selection
        Singleton singleton = new Singleton("3", 10, "burger ", "A burger", allowedTypes, defaultSelection, true, "shop1");

        // make a food by putting the one singleton in a list
        burger = new Food("123", "burger combo", "A burger combo", 15, new Singleton[]{singleton}, "shop1");

        // create a cart with the one food item mapped to a list with the default selection
        contents = new HashMap<>();
        selectionListList = new ArrayList<>();
        selectionListList.add(new Selection[]{defaultSelection});
        contents.put(burger,selectionListList);
        cart =  new Cart("cart1", "shop1", contents);




        fR = new RAMFoodRepository(burger);
        Customer customer = new Customer("1000", "custName", "custPass", new OrderBook(), cart );
        cR = new RAMCustomerRepository(customer);

        rB = new RAMRepositoryBoundary();
        cB = new RAMCustomerBoundary();
        cOB = new RAMCartObjectBoundary();
        useCase = new ModifySelectionInCartInteractor(cR, fR, rB, cB, cOB);

    }

    @Test
    void modifySelection() {
        //make a new food in the cart that is a burger with no addons
        HashMap<Addon, Integer> selectionHash = new HashMap<>();
        //empty selection
        Selection newSelection = new Selection(selectionHash);
        Cart updatedCart = (Cart) useCase.modifySelection("1000", "123", selectionListList.get(0), new Selection[]{newSelection}).getContents();
        HashMap<Food, List<Selection[]>> updatedContents = updatedCart.getContents();
        List<Selection[]> newSelectionListList = new ArrayList<>();
        newSelectionListList.add(new Selection[]{newSelection});

        assertEquals(Arrays.toString(newSelectionListList.get(0)), Arrays.toString(updatedContents.get(burger).get(0)));
    }
    @Test
    void modifySelectionInvalidToken(){
        assertEquals("No such customer found.", useCase.modifySelection("invalidToken", "123", new Selection[]{defaultSelection}, new Selection[]{defaultSelection}).getMessage());
    }

    @Test
    void modifySelectionInvalidFood(){
        assertEquals("No such food found.", useCase.modifySelection("1000", "InvalidFood", new Selection[]{defaultSelection}, new Selection[]{defaultSelection}).getMessage());
    }

    @Test
    void modifySelectionInvalidAddonType(){
        //create addon of wrong type
        ArrayList<Integer> allowedTypes = new ArrayList<>();
        allowedTypes.add(52);
        Addon newAddon = new Addon("0002", "sprinkles", 2, allowedTypes, true, "shop1");

        // build selection hash map of addon to integers (default selection)
        HashMap<Addon, Integer> selectionHash = new HashMap<>();
        selectionHash.put(newAddon, 2);
        Selection newSelection = new Selection(selectionHash);
        assertEquals("Invalid selection provided. Please try again.", useCase.modifySelection("1000", "123", new Selection[]{defaultSelection}, new Selection[]{newSelection}).getMessage());
    }
}