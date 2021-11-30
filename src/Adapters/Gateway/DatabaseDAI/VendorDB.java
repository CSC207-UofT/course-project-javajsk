package Adapters.Gateway.DatabaseDAI;

import Entities.Interfaces.IUser;
import Entities.Interfaces.IVendor;
import UseCases.DataAccessInterfaces.VendorRepository;

public class VendorDB implements VendorRepository {

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

    @Override
    public IVendor getVendor(String id) {
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
    public IVendor getVendorFromToken(String token) {
        return null;
    }

    @Override
    public Boolean isTokenValid(String token) {
        return null;
    }

    @Override
    public boolean save(IVendor vendor) {
        return false;
    }
}
