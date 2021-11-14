package UseCases;

import Entities.*;
import Entities.Interfaces.IFood;
import Entities.Interfaces.IOrder;
import Entities.Interfaces.IOrderbook;
import Entities.Interfaces.ISingleton;

import java.util.HashMap;


public class OrderInteractor {

    /**
     * creates a regular order object
     *
     *  @param cart the cart the user wants to order
     * @param user the user making the purchase
     * @param foodTruck the shop the user is making the purchase from
     * @return a IOrder object if it's a valid cart or a warning string
     */

    public Object createOrder(RegularCart cart, User user, FoodTruck foodTruck){
        if (validCart(cart, foodTruck)){
            RegularOrder order = new RegularOrder(cart);
            //add this order to the user's order history
            IOrderbook orderBook = foodTruck.getOrderBook();
            orderBook.addOrder(order);
            return order;

        }

        // TODO give warning for invalid cart
        return "invalid cart";
    }

    /**
     * checks if all food in the given cart is from the food truck
     *
     * @param cart the cart the user is trying to order
     * @param foodTruck the food truck the user is trying to order from
     *
     * @return if all the food in cart is from the given food truck
     */
    public boolean validCart(RegularCart cart, FoodTruck foodTruck){
        IFood[] items = cart.getFoods();
        HashMap<IFood, ISingleton> menuItems = foodTruck.getMenu();
        for (IFood item:items){
            if (!menuItems.containsKey(item)){return false;}
        }
        return true;
    }
    /**
     * changes the status of the order
     *
     * @param order  the order whose status is changing
     * @param newStatus indicates the new status of the order
     */
    public void setStatus(IOrder order, boolean newStatus){
        order.setStatus(newStatus);
    }
}
