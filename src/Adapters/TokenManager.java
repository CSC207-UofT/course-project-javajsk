package Adapters;

import org.json.JSONObject;

public interface TokenManager {
    JSONObject parseToken(String token);

    String generateToken(String username, String password);
}
