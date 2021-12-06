package UseCasesTest.daitesters;

import businessrules.dai.Repository;
import entities.Customer;
import entities.Order;
import entities.User;
import org.mockito.internal.matchers.Or;

import java.util.ArrayList;
import java.util.List;

public class RAMOrderRepository implements Repository<Order> {
    List<Order> storage;
    public RAMOrderRepository(){
        List<Order> storage = new ArrayList<>();
        this.storage = storage;
    }

    @Override
    public Order read(String id) {
        for (Order order : storage) {
            if (order.getId().equals(id)) {
                return order;
            }
        }
        return null;
    }

    @Override
    public boolean update(String id, Order item) {
        for (Order order : storage) {
            if (order.getId().equals(id)) {
                storage.add(item);
                storage.remove(order);
                return true;
            }
        }
        return false;

    }

    @Override
    public String create(Order item) {
        for (Order order : storage) {
            if (item.getId().equals(order.getId()))
                return "Order already exists";
        }
        storage.add(item);
        return "Order created";
    }

    @Override
    public List<Order> readMultiple(String parameter, String needle) {
        return null;
    }

    @Override
    public Order findOneByFieldName(String fieldName, String needle) {
        for (Order order : storage) {
            if (order.getId().equals(needle)) {
                return order;
            }
        }
        return null;
    }

}
