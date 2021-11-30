package adapters.gateway.DatabaseDAI;

import entities.Interfaces.User;
import entities.Interfaces.Vendor;
import usecases.DataAccessInterfaces.VendorRepository;

public class VendorDB implements VendorRepository {

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

    @Override
    public Vendor getVendor(String id) {
        return null;
    }

    @Override
    public String getAuthenticationToken(String id) {
        return null;
    }

    @Override
    public String getUserIDFromToken(String token) {
        return null;
    }

    @Override
    public Vendor getVendorFromToken(String token) {
        return null;
    }

    @Override
    public Boolean isTokenValid(String token) {
        return null;
    }

    @Override
    public boolean save(Vendor vendor) {
        return false;
    }
}
