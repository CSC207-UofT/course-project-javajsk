package adapters.dam.entityrepoitories;

import adapters.dam.DBGateway;
import businessrules.dai.Repository;
import entities.Cart;
import entities.Order;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Date;
import java.util.List;

public class OrderDB implements Repository<Order> {
    DBGateway dbGateway;
    @Override
    public Order read(String id) {
        return null;
    }

    @Override
    public boolean update(String id, Order item) {
        return false;
    }

    public OrderDB(DBGateway dbGateway) {
        this.dbGateway = dbGateway;
    }

    @Override
    public String create(Order item) {
        return null;
    }

    @Override
    public List<Order> readMultiple(String parameter, String needle) {
        return null;
    }

    @Override
    public Order findOneByFieldName(String fieldName, String needle) {
        return null;
    }

    public Order loadOrderFromJSON(JSONObject rawOrder){
        CartDB cartLoader = new CartDB(dbGateway);
        try {
            String id = rawOrder.getString("id");
            Cart cart = cartLoader.loadCartFromJSON(rawOrder.getJSONObject("cart"));
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
