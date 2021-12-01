package businessrules.addon;

import org.json.JSONObject;

public interface Factory <T> {
    T get(JSONObject data);
}
