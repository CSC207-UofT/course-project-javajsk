package businessrules.addon.usecases;

import businessrules.addon.inputboundaries.GetShopAddons;
import businessrules.dai.Repository;
import businessrules.outputboundaries.ObjectBoundary;
import businessrules.outputboundaries.RepositoryBoundary;
import businessrules.outputboundaries.ResponseObject;
import entities.Addon;

import java.util.List;

public class GetShopAddonsInteractor implements GetShopAddons {
    Repository<Addon> addonRepository;
    RepositoryBoundary repositoryBoundary;
    ObjectBoundary<Addon> addonObjectBoundary;
    @Override
    public ResponseObject getShopAddons(String shopId) {

        List<Addon> result = addonRepository.readMultiple("shopId", shopId);
        if(result == null){
            return repositoryBoundary.queryNotFound("Unable to find shop.");
        }
        return addonObjectBoundary.showObjectList(result);
    }
}
