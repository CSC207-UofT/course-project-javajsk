package businessrules.order.usecases;

import businessrules.dai.VendorRepository;
import businessrules.order.inputboundaries.GetShopOrders;
import businessrules.outputboundaries.ObjectBoundary;
import businessrules.outputboundaries.RepositoryBoundary;
import businessrules.outputboundaries.ResponseObject;
import entities.Order;
import entities.Vendor;

/**
 * Use case for getting a shop's orders from a repository
 */
public class GetShopOrdersInteractor implements GetShopOrders {
    VendorRepository vendorRepository;
    RepositoryBoundary repositoryBoundary;
    ObjectBoundary<Order> orderObjectBoundary;

    /**
     * Instantiates a use case for getting a shop's orders
     * @param vR the vendor repository
     * @param rB the repository boundary
     * @param oOB the order object boundary
     */
    public GetShopOrdersInteractor(VendorRepository vR, RepositoryBoundary rB, ObjectBoundary<Order> oOB) {
        this.vendorRepository = vR;
        this.repositoryBoundary = rB;
        this.orderObjectBoundary = oOB;
    }

    /**
     * Method for getting a shops orders
     * @param vendorToken the vendor token
     * @return a response object
     */
    @Override
    public ResponseObject getShopOrders(String vendorToken) {
        Vendor vendor = (Vendor) vendorRepository.getUserFromToken(vendorToken);
        if(vendor == null){
            return repositoryBoundary.queryNotFound("No such vendor found");
        }

        return orderObjectBoundary.showObjectList(vendor.getShop().getOrderBook().getIncompleteOrders());

    }
}
