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

public class OrderDB implements Repository<Order> {
    DBGateway databaseConnector;
    final String tableName = "Order";

    public OrderDB(DBGateway databaseConnector) {
        this.databaseConnector = databaseConnector;
    }

    @Override
    public Order read(String id) {
        return loadOrderFromJSON(databaseConnector.read(tableName, id));
    }

    @Override
    public boolean update(String id, Order item) {
        return databaseConnector.update(tableName, id, loadJSONFromOrder(item));

    }


    @Override
    public String create(Order item) {
        return databaseConnector.create(tableName, loadJSONFromOrder(item));
    }

    @Override
    public List<Order> readMultiple(String parameter, String needle) {
        List<Order> orderList = new ArrayList<>();
        List<JSONObject> rawOrders = databaseConnector.readMultiple(tableName, parameter, needle);
        for(JSONObject rawOrder: rawOrders){
            orderList.add(loadOrderFromJSON(rawOrder));
        }
        return orderList;
    }


    @Override
    public Order findOneByFieldName(String fieldName, String needle) {
        return loadOrderFromJSON(databaseConnector.readOne(tableName,fieldName,needle));
    }
    public JSONObject loadJSONFromOrder(Order order){
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("id", order.getId());
        jsonObject.put("cartId", order.getCart().getId());
        jsonObject.put("shopId", order.getShopId());
        jsonObject.put("customerId", order.getCustomerId());
        jsonObject.put("status", order.getStatus());

        // TODO: FIX THIS DATE SHIT.
        jsonObject.put("timePlaced", order.getTimePlaced());
        jsonObject.put("timeStatusModified", order.getTimeStatusModified());
        return jsonObject;
    }


    public Order loadOrderFromJSON(JSONObject rawOrder){
        CartDB cartLoader = new CartDB(databaseConnector);
        try {
            String id = rawOrder.getString("id");
            Cart cart = cartLoader.read(rawOrder.getString("cartId"));
            String shopId = rawOrder.getString("shopId");
            String customerId = rawOrder.getString("customerId");
            Order.Status status = rawOrder.getEnum(Order.Status.class, "status");

            //TODO: FIX DATES.
            Date timePlaced = new Date();
            Date timeStatusModified = new Date();
            return new Order(id, cart, shopId, customerId, status, timePlaced, timeStatusModified);
        }catch (JSONException e){
            return null;
        }
    }
}
