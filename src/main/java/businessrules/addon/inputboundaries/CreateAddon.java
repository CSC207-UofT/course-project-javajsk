package businessrules.addon.inputboundaries;

import businessrules.outputboundaries.ResponseObject;
import EntitiesUnitTest.Addon;

public interface CreateAddon {
    ResponseObject createAddon(String vendorToken, Addon addon);
}
