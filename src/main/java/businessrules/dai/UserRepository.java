package businessrules.dai;

import entities.User;

public interface UserRepository {
    User getUserFromToken(String userToken);

    String authenticateUser(String username, String password);
}
