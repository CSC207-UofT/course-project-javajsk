package businessrules.vendor.inputboundaries;

import org.json.JSONObject;

public interface UpdateVendorInputBoundary {
    boolean updateVendor(String userToken, JSONObject newData);
}
