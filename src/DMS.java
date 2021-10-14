import Interfaces.Orderable;
import Interfaces.Sellable;
import Interfaces.Shop;
import Interfaces.DataSystem;

import java.util.ArrayList;
import java.util.HashMap;

public class DMS implements DataSystem {
    HashMap<String, Shop> shops = new HashMap<>();


    @Override
    public Sellable searchMenus(String key) {
        return null;
    }

    @Override
    public Shop getShop(String shopId) {
        return shops.get(shopId);
    }


    /** TODO: FINISH THIS FUNC **/
    public String generateKey(Shop shop){
       return "";
    }

    @Override
    public boolean addShop(Shop shop) {
        if(shops.values().contains(shop)){
            return false;
        }


        shops.put(generateKey(shop), shop);
        return true;
    }

    @Override
    public boolean removeShop(String shopId) {
        Shop removedShop =  shops.remove(shopId);
        return removedShop != null;
    }

    @Override
    public String getShopName(String shopId) {
        if(shops.containsKey(shopId)){
            return shops.get(shopId).getShopName();
        }
        return null;
    }

    @Override
    public String getLocation(String shopId) {

        if(shops.containsKey(shopId)){
            return shops.get(shopId).getLocation();
        }
        return null;
    }

    @Override
    public boolean getShopStatus(String shopId) {

        if(shops.containsKey(shopId)){
            return shops.get(shopId).getShopStatus();
        }
        System.out.println("This is fake.");
        return false;
    }

    @Override
    public Sellable getMenuItem(String shopId, String id) {

        if(shops.containsKey(shopId)){
            return shops.get(shopId).getMenuItem(id);
        }
        return null;
    }

    @Override
    public float getMenuItemPrice(String shopId, String id) {
        if(shops.containsKey(shopId)){
            return shops.get(shopId).getMenuItemPrice(id);
        }
        return -404;
    }

    @Override
    public boolean getMenuItemAvailability(String shopId, String id) {

        if(shops.containsKey(shopId)){
            return shops.get(shopId).getMenuItemAvailability(id);
        }
        System.out.println("Does not work.");
        return false;
    }

    @Override
    public String getMenuItemDescription(String shopId, String id) {
        if(shops.containsKey(shopId)){
            return shops.get(shopId).getMenuItemDescription(id);
        }
        return null;
    }

    @Override
    public String getMenuItemName(String shopId, String id) {
        if(shops.containsKey(shopId)){
            return shops.get(shopId).getMenuItemName(id);
        }
        return null;
    }

    @Override
    public Float getMenuDiscount(String shopId, String id) {
        if(shops.containsKey(shopId)){
            return shops.get(shopId).getMenuItemDiscount(id);
        }
        return null;
    }

    @Override
    public ArrayList<Sellable> getAllMenuItems(String shopId) {
        if(shops.containsKey(shopId)){
            return shops.get(shopId).getAllMenuItems();
        }
        return null;
    }

    @Override
    public ArrayList<Sellable> getAvailableMenuItems(String shopId) {
        if(shops.containsKey(shopId)){
            return shops.get(shopId).getAvailableMenuItems();
        }
        return null;
    }

    @Override
    public Orderable getOrder(String shopId, String id) {
        if(shops.containsKey(shopId)){
            return shops.get(shopId).getOrder(id);
        }
        return null;
    }

    @Override
    public void addOrder(String shopId, Orderable item) {
        if(shops.containsKey(shopId)){
            shops.get(shopId).addOrder(item);
        }
        System.out.println("Fake.");
    }


    @Override
    public boolean removeOrder(String shopId, String id) {
        if(shops.containsKey(shopId)){
            return shops.get(shopId).removeOrder(id);
        }
        return false;
    }

    @Override
    public ArrayList<Sellable> getOrderItems(String shopId, String id) {
        if(shops.containsKey(shopId)){
            return shops.get(shopId).getOrderItems(id);
        }
        return null;
    }

    @Override
    public int getOrderStatus(String shopId, String id) {
        if(shops.containsKey(shopId)){
            return shops.get(shopId).getOrderStatus(id);
        }
        return -404;
    }

    @Override
    public float getTotalPriceOfOrder(String shopId, String id) {
        if(shops.containsKey(shopId)){
            return shops.get(shopId).getTotalPriceOfOrder(id);
        }
        return -404;
    }

    @Override
    public boolean isOrderCancelled(String shopId, String id) {
        if(shops.containsKey(shopId)){
            return shops.get(shopId).isOrderCancelled(id);
        }
        System.out.println("s");
        return false;
    }

    @Override
    public void setOrderDiscount(String shopId, String id, float percentage) {
        if(shops.containsKey(shopId)) {
            shops.get(shopId).setOrderDiscount(id, percentage);
        }
    }

    @Override
    public float getOrderDiscount(String shopId, String id) {
        if(shops.containsKey(shopId)){
            return shops.get(shopId).getOrderDiscount(id);
        }
        return -404;
    }

    @Override
    public void updateOrderETA(String shopId, String id, float time) {
        if(shops.containsKey(shopId)){
            shops.get(shopId).updateOrderETA(id, time);
        }
    }
}
