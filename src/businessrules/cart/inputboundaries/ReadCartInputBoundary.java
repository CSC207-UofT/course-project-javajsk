package businessrules.cart.inputboundaries;

import org.json.JSONObject;

public interface ReadCartInputBoundary {
    JSONObject readCart(String customerToken);
}
