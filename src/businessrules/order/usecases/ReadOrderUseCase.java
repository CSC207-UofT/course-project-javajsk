package businessrules.order.usecases;

import businessrules.dai.OrderRepository;
import businessrules.loaders.OrderLoader;
import businessrules.order.inputboundaries.ReadOrderInputBoundary;
import businessrules.outputboundary.OrderModel;
import entities.Order;
import org.json.JSONException;
import org.json.JSONObject;

public class ReadOrderUseCase implements ReadOrderInputBoundary {
    OrderRepository orderRepository;
    OrderModel orderModel;
    OrderLoader orderLoader;

    /**
     * Instantiates a use case for getting orders
     * @param oR the order repository
     * @param oM  the output boundary
     */
    public ReadOrderUseCase(OrderRepository oR, OrderModel oM, OrderLoader oL) {
        this.orderRepository = oR;
        this.orderModel = oM;
        this.orderLoader = oL;
    }


    /**Method for getting the JSON object of an order
     * @param id the order id
     * @return the JSON object
     */
    @Override
    public JSONObject readOrder(String id) {
        JSONObject orderData = orderRepository.readOrder(id);
        Order order;

        try {
            order = orderLoader.loadOrder(orderData);
        }catch (JSONException e){
            orderModel.displayError(e.getMessage());
            return null;
        }

        return orderModel.displayOrder(order.jsonify());

    }
}
