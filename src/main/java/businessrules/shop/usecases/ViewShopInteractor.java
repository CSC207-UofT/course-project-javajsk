package businessrules.shop.usecases;

import businessrules.dai.Repository;
import businessrules.outputboundaries.ObjectBoundary;
import businessrules.outputboundaries.ResponseObject;
import businessrules.shop.inputboundaries.ViewShop;

import entities.Shop;

public class ViewShopInteractor implements ViewShop {

    Repository<Shop> shopRepository;
    ObjectBoundary<Shop> shopObjectBoundary;

    public ViewShopInteractor(Repository<Shop> shopRepository, ObjectBoundary<Shop> shopObjectBoundary) {
        this.shopRepository = shopRepository;
        this.shopObjectBoundary = shopObjectBoundary;
    }

    @Override
    public ResponseObject viewShop(String shopId) {
        Shop shop = shopRepository.read(shopId);
        return shopObjectBoundary.showObject(shop);

    }
}
