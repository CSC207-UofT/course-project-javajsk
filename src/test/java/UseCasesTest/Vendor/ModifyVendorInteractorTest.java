package UseCasesTest.Vendor;

import UseCasesTest.TestBoundaries.RAMRepositoryBoundary;
import UseCasesTest.TestBoundaries.RAMVendorBoundary;
import UseCasesTest.TestBoundaries.RAMVendorObjectBoundary;
import UseCasesTest.daitesters.RAMVendorRepository;
import adapters.dam.SHA512Hasher;
import businessrules.dai.Hasher;
import businessrules.dai.VendorRepository;
import businessrules.outputboundaries.ObjectBoundary;
import businessrules.outputboundaries.RepositoryBoundary;
import businessrules.outputboundaries.ResponseObject;
import businessrules.outputboundaries.VendorBoundary;
import businessrules.vendor.usecases.ModifyVendorInteractor;
import entities.Menu;
import entities.OrderBook;
import entities.Shop;
import entities.Vendor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ModifyVendorInteractorTest {
    VendorRepository vendorRepository;
    RepositoryBoundary repositoryBoundary;
    VendorBoundary vendorBoundary;
    ObjectBoundary<Vendor> vendorObjectBoundary;
    Hasher hasher;
    ModifyVendorInteractor modifyVendorInteractor;
    Vendor vendor;

    @BeforeEach
    void setUp() {
        Menu menu = new Menu();
        OrderBook orderBook = new OrderBook();
        Shop shop = new Shop("00001", "JavaJShop", "Bay Street", true, menu, orderBook);
        vendor = new Vendor("12345", "Username", "Password", shop);
        vendorRepository = new RAMVendorRepository(vendor);
        repositoryBoundary = new RAMRepositoryBoundary();
        vendorBoundary = new RAMVendorBoundary();
        vendorObjectBoundary = new RAMVendorObjectBoundary();
        hasher = new SHA512Hasher();
        modifyVendorInteractor = new ModifyVendorInteractor(vendorRepository, repositoryBoundary, vendorBoundary,
                vendorObjectBoundary, hasher);
    }

    @Test
    void successfulModifyVendor() {
        modifyVendorInteractor.modifyVendor("12345", "username1",
                "password1", "password1");
        Vendor new_vendor = vendorRepository.read("12345");
        assertEquals("username1", new_vendor.getUserName());
        assertEquals(hasher.hash("password1"), new_vendor.getHashedPassword());
    }

    @Test
    void noVendorModifyVendor() {
        ResponseObject responseObject = modifyVendorInteractor.modifyVendor("20000", "Username2",
                "Password2", "Password2");
        assertEquals("Unable to find such a vendor.", responseObject.getMessage());
        assertEquals(1, responseObject.getStatus());
    }

    @Test
    void noMatchPassword() {
        ResponseObject responseObject = modifyVendorInteractor.modifyVendor("12345", "username1",
                "Password2", "Password3");
        assertEquals("Passwords must match.", responseObject.getMessage());
    }

    @Test
    void usedUsername() {
        ResponseObject responseObject = modifyVendorInteractor.modifyVendor("12345", "Username",
                "Password2", "Password2");
        assertEquals("Username is already taken.", responseObject.getMessage());
    }
}