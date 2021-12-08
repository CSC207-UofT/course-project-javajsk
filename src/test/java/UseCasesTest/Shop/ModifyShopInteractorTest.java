package UseCasesTest.Shop;

import UseCasesTest.TestBoundaries.RAMRepositoryBoundary;
import UseCasesTest.TestBoundaries.RAMShopObjectBoundary;
import UseCasesTest.TestBoundaries.RAMVendorBoundary;
import UseCasesTest.daitesters.RAMShopRepository;
import UseCasesTest.daitesters.RAMVendorRepository;
import businessrules.dai.Repository;
import businessrules.dai.VendorRepository;
import businessrules.outputboundaries.ObjectBoundary;
import businessrules.outputboundaries.RepositoryBoundary;
import businessrules.outputboundaries.ResponseObject;
import businessrules.outputboundaries.VendorBoundary;
import businessrules.shop.usecases.ModifyShopInteractor;
import entities.Menu;
import entities.OrderBook;
import entities.Shop;
import entities.Vendor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ModifyShopInteractorTest {

    VendorRepository vR;
    RepositoryBoundary rB;
    Repository<Shop> sR;
    VendorBoundary vB;
    ObjectBoundary<Shop> sOB;
    Vendor vendor;
    Shop shop;
    ModifyShopInteractor useCase;

    @BeforeEach
    void setUp() {
        shop = new Shop("00001", "JavaJShop", "Bay Street", true, new Menu(), new OrderBook());
        vendor = new Vendor("12345", "Username", "Password", shop);
        vR = new RAMVendorRepository(vendor);

        sR = new RAMShopRepository(shop);
        sOB = new RAMShopObjectBoundary();
        rB = new RAMRepositoryBoundary();
        vB = new RAMVendorBoundary();

        useCase = new ModifyShopInteractor(vR, rB, sR, vB, sOB);

    }

    @Test
    void modifyShop() {
        Shop updatedShop = new Shop("00001", "truck2", "Bahen", true,
                new Menu(), new OrderBook());
        useCase.modifyShop(vendor.getUserName(),updatedShop);
        assertEquals("Bahen", vendor.getShop().getLocation());
        assertEquals("truck2", vendor.getShop().getName());
    }

    @Test
    void modifyShopInvalidVendor() {
        ResponseObject shopObj = useCase.modifyShop("invalidToken", shop);
        assertEquals("No such vendor found.", shopObj.getMessage());
    }

}