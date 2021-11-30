package adapters.gateway.DatabaseDAI;

import entities.Interfaces.Customer;
import entities.Interfaces.User;
import usecases.DataAccessInterfaces.CustomerRepository;

public class CustomerDB implements CustomerRepository {
    @Override
    public Customer getCustomer(String id) {
        return null;
    }

    @Override
    public Boolean deleteCustomer(String id) {
        return null;
    }

    @Override
    public boolean save(Customer customer) {
        return false;
    }

    @Override
    public boolean createCustomer(Customer customer) {
        return false;
    }

    @Override
    public User getUserFromToken(String token) {
        return null;
    }

    @Override
    public String getAuthenticationToken(String username, String password) {
        return null;
    }

    @Override
    public void setPassword(String token, String new_password) {

    }
}
