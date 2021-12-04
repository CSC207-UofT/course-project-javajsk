package businessrules.order.usecases;

import businessrules.dai.Repository;
import businessrules.dai.VendorRepository;
import businessrules.order.inputboundaries.CompleteOrder;
import businessrules.outputboundaries.*;
import entities.Order;
import entities.Vendor;

import java.util.Date;
import java.util.List;

public class CompleteOrderInteractor implements CompleteOrder {
    VendorRepository vendorRepository;
    RepositoryBoundary repositoryBoundary;
    Repository<Order> orderRepository;
    VendorBoundary vendorBoundary;
    ObjectBoundary<Order> orderObjectBoundary;

    @Override
    public ResponseObject completeOrder(String vendorToken, String orderId) {
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

        order.setStatus(Order.Status.CANCELLED);
        order.setTimeStatusModified(new Date());

        List<Order> userOrders = orderRepository.readMultiple("shopId", vendor.getShop().getId() );
        return orderObjectBoundary.showObjectList(userOrders);
    }
}
