package adapters.dam.entityrepoitories;

import adapters.dam.DBGateway;
import businessrules.dai.Repository;
import entities.Cart;
import entities.Order;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * An implementation of a repository with type order
 */
public class OrderDB implements Repository<Order> {
    DBGateway databaseConnector;
    final String tableName = "Order";

    /**
     * Instantiates a order database
     * @param databaseConnector the database connector
     */
    public OrderDB(DBGateway databaseConnector) {
        this.databaseConnector = databaseConnector;
    }

    /**
     * Method for reading an order entry from the database
     * @param id the id of the order entry
     * @return the corresponding order entity
     */
    @Override
    public Order read(String id) {
        return loadOrderFromJSON(databaseConnector.read(tableName, id));
    }

    /**
     * Method for updating an order entry in the database
     * @param id the id of the order entry
     * @param item the updated order information
     * @return whether the update was successful or not
     */
    @Override
    public boolean update(String id, Order item) {
        return databaseConnector.update(tableName, id, loadJSONFromOrder(item));

    }

    /**
     * Method for creating a new order entry in the database
     * @param item the new order information
     * @return the id of the new order entry
     */
    @Override
    public String create(Order item) {
        return databaseConnector.create(tableName, loadJSONFromOrder(item));
    }

    /**
     * Method for retrieving multiple orders from the database
     * @param parameter the parameter to search by
     * @param needle the value of the parameter to find
     * @return a list of order entities that match the requirements
     */
    @Override
    public List<Order> readMultiple(String parameter, String needle) {
        List<Order> orderList = new ArrayList<>();
        List<JSONObject> rawOrders = databaseConnector.readMultiple(tableName, parameter, needle);
        for(JSONObject rawOrder: rawOrders){
            orderList.add(loadOrderFromJSON(rawOrder));
        }
        return orderList;
    }

    /**
     * Method for retrieving an order from the database
     * @param fieldName the field to search by
     * @param needle the value of the field to find
     * @return an order entity that matches the requirements
     */
    @Override
    public Order findOneByFieldName(String fieldName, String needle) {
        return loadOrderFromJSON(databaseConnector.readOne(tableName,fieldName,needle));
    }

    /**
     * Method for converting an order entity to a JSON object
     * @param order the order entity
     * @return the corresponding JSON object
     */
    public JSONObject loadJSONFromOrder(Order order){

        return new JSONObject(order.toString());
    }

    /**
     * Method for converting a JSON object to an order entity
     * @param rawOrder the JSON data
     * @return the corresponding order entity
     */
    public Order loadOrderFromJSON(JSONObject rawOrder){
        CartDB cartLoader = new CartDB(databaseConnector);
        try {
            String id = rawOrder.getString("id");
            Cart cart = cartLoader.read(rawOrder.getString("cartId"));
            String shopId = rawOrder.getString("shopId");
            String customerId = rawOrder.getString("customerId");
            Order.Status status = rawOrder.getEnum(Order.Status.class, "status");
            Date timePlaced = new Date();
            Date timeStatusModified = new Date();
            return new Order(id, cart, shopId, customerId, status, timePlaced, timeStatusModified);
        }catch (JSONException e){
            return null;
        }
    }
}
