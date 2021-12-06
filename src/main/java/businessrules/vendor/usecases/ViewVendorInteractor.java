package businessrules.vendor.usecases;

import businessrules.vendor.inputboundaries.ViewVendor;
import businessrules.dai.Repository;
import businessrules.outputboundaries.ObjectBoundary;
import businessrules.outputboundaries.ResponseObject;
import entities.Vendor;
import entities.Shop;

public class ViewVendorInteractor implements ViewVendor {
    Repository<Vendor> vendorRepository;
    ObjectBoundary<Vendor> vendorObjectBoundary;

    public ViewVendorInteractor(Repository<Vendor> vendorRepository, ObjectBoundary<Vendor> vendorObjectBoundary) {
        this.vendorRepository = vendorRepository;
        this.vendorObjectBoundary = vendorObjectBoundary;
    }

    public ResponseObject viewVendor(String vendorId) {
        Vendor vendor = vendorRepository.read(vendorId);
        return vendorObjectBoundary.showObject(vendor);

    }
}
