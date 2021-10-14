import Interfaces.OrderBook;
import Interfaces.Orderable;
import Interfaces.Sellable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

public class FiFoBook implements OrderBook {
    HashMap<String, Orderable> orders = new HashMap<>();
    Queue<String> orderQueue = new LinkedList<String>();
    int items = 0;


    public String generateKey(Orderable item){
        return "";
    }

    @Override
    public Orderable getNextOrder() {
        if(items == 0){
            return null;
        }
        String nextId = orderQueue.remove();
        return orders.get(nextId);
    }

    @Override
    public Orderable getOrder(String id) {
        return orders.get(id);
    }

    @Override
    public void addOrder(Orderable item) {
        items++;
        String key = generateKey(item);
        orders.put(key, item);
        orderQueue.add(key);

    }

    @Override
    public boolean removeOrder(String id) {
        if(orders.containsKey(id)){
            orders.remove(id);
            return true;
        }
        return false;
    }

    @Override
    public boolean completeOrder(String id) {
        if(orders.containsKey(id)){
            orders.get(id).setOrderStatus(Orderable.COMPLETED);
            return true;
        }
        return false;
    }

    @Override
    public boolean setOrderStatus(String id, int status) {
        if(orders.containsKey(id)){
            orders.get(id).setOrderStatus(status);
            return true;
        }
        return false;
    }

    @Override
    public ArrayList<Sellable> getOrderItems(String id) {
        if(orders.containsKey(id)) {
            return orders.get(id).getOrderItems();
        }
        return null;
    }

    @Override
    public boolean addItemToOrder(String id, Sellable item, int quantity) {
        if(orders.containsKey(id)) {
            orders.get(id).addItem(item, quantity);
            return true;
        }
        return false;
    }

    @Override
    public boolean modifyOrder(String id, int index, Sellable item) {
        if(orders.containsKey(id)) {
            return orders.get(id).modifyOrder(index, item);
        }
        return false;
    }

    @Override
    public boolean removeItemFromOrder(String id, int index) {
        if(orders.containsKey(id)) {
            return orders.get(id).removeItem(index);
        }
        return false;
    }


    @Override
    public int getOrderStatus(String id) {
        if(orders.containsKey(id)) {
            return orders.get(id).getOrderStatus();
        }
        return -404;
    }

    @Override
    public float getTotalPriceOfOrder(String id) {
        if(orders.containsKey(id)) {
            return orders.get(id).getTotalPrice();
        }
        return -404;
    }

    @Override
    public boolean isOrderCancelled(String id) {
        if(orders.containsKey(id)) {
            return orders.get(id).isCancelled();
        }
        System.out.println("NULL POINTER");
        return false;
    }

    @Override
    public boolean setOrderDiscount(String id, float percentage) {
        if(orders.containsKey(id)) {
            return orders.get(id).setDiscount(percentage);

        }
        return false;
    }

    @Override
    public float getOrderDiscount(String id) {
        if(orders.containsKey(id)) {
            return orders.get(id).getDiscount();

        }
        return -404;
    }

    @Override
    public boolean updateOrderETA(String id, float time) {
        if(orders.containsKey(id)) {
            return orders.get(id).updateETA(time);

        }
        return false;
    }
}
