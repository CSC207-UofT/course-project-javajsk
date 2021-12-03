package businessrules.order.usecases;

import businessrules.dai.VendorRepository;
import businessrules.order.inputboundaries.GetNextOrder;
import businessrules.outputboundaries.ObjectBoundary;
import businessrules.outputboundaries.RepositoryBoundary;
import businessrules.outputboundaries.ResponseObject;
import entities.Order;
import entities.Vendor;

public class GetNextOrderInteractor implements GetNextOrder {
    VendorRepository vendorRepository;
    RepositoryBoundary repositoryBoundary;
    ObjectBoundary<Order> orderObjectBoundary;

    @Override
    public ResponseObject getNextOrder(String vendorToken) {
        Vendor vendor = (Vendor) vendorRepository.getUserFromToken(vendorToken);
        if(vendor == null){
            return repositoryBoundary.queryNotFound("No such vendor found");
        }

        return orderObjectBoundary.showObject(vendor.getShop().getOrderBook().getNextOrder());

    }
}
