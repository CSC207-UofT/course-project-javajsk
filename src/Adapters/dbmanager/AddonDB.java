package Adapters.dbmanager;

import Adapters.DatabaseGateway;
import businessrules.dai.AddonRepository;
import org.json.JSONObject;



public class AddonDB implements AddonRepository {
    DatabaseGateway databaseGateway;
    String TABLE = "addons";

    @Override
    public String createAddon(JSONObject data) {
        return databaseGateway.createEntry(TABLE, data);
    }

    @Override
    public boolean updateAddon(String id, JSONObject data) {
        return databaseGateway.updateEntry(TABLE,id, data);
    }

    @Override
    public boolean deleteAddon(String id) {
        return databaseGateway.deleteEntry(TABLE,id);
    }

    @Override
    public JSONObject readAddon(String id) {
        return databaseGateway.readEntry(TABLE, id);
    }


}
