package Adapters.Gateway.DatabaseDAI;

import Entities.Interfaces.IUser;
import UseCases.DataAccessInterfaces.UserRepository;

public class UserDB implements UserRepository {
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
