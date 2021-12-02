package businessrules.outputboundary;

import org.json.JSONObject;

public interface VendorModel {
    void displayVendor(JSONObject vendor);

    void updateVendor(String id, JSONObject vendor);

    void deleteVendor(String id);
}
