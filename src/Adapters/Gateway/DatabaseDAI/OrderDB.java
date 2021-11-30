package Adapters.Gateway.DatabaseDAI;

import Entities.Interfaces.IOrder;
import Entities.Interfaces.IOrderbook;
import UseCases.DataAccessInterfaces.OrderRepository;

public class OrderDB implements OrderRepository {
    @Override
    public IOrder getOrder(String id) {
        return null;
    }

    @Override
    public boolean setOrderStatus(String id, String status) {
        return false;
    }

    @Override
    public IOrderbook getOrdersByFoodTruck(String truckId) {
        return null;
    }

    @Override
    public IOrderbook getOrdersByCustomer(String customerID) {
        return null;
    }

    @Override
    public boolean save(IOrder order) {
        return false;
    }

    @Override
    public boolean createOrder(IOrder order) {
        return false;
    }
}
