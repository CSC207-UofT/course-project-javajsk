package adapters;

import org.json.JSONObject;

import java.util.List;

public interface DBGateway {
    JSONObject read(String table, String id);

    boolean update(String table, String id, JSONObject newDat);

    String create(String table, JSONObject data);

    List<JSONObject> readMultiple(String table,String parameter, String needle);

    JSONObject readOne(String table, String parameter, String needle);

    JSONObject getCollection(String collection);
}
