package UseCasesTest.Shop;

import UseCasesTest.TestBoundaries.RAMRepositoryBoundary;
import UseCasesTest.TestBoundaries.RAMShopObjectBoundary;
import UseCasesTest.daitesters.RAMShopRepository;
import UseCasesTest.daitesters.RAMVendorRepository;
import businessrules.dai.Repository;
import businessrules.dai.VendorRepository;
import businessrules.outputboundaries.ObjectBoundary;
import businessrules.outputboundaries.RepositoryBoundary;
import businessrules.outputboundaries.ResponseObject;
import businessrules.shop.usecases.ChangeShopStatusInteractor;
import entities.Menu;
import entities.OrderBook;
import entities.Shop;
import entities.Vendor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ChangeShopStatusInteractorTest {

    VendorRepository vR;
    Repository<Shop> sR;
    RepositoryBoundary rB;
    ObjectBoundary<Shop> sOB;
    ChangeShopStatusInteractor useCase;
    Vendor vendor;
    Shop shop;

    @BeforeEach
    void setUp() {
        shop = new Shop("00001", "JavaJShop", "Bay Street", true, new Menu(), new OrderBook());
        vendor = new Vendor("12345", "Username", "Password", shop);
        vR = new RAMVendorRepository(vendor);

        sR = new RAMShopRepository(shop);
        sOB = new RAMShopObjectBoundary();
        rB = new RAMRepositoryBoundary();

        useCase = new ChangeShopStatusInteractor(vR, sR, rB, sOB);


    }

    @Test
    void changeShopStatusToClosedTest() {
        Shop tempShop = (Shop) useCase.changeShopStatus(vendor.getId(), false).getContents();
        assertFalse(tempShop.isOpen());
    }

    @Test
    void changeShopStatusToOpenedTest() {
        shop.setOpen(false);
        Shop shop = (Shop) useCase.changeShopStatus(vendor.getId(), true).getContents();
        assertTrue(shop.isOpen());
    }

    @Test
    void changeShopStatusInvalidVendor() {
        ResponseObject shopObj = useCase.changeShopStatus("invalidToken", false);
        assertEquals("No such vendor found.", shopObj.getMessage());
    }
}