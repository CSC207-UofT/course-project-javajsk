package businessrules.outputboundary;

import org.json.JSONObject;

public interface VendorModel {
    void displayVendor(JSONObject object);

    void updateVendor(JSONObject object);
}
