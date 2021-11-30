package UseCases.Orders;

public interface setOrderStatusInputBoundary {
    boolean setOrderStatus(String orderId, String status);
}
