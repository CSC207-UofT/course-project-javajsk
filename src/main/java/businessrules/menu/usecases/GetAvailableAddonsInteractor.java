package businessrules.menu.usecases;

import businessrules.dai.Repository;
import businessrules.menu.inputboundaries.GetAvailableAddons;
import businessrules.outputboundaries.ObjectBoundary;
import businessrules.outputboundaries.RepositoryBoundary;
import businessrules.outputboundaries.ResponseObject;
import entities.Addon;
import entities.Shop;

import java.util.List;

/**
 * Use case for getting available addons of a shop from a repository
 */
public class GetAvailableAddonsInteractor implements GetAvailableAddons {
    /**
     * The Shop repository.
     */
    Repository<Shop> shopRepository;
    /**
     * The Repository boundary.
     */
    RepositoryBoundary repositoryBoundary;
    /**
     * The Addon object boundary.
     */
    ObjectBoundary<Addon> addonObjectBoundary;

    /**
     * Instantiates a use case for getting a shop's available addons
     *
     * @param sR  the shop repository
     * @param rB  the repository boundary
     * @param aOB the addon object boundary
     */
    public GetAvailableAddonsInteractor(Repository<Shop> sR, RepositoryBoundary rB, ObjectBoundary<Addon> aOB) {
        this.shopRepository = sR;
        this.repositoryBoundary = rB;
        this.addonObjectBoundary = aOB;
    }

    /**
     * Method for getting available addons
     *
     * @param shopId the shop id
     * @return a response object
     */
    @Override
    public ResponseObject getAvailableAddons(String shopId) {
        Shop shop = shopRepository.read(shopId);

        if (shop == null) {
            return repositoryBoundary.queryNotFound("No such shop found.");
        }

        List<Addon> availableAddons = shop.getMenu().getAvailableAddons();
        return addonObjectBoundary.showObjectList(availableAddons);
    }
}
