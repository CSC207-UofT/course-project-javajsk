package businessrules.menu.inputboundaries;

import businessrules.outputboundaries.ResponseObject;

public interface GetAvailableAddons {
    ResponseObject getAvailableAddons(String shopId);
}
