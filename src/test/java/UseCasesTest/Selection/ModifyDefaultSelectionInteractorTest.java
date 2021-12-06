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
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ModifyDefaultSelectionInteractorTest {
    ModifyDefaultSelectionInteractor useCase;
    Selection selection;
    RAMVendorRepository vR;
    RAMSingletonRepository sR;
    ObjectBoundary<Singleton> sOB;
    VendorBoundary vB;
    RepositoryBoundary rB;
    List<Integer> allowedAddonTypes;
    Selection defaultSelection;
    Singleton updatedSingleton;
    String vendorToken;


    @BeforeEach
    void setUp() {

        allowedAddonTypes = new ArrayList<>();
        allowedAddonTypes.add(1);
        allowedAddonTypes.add(2);
        Addon addon1 = new Addon("00000", "Lettuce", 0.5f,
                allowedAddonTypes, true, "00001");
        Addon addon2 = new Addon("00003", "Tomato", 0.5f,
                allowedAddonTypes, true, "00001");
        HashMap<Addon, Integer> singletonSelection = new HashMap<>();
        singletonSelection.put(addon1, 1);
        singletonSelection.put(addon2, 2);
        defaultSelection = new Selection(singletonSelection);
        Singleton singleton = new Singleton("12345", 15f, "Cheeseburger", "Burger with cheese",
                allowedAddonTypes, defaultSelection, true, "00001");
        sR = new RAMSingletonRepository(singleton);
        Shop shop = new Shop("00001", "JavaJShop", "Bay Street", true, new Menu(), new OrderBook());
        vR = new RAMVendorRepository(new Vendor("12345", "vendorName", "vendorPass", shop));
        rB = new RAMRepositoryBoundary();
        vB = new RAMVendorBoundary();
        sOB = new RAMSingletonObjectBoundary();
        Addon addon3 = new Addon("00004", "Bacon", 0.5f,
                allowedAddonTypes, true, "00001");
        singletonSelection.put(addon3, 1);
        selection = new Selection(singletonSelection);
        updatedSingleton.setDefaultSelection(selection);
        useCase = new ModifyDefaultSelectionInteractor(vR, rB, sR, vB, sOB);
        vendorToken = vR.authenticateUser("vendorName", "vendorPass");

    }

    @Test
    void modifyDefaultSelection() {
        assertEquals(updatedSingleton, useCase.modifyDefaultSelection("vendorName", "12345", selection).getContents());
    }

    @Test
    void modifyDefaultSelectionInvalidVendor() {
        assertEquals("No such vendor found", useCase.modifyDefaultSelection("invalidVendor", "12345", selection).getMessage());
    }

    @Test
    void modifyDefaultSelectionInvalidSingleton() {
        assertEquals("No such singleton found", useCase.modifyDefaultSelection("vendorName", "invalidSingletonId", selection).getMessage());
    }
}