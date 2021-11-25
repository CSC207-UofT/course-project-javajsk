package UseCases.Customer;

import Entities.Interfaces.ICustomer;
import Entities.Interfaces.IOrderbook;
import UseCases.DataAccessInterfaces.CustomerRepository;
import UseCases.DataAccessInterfaces.OrderRepository;
import UseCases.OutputBoundary.ErrorPopup;

public class GetOrderHistoryUseCase implements GetOrderHistoryInputBoundary{
    CustomerRepository customerRepository;
    OrderRepository orderRepository;
    ErrorPopup errorDisplayer;
    @Override
    public IOrderbook getOrderHistory(String userToken, String customerID) {
        ICustomer customer = (ICustomer) customerRepository.getUserFromToken(userToken);
        if(customer != null){

            return orderRepository.getOrdersByCustomer(customerID);
        }

        errorDisplayer.displayError("Invalid customerID");
        return null;

    }
}
