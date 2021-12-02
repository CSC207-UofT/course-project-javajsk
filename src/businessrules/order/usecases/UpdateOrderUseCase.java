package businessrules.order.usecases;

import businessrules.dai.AddonRepository;
import businessrules.dai.CustomerRepository;
import businessrules.dai.FoodRepository;
import businessrules.dai.OrderRepository;
import businessrules.loaders.OrderLoader;
import businessrules.loaders.SingletonLoader;
import businessrules.order.inputboundaries.UpdateOrderInputBoundary;
import businessrules.outputboundary.OrderModel;
import entities.Order;
import org.json.JSONObject;

public class UpdateOrderUseCase implements UpdateOrderInputBoundary {

    OrderRepository orderRepository;
    OrderModel orderModel;
    OrderLoader orderLoader;

    /**
     * Instantiates a use case for updating orders
     * @param oR the order repository
     * @param oM the output boundary
     */
    public UpdateOrderUseCase(OrderRepository oR, OrderModel oM, OrderLoader oL) {
        this.orderRepository = oR;
        this.orderModel = oM;
        this.orderLoader = oL;
    }

    /**
     * Method for updating order information
     * @param orderId the order id
     * @param object the updated order information
     * @return whether the update went through or not
     */
    @Override
    public JSONObject updateOrder(String orderId, JSONObject object) {
        Order order = orderLoader.loadOrderFromId(orderId);
        if(order == null){
            return orderModel.displayError("Invalid orderId");
        }

        boolean success = orderRepository.updateOrder(orderId, object);
        if(!success){
            return orderModel.displayError("Unable to update order in repository");
        }

        return orderModel.displayOrder(object);
    }
}
