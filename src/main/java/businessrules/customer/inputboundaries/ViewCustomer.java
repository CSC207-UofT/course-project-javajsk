package businessrules.customer.inputboundaries;

import businessrules.outputboundaries.ResponseObject;

public interface ViewCustomer {

    ResponseObject viewCustomer(String customerId);
}
