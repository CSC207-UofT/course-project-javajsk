package adapters.dam;

import businessrules.dai.CustomerRepository;
import entities.Customer;
import entities.User;

import java.util.List;

public class CustomerDB implements CustomerRepository {
    @Override
    public Customer read(String id) {
        return null;
    }

    @Override
    public boolean update(String id, Customer item) {
        return false;
    }

    @Override
    public boolean delete(String id) {
        return false;
    }

    @Override
    public String create(Customer item) {
        return null;
    }

    @Override
    public List<Customer> readMultiple(String parameter, String needle) {
        return null;
    }

    @Override
    public Customer findOneByFieldName(String fieldName, String needle) {
        return null;
    }

    @Override
    public User getUserFromToken(String userToken) {
        return null;
    }

    @Override
    public String authenticateUser(String username, String password) {
        return null;
    }
}
