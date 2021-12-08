package businessrules.shop.usecases;

import businessrules.dai.Repository;
import businessrules.outputboundaries.ObjectBoundary;
import businessrules.outputboundaries.ResponseObject;
import businessrules.shop.inputboundaries.ViewShop;

import entities.Shop;

/**
 * View Shop Use Case.
 */
public class ViewShopInteractor implements ViewShop {

    /**
     * The Shop repository.
     */
    Repository<Shop> shopRepository;
    /**
     * The Shop object boundary.
     */
    ObjectBoundary<Shop> shopObjectBoundary;

    /**
     * Instantiates a new View shop interactor.
     *
     * @param shopRepository     the shop repository
     * @param shopObjectBoundary the shop object boundary
     */
    public ViewShopInteractor(Repository<Shop> shopRepository, ObjectBoundary<Shop> shopObjectBoundary) {
        this.shopRepository = shopRepository;
        this.shopObjectBoundary = shopObjectBoundary;
    }

    /**
     * Method returns a response object containing shop with shopId or error message
     * @param shopId id of shop
     * @return response object
     */
    @Override
    public ResponseObject viewShop(String shopId) {
        Shop shop = shopRepository.read(shopId);
        return shopObjectBoundary.showObject(shop);

    }
}
