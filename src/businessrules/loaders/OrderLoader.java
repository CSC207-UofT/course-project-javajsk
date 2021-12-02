package businessrules.loaders;

import businessrules.dai.AddonRepository;
import businessrules.dai.CustomerRepository;
import businessrules.dai.FoodRepository;
import businessrules.dai.OrderRepository;
import businessrules.outputboundary.OrderModel;
import entities.Cart;
import entities.Order;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Date;

public class OrderLoader {
    OrderRepository orderRepository;
    OrderModel orderModel;
    CartLoader cartLoader;

    public OrderLoader(OrderRepository oR, OrderModel oM, CartLoader cL) {
        this.orderRepository = oR;
        this.orderModel = oM;
        this.cartLoader = cL;
    }

    /**
     * Creates new Order objects with repository data
     * @param data order data
     * @return the order object
     * @throws JSONException when the data is not formatted correctly
     */
    public Order loadOrder(JSONObject data) throws JSONException{
        String id = data.getString("id");
        Cart cart = cartLoader.loadCart(data.getJSONObject("cart")) ;
        String status = data.getString("status");
        String shopId = data.getString("shopId");
        String customerId = data.getString("customerId");
        Date timePlaced = (Date) data.get("timePlaced");
        Date timeStatusModified = (Date) data.get("timeStatusModified");

        return new Order(id, cart, shopId, customerId, status, timePlaced, timeStatusModified);
    }

    /**
     * Creates order object from an id
     * @param id of order
     * @return the order object
     */
    public Order loadOrderFromId(String id){
        JSONObject orderData = orderRepository.readOrder(id);
        if (orderData == null){
            orderModel.displayError("Unable to find order with id: " + id);
            return null;
        }
        try {
            return loadOrder(orderData);
        }catch (JSONException e){
            orderModel.displayError(e.getMessage());
        }
        return null;
    }


}
