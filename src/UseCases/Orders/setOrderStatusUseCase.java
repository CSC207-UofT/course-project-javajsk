package UseCases.Orders;

import Entities.Interfaces.IOrder;
import UseCases.DataAccessInterfaces.OrderRepository;

public class setOrderStatusUseCase implements setOrderStatusInputBoundary{
    private OrderRepository orderData;

    public setOrderStatusUseCase(OrderRepository orders){
        this.orderData = orders;
    }

    @Override
    public boolean setOrderStatus(String orderId, String status) {
        return this.orderData.setOrderStatus(orderId, status);
    }
}
