package businessrules.singleton.inputboundaries;

import org.json.JSONObject;

public interface ReadSingletonInputBoundary {
    JSONObject readSingleton(String id);
}
