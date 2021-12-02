package businessrules.vendor.inputboundaries;

import org.json.JSONObject;

public interface SignUpVendorInputBoundary {
    boolean signUp(JSONObject data);
}
