package UseCases.Vendor;

import Entities.FoodTruck;
import Entities.Vendor;
import UseCases.DataAccessInterfaces.VendorRepository;

import java.util.List;

public class CreateVendorUseCase implements CreateVendorInputBoundary{
    VendorRepository vendorRepository;
    

    @Override
    public Vendor createVendor(String id, String password, String name, List<FoodTruck> foodTrucks) {
        return null;
    }
}
