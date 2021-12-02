package businessrules.order.inputboundaries;

import org.json.JSONObject;

public interface ReadOrderInputBoundary {
    JSONObject readOrder(String id);
}
