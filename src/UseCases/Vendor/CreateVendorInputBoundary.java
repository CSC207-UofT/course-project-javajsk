package UseCases.Vendor;

import Entities.FoodTruck;
import Entities.Vendor;

import java.util.List;

public interface CreateVendorInputBoundary {

    public Vendor createVendor(String id, String password, String name, List<FoodTruck> foodTrucks);
}
