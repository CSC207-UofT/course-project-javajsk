package businessrules.dai;

import org.json.JSONObject;

public interface CustomerRepository extends UserRepository {
    public boolean createCustomer(String username, String hashedPassword);

    boolean updateCustomer(String id, JSONObject info);
}
