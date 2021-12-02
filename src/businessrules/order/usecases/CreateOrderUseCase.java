package businessrules.order.usecases;

import businessrules.dai.CustomerRepository;
import businessrules.dai.OrderRepository;
import businessrules.loaders.CustomerLoader;
import businessrules.loaders.OrderLoader;
import businessrules.order.inputboundaries.CreateOrderInputBoundary;
import businessrules.outputboundary.OrderModel;
import entities.Customer;
import entities.Order;
import org.json.JSONException;
import org.json.JSONObject;

public class CreateOrderUseCase implements CreateOrderInputBoundary {
    OrderModel orderModel;
    OrderRepository orderRepository;
    CustomerRepository customerRepository;
    OrderLoader orderLoader;
    CustomerLoader customerLoader;


    /**
     * Instantiates a use case for creating orders
     * @param oV the order output boundary
     * @param oR the order repository
     * @param cR the customer repository
     */
    public CreateOrderUseCase(OrderModel oV, OrderRepository oR, CustomerRepository cR, OrderLoader oL, CustomerLoader cL) {
        this.orderModel = oV;
        this.orderRepository = oR;
        this.customerRepository = cR;
        this.orderLoader = oL;
        this.customerLoader = cL;

    }

    /**
     * Creates an order
     * @param customerToken the customer making the order
     * @param shopId the shop to order is for
     * @param data the order data
     * @return whether the order was created or not
     */
    @Override
    public JSONObject createOrder(String customerToken, String shopId, JSONObject data) {
        Customer customer = customerLoader.loadCustomerFromToken(customerToken);
        if (customer == null){
            return orderModel.displayError("Invalid customer token");
        }
        Order order;
        try{
            order = orderLoader.loadOrder(data);
        }catch (JSONException e){
            return orderModel.displayError(e.getMessage());
        }
        String id = orderRepository.createOrder(order.jsonify());
        if (id == null){
            return orderModel.displayError("unable to create order in repository");
        }
        order.setId(id);
        return orderModel.displayOrder(order.jsonify());
    }

}
