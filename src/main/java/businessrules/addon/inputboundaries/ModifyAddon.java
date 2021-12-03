package businessrules.addon.inputboundaries;

import businessrules.outputboundaries.ResponseObject;
import entities.Addon;

public interface ModifyAddon {
    ResponseObject modifyAddon(String vendorToken, String id, Addon addon);
}
