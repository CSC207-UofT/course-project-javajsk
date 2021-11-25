package UseCases.RegularVendor;

import Entities.FoodTruck;
import Entities.Interfaces.IShop;
import Entities.Regular.RegularVendor;
import Entities.Regular.RegularVendor;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public interface CreateVendorInputBoundary {

    public RegularVendor createVendor(String id, ArrayList<IShop> shops);
}
