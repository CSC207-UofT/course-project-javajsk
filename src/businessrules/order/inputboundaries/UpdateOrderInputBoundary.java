package businessrules.order.inputboundaries;

import org.json.JSONObject;

public interface UpdateOrderInputBoundary {
    JSONObject updateOrder(String orderId, JSONObject object);
}
