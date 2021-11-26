package UseCases.RegularVendor;

import Entities.Interfaces.IShop;

public interface AddShopInputBoundary {

    boolean addShop(IShop shop, String token);

}

