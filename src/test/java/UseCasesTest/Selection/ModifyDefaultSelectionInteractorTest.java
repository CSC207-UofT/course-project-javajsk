package UseCasesTest.Selection;

import UseCasesTest.TestBoundaries.RAMSingletonObjectBoundary;
import UseCasesTest.TestBoundaries.RAMVendorBoundary;
import UseCasesTest.TestBoundaries.RAMRepositoryBoundary;
import UseCasesTest.daitesters.RAMSingletonRepository;
import UseCasesTest.daitesters.RAMVendorRepository;
import businessrules.outputboundaries.ObjectBoundary;
import businessrules.outputboundaries.RepositoryBoundary;
import businessrules.outputboundaries.VendorBoundary;
import businessrules.selection.usecases.ModifyDefaultSelectionInteractor;
import entities.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.HashMap;
import static org.junit.jupiter.api.Assertions.*;

class ModifyDefaultSelectionInteractorTest {
    ModifyDefaultSelectionInteractor useCase;
    Selection selection;
    RAMVendorRepository vR;
    RAMSingletonRepository sR;
    ObjectBoundary<Singleton> sOB;
    VendorBoundary vB;
    RepositoryBoundary rB;
    Vendor vendor;
    Selection defaultSelection;
    Selection newDefaultSelection;


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

        //build a new default selection
        ArrayList<Integer> newAllowedTypes = new ArrayList<>();
        newAllowedTypes.add(3);
        Addon addon2 = new Addon("1", "sprinkles", 12, allowedTypes, true, "shop1");
        HashMap<Addon, Integer> newSelectionHash = new HashMap<>();
        newSelectionHash.put(addon2, 2);
        newDefaultSelection = new Selection(selectionHash);

        Shop shop = new Shop("00001", "JavaJShop", "Bay Street", true, new Menu(), new OrderBook());
        vendor = new Vendor("12345", "Username", "Password", shop);
        vR = new RAMVendorRepository(vendor);
        sR = new RAMSingletonRepository(singleton);
        sOB = new RAMSingletonObjectBoundary();
        vB = new RAMVendorBoundary();
        rB = new RAMRepositoryBoundary();
        useCase = new ModifyDefaultSelectionInteractor(vR, rB, sR, vB, sOB);
    }

    @Test
    void modifyDefaultSelection() {
        Singleton updatedSingleton = (Singleton) useCase.modifyDefaultSelection("12345", "3",
                newDefaultSelection).getContents();
        assertEquals(newDefaultSelection, updatedSingleton.getDefaultSelection());
    }

    @Test
    void modifyDefaultSelectionInvalidVendor() {
        assertEquals("No such vendor found", useCase.modifyDefaultSelection("invalidVendor", "12345", selection).getMessage());
    }

    @Test
    void modifyDefaultSelectionInvalidSingleton() {
        assertEquals("No such singleton found", useCase.modifyDefaultSelection("12345", "invalidSingletonId", selection).getMessage());
    }

    @Test
    void modifyDefaultSelectionInvalidAddonType() {
        //build another selection with invalid addon types
        ArrayList<Integer> newAllowedTypes = new ArrayList<>();
        newAllowedTypes.add(3);
        Addon addon1 = new Addon("6", "sprinkles", 12, newAllowedTypes, true, "shop1");

        // build selection hash map of addon to integers (default selection)
        HashMap<Addon, Integer> newSelectionHash = new HashMap<>();
        newSelectionHash.put(addon1, 2);
        newDefaultSelection = new Selection(newSelectionHash);

        assertEquals("Incorrect values inputted for selection.",
                useCase.modifyDefaultSelection("12345", "3", newDefaultSelection).getMessage());
    }

}