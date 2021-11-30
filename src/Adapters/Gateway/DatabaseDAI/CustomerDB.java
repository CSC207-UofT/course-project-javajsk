package Adapters.Gateway.DatabaseDAI;

import Entities.Interfaces.ICustomer;
import Entities.Interfaces.IUser;
import UseCases.DataAccessInterfaces.CustomerRepository;

public class CustomerDB implements CustomerRepository {
    @Override
    public ICustomer getCustomer(String id) {
        return null;
    }

    @Override
    public Boolean deleteCustomer(String id) {
        return null;
    }

    @Override
    public boolean save(ICustomer customer) {
        return false;
    }

    @Override
    public boolean createCustomer(ICustomer customer) {
        return false;
    }

    @Override
    public IUser getUserFromToken(String token) {
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
