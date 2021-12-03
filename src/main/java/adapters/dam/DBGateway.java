package adapters.dam;

import org.json.JSONObject;

public interface DBGateway {
    JSONObject read(String table, String id);

    boolean update(String table, String id, JSONObject newDat);

    String create(String table, JSONObject data);

}
