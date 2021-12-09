package businessrules.vendor.usecases;

import businessrules.vendor.inputboundaries.ViewVendor;
import businessrules.dai.Repository;
import businessrules.outputboundaries.ObjectBoundary;
import businessrules.outputboundaries.ResponseObject;
import entities.Vendor;

/**
 * View Vendor use case.
 */
public class ViewVendorInteractor implements ViewVendor {
    /**
     * The Vendor repository.
     */
    Repository<Vendor> vendorRepository;
    /**
     * The Vendor object boundary.
     */
    ObjectBoundary<Vendor> vendorObjectBoundary;

    /**
     * Instantiates a new View vendor interactor.
     *
     * @param vendorRepository     the vendor repository
     * @param vendorObjectBoundary the vendor object boundary
     */
    public ViewVendorInteractor(Repository<Vendor> vendorRepository, ObjectBoundary<Vendor> vendorObjectBoundary) {
        this.vendorRepository = vendorRepository;
        this.vendorObjectBoundary = vendorObjectBoundary;
    }

    /**
     * Method to display vendor with given id
     *
     * (or en error message if no such vendor exists)
     *
     * @param vendorId id of vendor
     * @return response object containing vendor or error message
     */
    @Override
    public ResponseObject viewVendor(String vendorId) {
        Vendor vendor = vendorRepository.read(vendorId);
        return vendorObjectBoundary.showObject(vendor);
    }
}
