package UseCases.RegularVendor;

import Entities.Interfaces.IShop;
import Entities.Regular.RegularVendor;

import java.util.ArrayList;

public interface CreateVendorInputBoundary {

    RegularVendor createVendor(String id, ArrayList<IShop> shops);
}
