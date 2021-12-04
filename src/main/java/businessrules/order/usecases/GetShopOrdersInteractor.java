package businessrules.order.usecases;

import businessrules.dai.VendorRepository;
import businessrules.order.inputboundaries.GetShopOrders;
import businessrules.outputboundaries.ObjectBoundary;
import businessrules.outputboundaries.RepositoryBoundary;
import businessrules.outputboundaries.ResponseObject;
import entities.Order;
import entities.Vendor;

public class GetShopOrdersInteractor implements GetShopOrders {
    VendorRepository vendorRepository;
    RepositoryBoundary repositoryBoundary;
    ObjectBoundary<Order> orderObjectBoundary;

    @Override
    public ResponseObject getShopOrders(String vendorToken) {
        Vendor vendor = (Vendor) vendorRepository.getUserFromToken(vendorToken);
        if(vendor == null){
            return repositoryBoundary.queryNotFound("No such vendor found");
        }

        return orderObjectBoundary.showObjectList(vendor.getShop().getOrderBook().getIncompleteOrders());

    }
}
