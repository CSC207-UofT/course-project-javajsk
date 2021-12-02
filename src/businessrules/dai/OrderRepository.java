package businessrules.dai;

import org.json.JSONObject;

public interface OrderRepository {
    /**
     * Add an order to the repository
     * @param data the order information
     * @return the id of the new order
     */
    String createOrder(JSONObject data);

    /**
     * Gets the specified order in the repository
     * @param id id of the order
     * @return the data of the order
     */
    JSONObject readOrder(String id);

    /**
     * updates an order in the repository
     * @param id the id of the order
     * @param data the updated order
     * @return whether the update was successful or not
     */
    boolean updateOrder(String id, JSONObject data);

    /**
     * Deletes an order from the repository
     * @param id the id of the order
     * @return whether the order was deleted or not
     */
    boolean deleteOrder(String id);
}
