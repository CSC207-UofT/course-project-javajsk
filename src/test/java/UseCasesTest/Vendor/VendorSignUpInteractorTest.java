package UseCasesTest.Vendor;

import UseCasesTest.TestBoundaries.RAMRepositoryBoundary;
import UseCasesTest.TestBoundaries.RAMVendorBoundary;
import UseCasesTest.TestBoundaries.RAMVendorObjectBoundary;
import UseCasesTest.daitesters.RAMShopRepository;
import UseCasesTest.daitesters.RAMVendorRepository;
import adapters.SHA512Hasher;
import businessrules.dai.Hasher;
import businessrules.dai.Repository;
import businessrules.dai.VendorRepository;
import businessrules.outputboundaries.ObjectBoundary;
import businessrules.outputboundaries.RepositoryBoundary;
import businessrules.outputboundaries.ResponseObject;
import businessrules.outputboundaries.VendorBoundary;
import businessrules.vendor.usecases.VendorSignUpInteractor;
import entities.Menu;
import entities.OrderBook;
import entities.Shop;
import entities.Vendor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class VendorSignUpInteractorTest {
    VendorRepository vendorRepository;
    Hasher hasher;
    RepositoryBoundary repositoryBoundary;
    ObjectBoundary<Vendor> vendorObjectBoundary;
    Repository<Shop> shopRepository;
    VendorBoundary vendorBoundary;
    VendorSignUpInteractor vendorSignUpInteractor;
    Vendor vendor;

    @BeforeEach
    void setUp() {
        Menu menu = new Menu();
        OrderBook orderBook = new OrderBook();
        Shop shop = new Shop("86784", "JavaJShop", "Bay Street", true, menu, orderBook);
        vendor = new Vendor("12345", "Username", "Password", shop);
        vendorRepository = new RAMVendorRepository(vendor);
        hasher = new SHA512Hasher();
        repositoryBoundary = new RAMRepositoryBoundary();
        vendorObjectBoundary = new RAMVendorObjectBoundary();
        shopRepository = new RAMShopRepository(shop);
        vendorBoundary = new RAMVendorBoundary();
        vendorSignUpInteractor = new VendorSignUpInteractor(vendorRepository, hasher, repositoryBoundary,
                vendorObjectBoundary, shopRepository, vendorBoundary);
    }

    @Test
    void passwordNoMatch() {
        ResponseObject responseObject = vendorSignUpInteractor.signUp("username1", "password1",
                "password2", "UofTruck", "Bahen");
        assertEquals("Passwords do not match.", responseObject.getMessage());
    }

    @Test
    void usernameTaken() {
        ResponseObject responseObject = vendorSignUpInteractor.signUp("Username", "password1",
                "password1", "UofTruck", "Bahen");
        assertEquals("Username is already taken!", responseObject.getMessage());
    }


    @Test
    void signUp() {
        ResponseObject responseObject = vendorSignUpInteractor.signUp("username1", "password1",
                "password1", "UofTruck", "Bahen");
        Vendor new_vendor = (Vendor) responseObject.getContents();
        assertNotNull(vendorRepository.findOneByFieldName("", "username1"));
        assertNotNull(shopRepository.findOneByFieldName("", "UofTruck"));
        assertEquals("Bahen", new_vendor.getShop().getLocation());
    }
}