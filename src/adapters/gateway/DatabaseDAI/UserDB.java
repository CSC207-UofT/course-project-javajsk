package adapters.gateway.DatabaseDAI;

import entities.Interfaces.User;
import usecases.DataAccessInterfaces.UserRepository;

public class UserDB implements UserRepository {
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
