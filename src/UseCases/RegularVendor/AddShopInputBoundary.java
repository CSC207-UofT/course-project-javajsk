package UseCases.RegularVendor;

import Entities.Interfaces.IShop;
import Entities.Regular.RegularVendor;

public interface AddShopInputBoundary {

    boolean addShop(IShop shop, String token);

}

