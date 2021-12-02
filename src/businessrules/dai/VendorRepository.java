package businessrules.dai;

import org.json.JSONObject;

public interface VendorRepository extends UserRepository {
    boolean createVendor(String username, String hashedPassword);

    boolean updateVendor(String id, JSONObject info);
}
