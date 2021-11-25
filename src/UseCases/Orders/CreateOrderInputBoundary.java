package UseCases.Orders;

import Entities.Interfaces.ICart;
import Entities.Interfaces.ICustomer;
import Entities.Interfaces.IShop;


public interface CreateOrderInputBoundary {
    boolean createOrder(ICart cart, ICustomer customer, IShop foodTruck);
}
