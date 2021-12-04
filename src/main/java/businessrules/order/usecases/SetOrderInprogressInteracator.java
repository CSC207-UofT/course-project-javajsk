package businessrules.order.usecases;

import businessrules.dai.Repository;
import businessrules.dai.VendorRepository;
import businessrules.order.inputboundaries.SetOrderInprogress;
import businessrules.outputboundaries.ObjectBoundary;
import businessrules.outputboundaries.RepositoryBoundary;
import businessrules.outputboundaries.ResponseObject;
import businessrules.outputboundaries.VendorBoundary;
import entities.Order;
import entities.Vendor;

import java.util.Date;
import java.util.List;

public class SetOrderInprogressInteracator implements SetOrderInprogress {
    VendorRepository vendorRepository;
    Repository<Order> orderRepository;
    RepositoryBoundary repositoryBoundary;
    VendorBoundary vendorBoundary;
    ObjectBoundary<Order> orderObjectBoundary;

    @Override
    public ResponseObject setOrderInprogress(String vendorToken, String orderId) {
        Vendor vendor = (Vendor) vendorRepository.getUserFromToken(vendorToken);

        if(vendor == null){
            return repositoryBoundary.queryNotFound("No such vendor found.");
        }

        Order order = orderRepository.read(orderId);

        if(order == null){
            return repositoryBoundary.queryNotFound("No such order found.");
        }

        if(!order.getShopId().equals(vendor.getShop().getId())){
            return vendorBoundary.unauthorizedAccess("You do not own this order.");
        }

        order.setStatus(Order.Status.IN_PROGRESS);
        order.setTimeStatusModified(new Date());

        List<Order> userOrders = orderRepository.readMultiple("shopId", vendor.getShop().getId() );
        return orderObjectBoundary.showObjectList(userOrders);
    }
}
