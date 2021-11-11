package Entities;

import Entities.Interfaces.IAddon;
import Entities.Interfaces.ICart;
import Entities.Interfaces.IFood;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class RegularCart implements ICart {
    public class OrderInfo{
        public IAddon[] addons;
        public int[]  addonQuantities;

        public OrderInfo(IAddon[] addons, int[] addonQuantities) {
            this.addons = addons;
            this.addonQuantities = addonQuantities;
        }

        public float getPrice(){
            float sum = 0;
            for(int i =0; i < addons.length;i++){
                IAddon addon = addons[i];
                int qty = addonQuantities[i];
                sum += addon.getPrice() * qty;
            }
            return sum;
        }
}

    private HashMap<IFood, List<OrderInfo>> contents;

    public void RegularCart(IFood[] foods, List<OrderInfo>[] info){
        for (int i = 0; i < foods.length; i++) {
            contents.put(foods[i], info[i]);
        }
    }


    @Override
    public float getTotalPrice() {
        float sum = 0;
        for (IFood food: contents.keySet()) {
            List<OrderInfo> orders = contents.get(food);
            sum += food.getPrice() * orders.size();
            for (OrderInfo item: orders ) {
                sum += item.getPrice();
            }
        }
        return sum;
    }


    public boolean removeItem(IFood item, int index) {
        // removes item at given index.
        if(contents.containsKey(item)){
            int qty = contents.get(item).size();
            if (index <= qty -1) {
                contents.get(item).remove(index);
                return true;
            }else{
                throw new IllegalArgumentException("Index is out of bounds. Please check orderInfo size.");
            }
        }
        return false;
    }

    @Override
    public boolean removeItem(IFood item) {
        //simply removes the last entry of the item.
        if(contents.containsKey(item)){
            int qty = contents.get(item).size();
            contents.get(item).remove(qty-1);
            return true;
        }
        return false;
    }


    @Override
    public boolean setItemQuantity(IFood item, int quantity) {
        if(quantity < 1){
            // THIS SHOULD NEVER HAPPEN.
            throw new IllegalArgumentException("Quantities cannot be less than 1.");
        }

        if(contents.containsKey(item)){
            List<OrderInfo> foodData = contents.get(item);
            if(foodData.size() == quantity){
                return true;
            }
            if(foodData.size() < quantity){
                OrderInfo last_item = foodData.get(foodData.size()-1);
                for(int i = 0; i < quantity - foodData.size(); i++) {
                    foodData.add(last_item);
                }
                assert foodData.size() == quantity;
                return true;
            }else{
                for(int i = 0; i < foodData.size()-quantity; i++) {
                    //keep removing last item
                    foodData.remove(foodData.size()-1);
                }
                assert foodData.size() == quantity;
                return true;
            }
        }
        return false;
    }

    // version with setting quantities (If necessary).
    public boolean addItem(IFood item, int quantity, IAddon[] addons, int[] addonQuantities) {
        OrderInfo info = new OrderInfo(addons, addonQuantities);
        if(contents.containsKey(item)){
            contents.get(item).add(info);
            return this.setItemQuantity(item, quantity);
        }
        else {
            List<OrderInfo> order = new ArrayList<>();
            for (int i = 0; i < quantity; i++) {
                order.add(info);
            }
            contents.put(item, order);
            return true;
        }
    }

    @Override
    public boolean addItem(IFood item, IAddon[] addons, int[] addonQuantities) {
        OrderInfo info = new OrderInfo(addons, addonQuantities);
        if(contents.containsKey(item)){
            contents.get(item).add(info);
            return true;
        }
        else {
            List<OrderInfo> order = new ArrayList<>();
            order.add(info);
            contents.put(item, order);
            return true;
        }
    }

    @Override
    public boolean setAddons(IFood item, int index, IAddon[] addons, int[] addonQuantities) {
        if(contents.containsKey(item)){
            List<OrderInfo> order = contents.get(item);
            if(order.size() <= index){
                throw new IllegalArgumentException("Index greater than size of List.");
            }
            order.get(index).addonQuantities = addonQuantities;
            order.get(index).addons = addons;
        }
        return false;
    }

    public boolean setAddons(IFood item, IAddon[] addons, int[] addonQuantities) {
        // ONLY FOR setting the last item's addons and information.
        if(contents.containsKey(item)){
            List<OrderInfo> order = contents.get(item);
            order.get(order.size()-1).addonQuantities = addonQuantities;
            order.get(order.size()-1).addons = addons;
        }
        return false;
    }

}
