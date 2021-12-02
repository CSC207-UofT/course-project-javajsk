package businessrules.menu.inputboundaries;

import org.json.JSONObject;

public interface ReadMenuInputBoundary {
    JSONObject readMenu(String shopId);
}
