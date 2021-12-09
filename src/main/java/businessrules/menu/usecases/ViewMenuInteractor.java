package businessrules.menu.usecases;

import businessrules.dai.Repository;
import businessrules.menu.inputboundaries.ViewMenu;
import businessrules.outputboundaries.ObjectBoundary;
import businessrules.outputboundaries.RepositoryBoundary;
import businessrules.outputboundaries.ResponseObject;
import entities.Menu;
import entities.Shop;

/**
 * Use case for viewing the menu of a menu of a repository
 */
public class ViewMenuInteractor implements ViewMenu {
    /**
     * The Shop repository.
     */
    Repository<Shop> shopRepository;
    /**
     * The Repository boundary.
     */
    RepositoryBoundary repositoryBoundary;
    /**
     * The Menu object boundary.
     */
    ObjectBoundary<Menu> menuObjectBoundary;

    /**
     * Instantiates a use case for viewing the menu of a menu
     *
     * @param sR  the shop repository
     * @param rB  the repository boundary
     * @param mOB the menu object boundary
     */
    public ViewMenuInteractor(Repository<Shop> sR, RepositoryBoundary rB, ObjectBoundary<Menu> mOB) {
        this.shopRepository = sR;
        this.repositoryBoundary = rB;
        this.menuObjectBoundary = mOB;
    }

    /**
     * Method for getting the menu of a shop
     * @param shopId the shop id
     * @return a response object
     */
    @Override
    public ResponseObject viewMenu(String shopId) {
        Shop shop = shopRepository.read(shopId);
        if(shop == null){
            return repositoryBoundary.queryNotFound("No such shop found.");
        }
        return menuObjectBoundary.showObject(shop.getMenu());
    }
}
