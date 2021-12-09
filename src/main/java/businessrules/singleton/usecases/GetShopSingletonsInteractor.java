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
    /**
     * The Singleton repository.
     */
    Repository<Singleton> singletonRepository;
    /**
     * The Singleton object boundary.
     */
    ObjectBoundary<Singleton> singletonObjectBoundary;

    /**
     * Instantiates a new Get shop singletons interactor.
     *
     * @param singletonRepository     the singleton repository
     * @param singletonObjectBoundary the singleton object boundary
     */
    public GetShopSingletonsInteractor(Repository<Singleton> singletonRepository,
                                       ObjectBoundary<Singleton> singletonObjectBoundary) {
        this.singletonRepository = singletonRepository;
        this.singletonObjectBoundary = singletonObjectBoundary;
    }

    /**
     * Returns all Singletons in the specified shop.
     *
     * @param shopId id of the shop
     * @return response object
     */
    @Override
    public ResponseObject getShopSingletons(String shopId) {
        List<Singleton> singletonList = singletonRepository.readMultiple("shopId", shopId);
        return singletonObjectBoundary.showObjectList(singletonList);
    }
}
