package UseCasesTest.Vendor;

import UseCasesTest.TestBoundaries.RAMRepositoryBoundary;
import UseCasesTest.TestBoundaries.RAMVendorBoundary;
import UseCasesTest.daitesters.RAMVendorRepository;
import adapters.SHA512Hasher;
import businessrules.dai.Hasher;
import businessrules.dai.VendorRepository;
import businessrules.outputboundaries.RepositoryBoundary;
import businessrules.outputboundaries.ResponseObject;
import businessrules.outputboundaries.VendorBoundary;
import businessrules.vendor.usecases.VendorLoginInteractor;
import entities.Menu;
import entities.OrderBook;
import entities.Shop;
import entities.Vendor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class VendorLoginInteractorTest {
    VendorRepository vendorRepository;
    VendorBoundary vendorBoundary;
    RepositoryBoundary repositoryBoundary;
    Hasher hasher;
    Vendor vendor;
    VendorLoginInteractor vendorLoginInteractor;

    @BeforeEach
    void setUp() {
        Menu menu = new Menu();
        OrderBook orderBook = new OrderBook();
        Shop shop = new Shop("86784", "JavaJShop", "Bay Street", true, menu, orderBook);
        vendor = new Vendor("12345", "Username", "Password", shop);
        vendorRepository = new RAMVendorRepository(vendor);
        vendorBoundary = new RAMVendorBoundary();
        repositoryBoundary = new RAMRepositoryBoundary();
        hasher = new SHA512Hasher();
        vendorLoginInteractor = new VendorLoginInteractor(vendorRepository, vendorBoundary, repositoryBoundary,
                hasher);
    }

    @Test
    void login() {
        ResponseObject responseObject = vendorLoginInteractor.login("Username", "Password");
        assertEquals("User authenticated", responseObject.getContents());
    }

    @Test
    void unableLogin(){
        ResponseObject responseObject = vendorLoginInteractor.login("Username1", "Password1");
        assertEquals("Incorrect username or password. Please try again.", responseObject.getMessage());
    }
}