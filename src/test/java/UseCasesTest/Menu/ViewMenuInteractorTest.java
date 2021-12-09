package UseCasesTest.Menu;

import UseCasesTest.TestBoundaries.RAMMenuObjectBoundary;
import UseCasesTest.TestBoundaries.RAMRepositoryBoundary;
import UseCasesTest.daitesters.RAMShopRepository;
import businessrules.menu.usecases.ViewMenuInteractor;
import entities.Vendor;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ViewMenuInteractorTest {
    RAMShopRepository shopRepository;
    RAMRepositoryBoundary repositoryBoundary;
    RAMMenuObjectBoundary menuObjectBoundary;
    ViewMenuInteractor viewMenuInteractor;

    @Test
    void viewMenu() {
        Vendor vendor = new AddAddonToMenuInteractorTest().SetUp();
        shopRepository = new RAMShopRepository(vendor.getShop());
        repositoryBoundary = new RAMRepositoryBoundary();
        menuObjectBoundary = new RAMMenuObjectBoundary();
        viewMenuInteractor = new ViewMenuInteractor(shopRepository, repositoryBoundary, menuObjectBoundary);
        assertSame(vendor.getShop().getMenu(), viewMenuInteractor.viewMenu(vendor.getShop().getId()).getContents());
    }
}