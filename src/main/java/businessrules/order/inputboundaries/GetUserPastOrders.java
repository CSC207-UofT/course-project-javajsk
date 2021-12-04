package businessrules.order.inputboundaries;

import businessrules.outputboundaries.ResponseObject;

public interface GetUserPastOrders {
    ResponseObject getUserPastOrders(String userToken);
}
