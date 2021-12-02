package businessrules.outputboundary;

import org.json.JSONObject;

public interface VendorModel {
    JSONObject displayVendor(JSONObject vendor);

    JSONObject displayError(String errorMessage);
}
