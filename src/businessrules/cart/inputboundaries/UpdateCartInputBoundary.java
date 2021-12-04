package businessrules.cart.inputboundaries;

import org.json.JSONObject;

/**
 * Input boundary for update cart use case - indicates the methods and parameters that ClearCartUseCase must implement
 */
public interface UpdateCartInputBoundary {
    /**
     * A method that updates the given customer's cart with the new cart information
     * @param customerToken token of current customer
     * @param newCart information for new cart
     * @return JSON object containing updated cart information or error message information
     */
    JSONObject updateCart(String customerToken, JSONObject newCart);
}
