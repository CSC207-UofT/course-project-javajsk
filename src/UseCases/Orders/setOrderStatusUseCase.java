package UseCases.Orders;

import Entities.Interfaces.IOrder;
import UseCases.DataAccessInterfaces.OrderRepository;
import UseCases.OutputBoundary.OrderModel;

public class setOrderStatusUseCase implements setOrderStatusInputBoundary{
    OrderRepository orderData;
    OrderModel orderModel;

    /**
     * Creates a use case for changing order status
     * @param orders the repository containing all order data
     */
    public setOrderStatusUseCase(OrderRepository orders, OrderModel orderModel){
        this.orderData = orders;
        this.orderModel = orderModel;
    }

    /**
     * a Method for changing the status of a given order
     * @param orderId the id in the repository of a given order
     * @param status the new status of the object
     * @return true if the status has been changed and false if it hasn't
     */
    public boolean setOrderStatus(String orderId, String status) {
        if(this.orderData.setOrderStatus(orderId, status)) {
            this.orderModel.updateOrder(this.orderData.getOrder(orderId));
            return true;
        }
        return false;
    }
}
