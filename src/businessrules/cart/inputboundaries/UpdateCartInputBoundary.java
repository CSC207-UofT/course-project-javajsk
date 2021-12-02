package businessrules.cart.inputboundaries;

import org.json.JSONObject;

public interface UpdateCartInputBoundary {
    JSONObject updateCart(String customerToken, JSONObject newCart);
}
