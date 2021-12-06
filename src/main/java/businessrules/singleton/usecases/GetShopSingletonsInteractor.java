package businessrules.singleton.usecases;

import businessrules.dai.Repository;
import businessrules.outputboundaries.ObjectBoundary;
import businessrules.outputboundaries.ResponseObject;
import businessrules.singleton.inputboundaries.GetShopSingletons;
import entities.Singleton;

import java.util.List;

/**
 * Use case for getting all Singletons in a shop
 */
public class GetShopSingletonsInteractor implements GetShopSingletons {
    Repository<Singleton> singletonRepository;
    ObjectBoundary<Singleton> singletonObjectBoundary;

    public GetShopSingletonsInteractor(Repository<Singleton> singletonRepository,
                                       ObjectBoundary<Singleton> singletonObjectBoundary) {
        this.singletonRepository = singletonRepository;
        this.singletonObjectBoundary = singletonObjectBoundary;
    }

    /**
     * Method that gets all the Singletons in the specified shop as a JSONObject
     *
     * @param shopId id of the shop
     * @return       JSONObject representing a list of all Singletons in the shop
     */
    @Override
    public ResponseObject getShopSingletons(String shopId) {
        List<Singleton> singletonList = singletonRepository.readMultiple("shopId", shopId);
        return singletonObjectBoundary.showObjectList(singletonList);
    }
}
