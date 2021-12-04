package businessrules.singleton.usecases;

import businessrules.dai.Repository;
import businessrules.outputboundaries.ObjectBoundary;
import businessrules.outputboundaries.ResponseObject;
import businessrules.singleton.inputboundaries.GetShopSingletons;
import entities.Singleton;

import java.util.List;

public class GetShopSingletonsInteractor implements GetShopSingletons {
    Repository<Singleton> singletonRepository;
    ObjectBoundary<Singleton> singletonObjectBoundary;


    @Override
    public ResponseObject getShopSingletons(String shopId) {
        List<Singleton> singletonList = singletonRepository.readMultiple("shopId", shopId);
        return singletonObjectBoundary.showObjectList(singletonList);
    }
}
