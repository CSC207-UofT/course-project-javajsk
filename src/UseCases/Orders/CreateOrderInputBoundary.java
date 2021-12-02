package UseCases.Orders;

import entities.Interfaces.ICart;
import entities.Interfaces.ICustomer;
import entities.Interfaces.IShop;


public interface CreateOrderInputBoundary {
    boolean createOrder(ICart cart, ICustomer customer, String shopId);
}
