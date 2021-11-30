package adapters.gateway.DatabaseDAI;

import entities.Interfaces.Order;
import entities.Interfaces.OrderBook;
import usecases.DataAccessInterfaces.OrderRepository;

public class OrderDB implements OrderRepository {
    @Override
    public Order getOrder(String id) {
        return null;
    }

    @Override
    public boolean setOrderStatus(String id, String status) {
        return false;
    }

    @Override
    public OrderBook getOrdersByFoodTruck(String truckId) {
        return null;
    }

    @Override
    public OrderBook getOrdersByCustomer(String customerID) {
        return null;
    }

    @Override
    public boolean save(Order order) {
        return false;
    }

    @Override
    public boolean createOrder(Order order) {
        return false;
    }
}
