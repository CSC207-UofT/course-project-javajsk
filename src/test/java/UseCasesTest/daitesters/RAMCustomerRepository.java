package UseCasesTest.daitesters;
import businessrules.dai.CustomerRepository;
import businessrules.dai.UserRepository;
import entities.Customer;
import entities.User;
import entities.Vendor;

import java.util.ArrayList;
import java.util.List;
public class RAMCustomerRepository implements CustomerRepository {
    List<Customer> storage;
    public RAMCustomerRepository(Customer customer){
        List<Customer> storage = new ArrayList<>();
        storage.add(customer);
        this.storage = storage;
    }

    @Override
    public Customer read(String id) {
        for (Customer customer : storage) {
            if (customer.getId().equals(id)) {
                return customer;
            }
        }
        return null;
    }

    @Override
    public boolean update(String id, Customer item) {
        for (Customer customer : storage) {
            if (customer.getId().equals(id)) {
                storage.add(item);
                storage.remove(customer);
                return true;
            }
        }
        return false;

    }

    @Override
    public String create(Customer item) {
        for (Customer customer : storage) {
            if (item.getId().equals(customer.getId()))
                return "Customer already exists";
        }
        storage.add(item);
        return "Customer created";
    }

    @Override
    public List<Customer> readMultiple(String parameter, String needle) {
        return null;
    }

    @Override
    public Customer findOneByFieldName(String fieldName, String needle) {
        for (Customer customer : storage) {
            if (customer.getUserName().equals(needle)) {
                return customer;
            }
        }
        return null;
    }

    @Override
    public User getUserFromToken(String userToken) {
       //Assume userToken is same as ID
        for (Customer customer:storage){
            if(customer.getId().equals(userToken)){
                return customer;
            }
        }
        return null;
    }

    @Override
    public String authenticateUser(String username, String password) {
        for (Customer customer:storage){
            if (customer.getUserName().equals(username)) {
                if (customer.getHashedPassword().equals(password))
                    return "User authenticated";
                else {
                    return "Password incorrect";
                }
            }
        }
        return "User not found";
    }
}



