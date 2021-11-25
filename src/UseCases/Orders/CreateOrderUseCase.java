package UseCases.Orders;

import Entities.Interfaces.*;
import Entities.Regular.RegularOrder;
import UseCases.DataAccessInterfaces.CustomerRepository;
import UseCases.DataAccessInterfaces.OrderRepository;
import UseCases.DataAccessInterfaces.VendorRepository;
import java.util.HashMap;

public class CreateOrderUseCase implements CreateOrderInputBoundary{
    private OrderRepository orderData;
    private CustomerRepository customerData;
    private VendorRepository vendorData;

    public CreateOrderUseCase(CustomerRepository customers, VendorRepository vendors, OrderRepository orders){
        this.orderData = orders;
        this.customerData = customers;
        this.vendorData = vendors;
    }

    public boolean createOrder(ICart cart, ICustomer customer, IShop foodTruck) {
        boolean validCart = validCart(cart, foodTruck);
        if (validCart){
            String ID = null;
            IOrder order = new RegularOrder(cart, ID);
            order.setStatus("processing");
            if (!this.orderData.save(order)){
                //handle order not being saved
                return false;
            }
            //TODO generate an id
            //TODO add order to customer history and vendor orderbook
            return true;
        }
        return false;
    }

    private boolean validCart(ICart cart, IShop foodTruck ){
        IFood[] cartItems = cart.getFoods();
        HashMap<IFood, ISingleton> menuItems = foodTruck.getMenu().getMenuContents();
        for(IFood item: cartItems){
            //should it keep a list of invalid items so that the customer can be alerted
            if(!menuItems.containsKey(item)){
                return false;
            }
        }
        return true;

    }
}
