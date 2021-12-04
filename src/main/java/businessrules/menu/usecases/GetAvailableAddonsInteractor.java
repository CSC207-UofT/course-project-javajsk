package businessrules.menu.usecases;

import businessrules.dai.Repository;
import businessrules.menu.inputboundaries.GetAvailableAddons;
import businessrules.outputboundaries.ObjectBoundary;
import businessrules.outputboundaries.RepositoryBoundary;
import businessrules.outputboundaries.ResponseObject;
import EntitiesUnitTest.Addon;
import EntitiesUnitTest.Shop;

import java.util.List;

public class GetAvailableAddonsInteractor implements GetAvailableAddons {
    Repository<Shop> shopRepository;
    RepositoryBoundary repositoryBoundary;
    ObjectBoundary<Addon> addonObjectBoundary;
    @Override
    public ResponseObject getAvailableAddons(String shopId) {
        Shop shop = shopRepository.read(shopId);

        if(shop == null){
            return repositoryBoundary.queryNotFound("No such shop found.");
        }

        List<Addon> availableAddons = shop.getMenu().getAvailableAddons();
        return addonObjectBoundary.showObjectList(availableAddons);
    }
}
