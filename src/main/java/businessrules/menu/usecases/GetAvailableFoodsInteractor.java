package businessrules.menu.usecases;

import businessrules.dai.Repository;
import businessrules.menu.inputboundaries.GetAvailableFoods;
import businessrules.outputboundaries.ObjectBoundary;
import businessrules.outputboundaries.RepositoryBoundary;
import businessrules.outputboundaries.ResponseObject;
import entities.Food;
import entities.Shop;

import java.util.List;

/**
 * Use case for getting the available foods from a shop entry in a repository
 */
public class GetAvailableFoodsInteractor implements GetAvailableFoods {
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
    ObjectBoundary<Food> addonObjectBoundary;

    /**
     * Instantiates a use case for getting available food entities from a shop entry
     *
     * @param sR  the shop repository
     * @param rB  the repository boundary
     * @param aOB the addon object boundary
     */
    public GetAvailableFoodsInteractor(Repository<Shop> sR, RepositoryBoundary rB, ObjectBoundary<Food> aOB) {
        this.shopRepository = sR;
        this.repositoryBoundary = rB;
        this.addonObjectBoundary = aOB;
    }

    /**
     * Method for getting available foods from a shop
     *
     * @param shopId the shop id
     * @return a response object
     */
    @Override
    public ResponseObject getAvailableFoods(String shopId) {
        Shop shop = shopRepository.read(shopId);

        if (shop == null) {
            return repositoryBoundary.queryNotFound("No such shop found.");
        }

        List<Food> availableFoods = shop.getMenu().getAvailableFoods();
        return addonObjectBoundary.showObjectList(availableFoods);
    }
}
