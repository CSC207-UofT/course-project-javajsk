package businessrules.outputboundary;

import org.json.JSONObject;

public interface CartModel {
    JSONObject displayCart(JSONObject cart);

    JSONObject displayError(String errorMessage);
}
