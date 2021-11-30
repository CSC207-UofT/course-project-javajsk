package UseCases.Orders;

import Entities.Interfaces.*;
import Entities.Regular.RegularOrder;
import UseCases.DataAccessInterfaces.CustomerRepository;
import UseCases.DataAccessInterfaces.OrderRepository;
import UseCases.DataAccessInterfaces.VendorRepository;
import UseCases.OutputBoundary.OrderModel;

public class CreateOrderUseCase implements CreateOrderInputBoundary{
    OrderRepository orderData;
    CustomerRepository customerData;
    VendorRepository vendorData;
    OrderModel orderModel;

    /**
     * Creates a use case for making order objects
     *
     * @param customers the repository containing all customer data
     * @param vendors the repository containing all vendor data
     * @param orders the repository containing all order data
     * @param orderModel the output boundary
     */
    public CreateOrderUseCase(CustomerRepository customers, VendorRepository vendors, OrderRepository orders, OrderModel orderModel){
        this.orderData = orders;
        this.customerData = customers;
        this.vendorData = vendors;
        this.orderModel = orderModel;
    }

    /**
     * A method for creating new order objects
     * @param cart the cart with the items that want to be purchased
     * @param customer the customer buying the items
     * @param foodTruck the food truck selling the items
     * @return true if the order has been created and false if it hasn't
     */
    public boolean createOrder(ICart cart, ICustomer customer, IShop foodTruck) {
        boolean validCart = validCart(cart, foodTruck);
        if (validCart){
            IOrder order = new RegularOrder(cart, null);
            order.setStatus("processing");
            return this.orderData.save(order);
        }
        return false;
    }

    /**
     * Check if all the items in a cart belong to the same food truck
     * @param cart the cart we are checking
     * @param shop the food truck the cart is from
     * @return true if the cart is all from the same food truck and false if it isn't
     */
    private boolean validCart(ICart cart, IShop shop ){
        String id = shop.getId();
        IFood[] cartItems = cart.getFoods();
        for(IFood item: cartItems){
            //TODO: should it keep a list of invalid items so that the customer can be alerted
            if(!item.getShopId().equals(id)){
                return false;
            }
        }
        return true;

    }
}
