package UseCases.Vendor;

import Entities.Vendor;

public interface SetVendorInputBoundary {

    boolean setVendor(Vendor vendor, String token);

}

