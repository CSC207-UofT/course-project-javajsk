package businessrules.addon.usecases;

import businessrules.addon.inputboundaries.GetShopAddons;
import businessrules.dai.Repository;
import businessrules.outputboundaries.ObjectBoundary;
import businessrules.outputboundaries.RepositoryBoundary;
import businessrules.outputboundaries.ResponseObject;
import entities.Addon;

import java.util.List;

/**
 * Get shop addons use case
 */
public class GetShopAddonsInteractor implements GetShopAddons {
    /**
     * The Addon repository.
     */
    Repository<Addon> addonRepository;
    /**
     * The Repository boundary.
     */
    RepositoryBoundary repositoryBoundary;
    /**
     * The Addon object boundary.
     */
    ObjectBoundary<Addon> addonObjectBoundary;

    /**
     * Instantiates a new Get shop addons interactor.
     *
     * @param addonRepository     the addon repository
     * @param repositoryBoundary  the repository boundary
     * @param addonObjectBoundary the addon object boundary
     */
    public GetShopAddonsInteractor(Repository<Addon> addonRepository, RepositoryBoundary repositoryBoundary,
                                   ObjectBoundary<Addon> addonObjectBoundary) {
        this.addonRepository = addonRepository;
        this.repositoryBoundary = repositoryBoundary;
        this.addonObjectBoundary = addonObjectBoundary;
    }

    /**
     * Method that returns a response object that contains the addons of the shop
     * with the given id
     * @param shopId id of shop
     * @return response object containing shop addons or error message
     */
    @Override
    public ResponseObject getShopAddons(String shopId) {

        List<Addon> result = addonRepository.readMultiple("shopId", shopId);
        if(result == null){
            return repositoryBoundary.queryNotFound("Unable to find shop.");
        }
        return addonObjectBoundary.showObjectList(result);
    }
}
