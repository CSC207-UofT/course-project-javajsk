package adapters.dam.entityrepoitories;

import businessrules.dai.Repository;
import entities.Order;

import java.util.List;

public class OrderDB implements Repository<Order> {
    @Override
    public Order read(String id) {
        return null;
    }

    @Override
    public boolean update(String id, Order item) {
        return false;
    }

    @Override
    public boolean delete(String id) {
        return false;
    }

    @Override
    public String create(Order item) {
        return null;
    }

    @Override
    public List<Order> readMultiple(String parameter, String needle) {
        return null;
    }

    @Override
    public Order findOneByFieldName(String fieldName, String needle) {
        return null;
    }
}
