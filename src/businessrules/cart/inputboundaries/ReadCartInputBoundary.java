package businessrules.cart.inputboundaries;

import org.json.JSONObject;

/**
 * Input boundary for reading cart use case - indicates the methods and parameters that ClearCartUseCase must implement
 */
public interface ReadCartInputBoundary {
    /**
     * A method for reading the information contained in a given customer's cart
     * @param customerToken token of current customer
     * @return JSON object containing information on the customer's cart of error messages
     */
    JSONObject readCart(String customerToken);
}
