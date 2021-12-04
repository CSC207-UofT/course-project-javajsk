package businessrules.menu.usecases;

import businessrules.dai.Repository;
import businessrules.menu.inputboundaries.ViewMenu;
import businessrules.outputboundaries.ObjectBoundary;
import businessrules.outputboundaries.RepositoryBoundary;
import businessrules.outputboundaries.ResponseObject;
import EntitiesUnitTest.Menu;
import EntitiesUnitTest.Shop;

public class ViewMenuInteractor implements ViewMenu {
    Repository<Shop> shopRepository;
    RepositoryBoundary repositoryBoundary;
    ObjectBoundary<Menu> menuObjectBoundary;
    @Override
    public ResponseObject viewMenu(String shopId) {
        Shop shop = shopRepository.read(shopId);
        if(shop == null){
            return repositoryBoundary.queryNotFound("No such shop found.");
        }
        return menuObjectBoundary.showObject(shop.getMenu());
    }
}
