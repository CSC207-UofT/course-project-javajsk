package businessrules.order.usecases;

import businessrules.dai.Repository;
import businessrules.dai.VendorRepository;
import businessrules.order.inputboundaries.CompleteOrder;
import businessrules.outputboundaries.*;
import entities.Order;
import entities.Vendor;

import java.util.Date;
import java.util.List;

/**
 * Use case for completing an order entry of a repository
 */
public class CompleteOrderInteractor implements CompleteOrder {
    VendorRepository vendorRepository;
    RepositoryBoundary repositoryBoundary;
    Repository<Order> orderRepository;
    VendorBoundary vendorBoundary;
    ObjectBoundary<Order> orderObjectBoundary;

    /**
     * Instantiates a use case for completing an order
     * @param vR the vendor repository
     * @param rB the repository boundary
     * @param oR the order repository
     * @param vB the vendor boundary
     * @param oOB the order object boundary
     */
    public CompleteOrderInteractor(VendorRepository vR, RepositoryBoundary rB, Repository<Order> oR,
                                   VendorBoundary vB, ObjectBoundary<Order> oOB) {
        this.vendorRepository = vR;
        this.repositoryBoundary = rB;
        this.orderRepository = oR;
        this.vendorBoundary = vB;
        this.orderObjectBoundary = oOB;
    }

    /**
     * Method for setting tha status of an order to completed
     * @param vendorToken the vendor token
     * @param orderId the order id
     * @return a response object
     */
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

        order.setTimeStatusModified(new Date());
        order.setStatus(Order.Status.COMPLETED);


        List<Order> userOrders = orderRepository.readMultiple("shopId", vendor.getShop().getId() );
        return orderObjectBoundary.showObjectList(userOrders);
    }
}
