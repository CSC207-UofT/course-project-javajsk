package businessrules.shop.inputboundaries;

import businessrules.outputboundaries.ResponseObject;
import EntitiesUnitTest.Shop;

public interface ModifyShop {
    ResponseObject modifyShop(String vendorToken, Shop shop);
}
