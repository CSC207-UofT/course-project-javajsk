package businessrules.shop.inputboundaries;

public interface DeleteShopInputBoundary {
    boolean deleteShop(String vendorToken, String shopId);
}
