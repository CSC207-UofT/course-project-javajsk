package businessrules.dai;

import EntitiesUnitTest.User;

public interface UserRepository {
    User getUserFromToken(String userToken);

    String authenticateUser(String username, String password);
}
