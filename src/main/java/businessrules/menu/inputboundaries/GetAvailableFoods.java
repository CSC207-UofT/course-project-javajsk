package businessrules.menu.inputboundaries;

import businessrules.outputboundaries.ResponseObject;

public interface GetAvailableFoods {
    ResponseObject getAvailableFoods(String shopId);
}
