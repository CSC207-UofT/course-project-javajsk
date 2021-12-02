package Adapters.dbmanager;

import Adapters.DatabaseGateway;
import businessrules.dai.ShopRepository;
import org.json.JSONObject;

public class ShopDB implements ShopRepository{
    DatabaseGateway databaseGateway;
    String TABLE = "shop";
    @Override
    public String createShop(JSONObject data) {
        return databaseGateway.createEntry(TABLE, data);
    }

    @Override
    public boolean updateShop(String shopId, JSONObject shop) {
        return databaseGateway.updateEntry(TABLE, shopId, shop);
    }

    @Override
    public JSONObject readShop(String shopId) {
        return databaseGateway.readEntry(TABLE, shopId);
    }

    @Override
    public boolean deleteShop(String shopId) {
        return databaseGateway.deleteEntry(TABLE, shopId);
    }
}
