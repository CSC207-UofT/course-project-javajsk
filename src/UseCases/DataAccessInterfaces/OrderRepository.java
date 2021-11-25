package UseCases.DataAccessInterfaces;

import Entities.Interfaces.IOrder;

import java.util.ArrayList;

/**
 * Order
 * This interface outlines the methods that will be used in accessing data from the higher level data system.
 */
public interface OrderRepository {

    /**
     * Method that gets the order object from higher level data system associated with given Id
     * @param id Id of desired order
     * @return order
     */
    IOrder getOrder(String id);

    /**
     * Method sets status of order with given id in the repository
     * Will return false if order with given id doesn't exist
     * @param id id of order
     * @param status status of order
     * @return whether order status was set
     */
    boolean setOrderStatus(String id, String status);

    /**
     * Method returns all the orders with the given foodtruck id
     * from the higher level data system
     *
     * @param truckId if of foodtruck
     * @return arraylist of orders
     */
    ArrayList<IOrder> getOrdersByFoodTruck(String truckId);

    boolean save(IOrder order);
}
