package adapters.dam;

import businessrules.dai.VendorRepository;
import entities.User;
import entities.Vendor;

import java.util.List;

public class VendorDB implements VendorRepository {
    @Override
    public Vendor read(String id) {
        return null;
    }

    @Override
    public boolean update(String id, Vendor item) {
        return false;
    }

    @Override
    public String create(Vendor item) {
        return null;
    }

    @Override
    public List<Vendor> readMultiple(String parameter, String needle) {
        return null;
    }

    @Override
    public Vendor findOneByFieldName(String fieldName, String needle) {
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
