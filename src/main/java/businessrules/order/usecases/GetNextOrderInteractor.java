package businessrules.order.usecases;

import businessrules.dai.VendorRepository;
import businessrules.order.inputboundaries.GetNextOrder;
import businessrules.outputboundaries.ObjectBoundary;
import businessrules.outputboundaries.RepositoryBoundary;
import businessrules.outputboundaries.ResponseObject;
import entities.Order;
import entities.Vendor;

/**
 * Use case for getting the next order entry of a repository
 */
public class GetNextOrderInteractor implements GetNextOrder {
    VendorRepository vendorRepository;
    RepositoryBoundary repositoryBoundary;
    ObjectBoundary<Order> orderObjectBoundary;

    /**
     * Instantiates a use case for getting the next order entry of a repository
     * @param vR the vendor repository
     * @param rB the repository boundary
     * @param oOB the order object boundary
     */
    public GetNextOrderInteractor(VendorRepository vR, RepositoryBoundary rB, ObjectBoundary<Order> oOB) {
        this.vendorRepository = vR;
        this.repositoryBoundary = rB;
        this.orderObjectBoundary = oOB;
    }

    /**
     * Method for getting the next incomplete order
     * @param vendorToken the vendor token
     * @return a response object
     */
    @Override
    public ResponseObject getNextOrder(String vendorToken) {
        Vendor vendor = (Vendor) vendorRepository.getUserFromToken(vendorToken);
        if(vendor == null){
            return repositoryBoundary.queryNotFound("No such vendor found");
        }

        return orderObjectBoundary.showObject(vendor.getShop().getOrderBook().getNextOrder());

    }
}
