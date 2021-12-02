package Adapters;

import org.json.JSONObject;

public interface DatabaseGateway {
    String createEntry(String table, JSONObject data);

    boolean deleteEntry(String table, String id);

    boolean updateEntry(String table, String id, JSONObject newData);

    JSONObject readEntry(String table, String id);

    boolean isColUnique(String table, String col, Object value);
}
