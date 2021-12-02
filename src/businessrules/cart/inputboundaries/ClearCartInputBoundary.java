package businessrules.cart.inputboundaries;

import org.json.JSONObject;

public interface ClearCartInputBoundary {
    JSONObject clearCart(String customerToken);
}
