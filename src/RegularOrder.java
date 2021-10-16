import Interfaces.Orderable;
import Interfaces.Sellable;

import java.util.ArrayList;

public class RegularOrder implements Orderable {
    private ArrayList<Sellable> items;
    private float Ordertime;
    private int orderStatus;
    private float discount;
    private float eta; 
    private String id;


    public RegularOrder(ArrayList<Sellable> items, float discount){
        this.items = items;
        this.discount = discount;
    }

    @Override
    public String getOrderId(){
        return id;
    }

    @Override
    public ArrayList<Sellable> getOrderItems() {
        return items;
    }

    @Override
    public void addItem(Sellable item, int quantity) {
        for (int i = 0; i < quantity; i++) {
            items.add(item);
        }
    }

    @Override
    public boolean modifyOrder(int index, Sellable item) {
        if(items.size() < index) {
            items.set(index, item);
            return true;
        }
        return false;
    }

    @Override
    public boolean removeItem(int index) {
        if(items.size() < index) {
            items.remove(index);
            return true;
        }
        return false;
    }

    @Override
    public void setOrderStatus(int status) {
        orderStatus = status;
    }

    @Override
    public int getOrderStatus() {
        return orderStatus;
    }

    @Override
    public float getTotalPrice() {
        float sum = 0;
        for(Sellable item: items){
            sum+= item.getPrice();
        }
        return sum * this.getDiscount();
    }

    @Override
    public boolean isCancelled() {
        return orderStatus == Orderable.CANCELLED;
    }

    @Override
    public boolean setDiscount(float percentage) {
        discount = percentage;
        return false;
    }

    @Override
    public float getDiscount() {
        return discount;
    }

    @Override
    public boolean updateETA(float time) {
        eta = time;
        return true;
    }
}
