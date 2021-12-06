package businessrules.addon.usecases;

import businessrules.addon.inputboundaries.GetShopAddons;
import businessrules.dai.Repository;
import businessrules.outputboundaries.ObjectBoundary;
import businessrules.outputboundaries.RepositoryBoundary;
import businessrules.outputboundaries.ResponseObject;
import entities.Addon;

import java.util.List;
import java.util.function.ToDoubleBiFunction;

public class GetShopAddonsInteractor implements GetShopAddons {
    Repository<Addon> addonRepository;
    RepositoryBoundary repositoryBoundary;
    ObjectBoundary<Addon> addonObjectBoundary;

    public GetShopAddonsInteractor(Repository<Addon> addonRepository, RepositoryBoundary repositoryBoundary,
                                   ObjectBoundary<Addon> addonObjectBoundary) {
        this.addonRepository = addonRepository;
        this.repositoryBoundary = repositoryBoundary;
        this.addonObjectBoundary = addonObjectBoundary;
    }

    @Override
    //TODO implement this using readMultiple method; currently null in all local repositories
    public ResponseObject getShopAddons(String shopId) {

        List<Addon> result = addonRepository.readMultiple("shopId", shopId);
        if(result == null){
            return repositoryBoundary.queryNotFound("Unable to find shop.");
        }
        return addonObjectBoundary.showObjectList(result);
    }
}
