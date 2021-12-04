package businessrules.menu.usecases;

import businessrules.dai.Repository;
import businessrules.menu.inputboundaries.GetAvailableFoods;
import businessrules.outputboundaries.ObjectBoundary;
import businessrules.outputboundaries.RepositoryBoundary;
import businessrules.outputboundaries.ResponseObject;
import EntitiesUnitTest.Food;
import EntitiesUnitTest.Shop;

import java.util.List;

public class GetAvailableFoodsInteractor implements GetAvailableFoods {
    Repository<Shop> shopRepository;
    RepositoryBoundary repositoryBoundary;
    ObjectBoundary<Food> addonObjectBoundary;

    @Override
    public ResponseObject getAvailableFoods(String shopId) {
        Shop shop = shopRepository.read(shopId);

        if(shop == null){
            return repositoryBoundary.queryNotFound("No such shop found.");
        }

        List<Food> availableFoods = shop.getMenu().getAvailableFoods();
        return addonObjectBoundary.showObjectList(availableFoods);
    }
}
