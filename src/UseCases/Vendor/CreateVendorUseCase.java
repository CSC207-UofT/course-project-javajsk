package UseCases.Vendor;

import Entities.FoodTruck;
import Entities.Vendor;
import UseCases.DataAccessInterfaces.VendorRepository;

import java.util.List;

public class CreateVendorUseCase implements CreateVendorInputBoundary{
    VendorRepository vendorRepository;

    public CreateVendorUseCase(VendorRepository vendorRepository){
        this.vendorRepository = vendorRepository;
    }

    @Override
    public Vendor createVendor(String id, String password, String name, List<FoodTruck> foodTrucks) {
        Vendor new_vendor = new Vendor(id, password, name, foodTrucks);
        vendorRepository.save(new_vendor);
        return new_vendor;
    }
}
