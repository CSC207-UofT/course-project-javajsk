package businessrules.cart.inputboundaries;

import org.json.JSONObject;

/**
 * Input boundary for ClearCartUseCase - indicates the methods and parameters that ClearCartUseCase must implement
 */
public interface ClearCartInputBoundary {
    /**
     * A method for emptying the data in cart
     * @param customerToken token of current customer
     * @return JSON object containing error information or cart with empty components
     */
    JSONObject clearCart(String customerToken);
}
