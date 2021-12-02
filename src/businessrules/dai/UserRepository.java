package businessrules.dai;

import org.json.JSONObject;

public interface UserRepository {
    boolean updateUser(String id, JSONObject data);

    JSONObject readUserFromToken(String userToken);

    String getUserToken(String username, String password);

    boolean isUsernameUnique(String username);
}
