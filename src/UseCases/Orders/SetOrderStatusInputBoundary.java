package UseCases.Orders;

public interface SetOrderStatusInputBoundary {
    boolean setOrderStatus(String orderId, String status);
}
