package UseCases.DataAccessInterfaces;

import Entities.Interfaces.IUser;
import Entities.Interfaces.IVendor;

public interface UserRepository {
    IUser getUserFromToken(String token);

    String getAuthenticationToken(String username, String password);

    void setPassword(String token, String new_password);

}
