package UseCases.Customer;

import Entities.Interfaces.IOrderbook;

public interface GetOrderHistoryInputBoundary {

    IOrderbook getOrderHistory(String userToken, String customerID);
}
