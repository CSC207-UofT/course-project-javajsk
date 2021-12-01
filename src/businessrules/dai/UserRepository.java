package businessrules.dai;

import org.json.JSONObject;

public interface UserRepository {
    JSONObject readUserFromToken(String userToken);

    String getUserToken(String username, String password);
}
