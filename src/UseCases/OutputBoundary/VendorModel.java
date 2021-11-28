package UseCases.OutputBoundary;

import Entities.Interfaces.IVendor;

public interface VendorModel {

    void updateVendor(IVendor vendor);

    void displayVendor(IVendor vendor);
}
