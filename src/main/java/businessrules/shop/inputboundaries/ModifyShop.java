package businessrules.shop.inputboundaries;

import businessrules.outputboundaries.ResponseObject;
import entities.Shop;

public interface ModifyShop {
    ResponseObject modifyShop(String vendorToken, Shop shop);
}
