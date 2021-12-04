package businessrules.addon.inputboundaries;

import businessrules.outputboundaries.ResponseObject;
import entities.Addon;

public interface CreateAddon {
    ResponseObject createAddon(String vendorToken, Addon addon);
}
