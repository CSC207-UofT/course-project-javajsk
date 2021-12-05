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

/**
 * Use case for setting an order to in progress in a repository
 */
public class SetOrderInprogressInteracator implements SetOrderInprogress {
    VendorRepository vendorRepository;
    Repository<Order> orderRepository;
    RepositoryBoundary repositoryBoundary;
    VendorBoundary vendorBoundary;
    ObjectBoundary<Order> orderObjectBoundary;

    /**
     * Instantiates a use case for setting an order to in progress in a repository
     * @param vR the vendor repository
     * @param oR the order repository
     * @param rB the repository boundary
     * @param vB the vendor boundary
     * @param oOB the order object boundary
     */
    public SetOrderInprogressInteracator(VendorRepository vR, Repository<Order> oR, RepositoryBoundary rB, VendorBoundary vB, ObjectBoundary<Order> oOB) {
        this.vendorRepository = vR;
        this.orderRepository = oR;
        this.repositoryBoundary = rB;
        this.vendorBoundary = vB;
        this.orderObjectBoundary = oOB;
    }

    /**
     * Method for setting the order status as in progress
     * @param vendorToken the vendor token
     * @param orderId the order id
     * @return a response object
     */
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
