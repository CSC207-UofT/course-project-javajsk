package UseCasesTest.daitesters;

import businessrules.dai.VendorRepository;
import entities.Customer;
import entities.User;
import entities.Vendor;

import java.util.ArrayList;
import java.util.List;

public class RAMVendorRepository implements VendorRepository {
    List<Vendor> storage;

    public RAMVendorRepository(Vendor vendor) {
        List<Vendor> storage = new ArrayList<>();
        storage.add(vendor);
        this.storage = storage;
    }

    @Override
    public Vendor read(String id) {
        for (Vendor vendor : storage) {
            if (vendor.getId().equals(id)) {
                return vendor;
            }
        }
        return null;
    }

    @Override
    public boolean update(String id, Vendor item) {
        for (Vendor vendor : storage) {
            if (vendor.getId().equals(id)) {
                storage.add(item);
                storage.remove(vendor);
                return true;
            }
        }
        return false;
    }

    @Override
    public String create(Vendor item) {
        for (Vendor vendor : storage) {
            if (item.getId().equals(vendor.getId()))
                return "Item already exists";
        }
        storage.add(item);
        return "Item created";
    }

    @Override
    public List<Vendor> readMultiple(String parameter, String needle) {
        return null;
    }

    @Override
    public Vendor findOneByFieldName(String fieldName, String needle) {
        // Since this is only used with the "username" parameter in both customer and vendor db only, we can
        // hard code the outcome here.
        for (Vendor vendor : storage) {
            if (vendor.getUserName().equals(needle)) {
                return vendor;
            }
        }
        return null;
    }

    @Override
    public User getUserFromToken(String userToken) {

        for (Vendor vendor : storage) {
            if (vendor.getId().equals(userToken)) {
                return vendor;
            }
        }
        return null;

    }

    /**
     * Hard code get user from token to just be finding the username
     */
    @Override
    public String authenticateUser(String username, String password) {
        for (Vendor vendor : storage) {
            if (vendor.getUserName().equals(username)) {
                if (vendor.getHashedPassword().equals(password))
                    return "User authenticated";
                else {
                    return null;
                }
            }
        }
        return null;

    }
}

