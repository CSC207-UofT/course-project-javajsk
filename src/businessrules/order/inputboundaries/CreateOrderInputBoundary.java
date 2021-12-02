package businessrules.order.inputboundaries;

import org.json.JSONObject;

public interface CreateOrderInputBoundary {
    JSONObject createOrder(String customerToken, String shopId, JSONObject data);
}
