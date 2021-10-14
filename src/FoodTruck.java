import Interfaces.*;

import java.util.ArrayList;

public class FoodTruck implements Shop {
    String shopName;
    String location;
    boolean isOpen;
    Menu menu;
    OrderBook orderBook;


    @Override
    public String getShopName() {
        return shopName;

    }

    @Override
    public void changeShopName(String newName) {
        this.shopName = newName;
    }

    @Override
    public String getLocation() {
        return location;
    }

    @Override
    public void changeLocation(String location) {
        this.location = location;
    }

    @Override
    public boolean getShopStatus() {
        return isOpen;
    }

    @Override
    public void setShopStatus(boolean status) {
        isOpen = status;
    }

    @Override
    public String addMenuItem(Sellable item) {
        return menu.addItem(item);
    }

    @Override
    public Sellable getMenuItem(String id) {
        return menu.getItem(id);
    }

    @Override
    public boolean removeMenuItem(String id) {
        return menu.removeItem(id);
    }

    @Override
    public float getMenuItemPrice(String id) {
        return menu.getItemPrice(id);
    }

    @Override
    public boolean setMenuItemPrice(String id, float price) {
        return menu.setItemPrice(id, price);
    }

    @Override
    public boolean getMenuItemAvailability(String id) {
        return  menu.getItemAvailability(id);
    }

    @Override
    public boolean setMenuItemAvailability(String id, boolean available) {
        return menu.setItemAvailability(id, available);
    }

    @Override
    public boolean setMenuItemDescription(String id, String newDesc) {
        return menu.setItemDescription(id, newDesc);
    }

    @Override
    public String getMenuItemDescription(String id) {
        return menu.getItemDescription(id);
    }

    @Override
    public String getMenuItemName(String id) {
        return menu.getItemName(id);
    }

    @Override
    public Float getMenuItemDiscount(String id) {
        return menu.getDiscount(id);
    }

    @Override
    public boolean setMenuItemDiscount(String id, float discount) {
        return menu.setDiscount(id, discount);
    }

    @Override
    public boolean setMenuItemName(String id, String newName) {
        return menu.setItemName(id, newName);
    }

    @Override
    public ArrayList<Sellable> getAllMenuItems() {
        return menu.getAllItems();
    }

    @Override
    public ArrayList<Sellable> getAvailableMenuItems() {
        return menu.getAvailableItems();
    }

    @Override
    public Orderable getNextOrder() {
        return orderBook.getNextOrder();
    }

    @Override
    public Orderable getOrder(String id) {
        return orderBook.getOrder(id);
    }

    @Override
    public void addOrder(Orderable item) {
        orderBook.addOrder(item);
    }


    @Override
    public boolean removeOrder(String id) {
        return orderBook.removeOrder(id);
    }

    @Override
    public boolean completeOrder(String id) {
        return orderBook.completeOrder(id);
    }

    @Override
    public void setOrderStatus(String id, int status) {
        orderBook.setOrderStatus(id, status);
    }

    @Override
    public ArrayList<Sellable> getOrderItems(String id) {
        return orderBook.getOrderItems(id);
    }

    @Override
    public void addItemToOrder(String id, Sellable item, int quantity) {
        orderBook.addItemToOrder(id, item, quantity);
    }

    @Override
    public void modifyOrder(String id, int index, Sellable item) {
        orderBook.modifyOrder(id, index, item);
    }

    @Override
    public boolean removeItemFromOrder(String id, int index) {
        return orderBook.removeItemFromOrder(id, index);
    }

    @Override
    public int getOrderStatus(String id) {
        return orderBook.getOrderStatus(id);
    }

    @Override
    public float getTotalPriceOfOrder(String id) {
        return orderBook.getTotalPriceOfOrder(id);
    }

    @Override
    public boolean isOrderCancelled(String id) {
        return orderBook.isOrderCancelled(id);
    }

    @Override
    public void setOrderDiscount(String id, float percentage) {
        orderBook.setOrderDiscount(id, percentage);
    }

    @Override
    public float getOrderDiscount(String id) {
        return orderBook.getOrderDiscount(id);
    }

    @Override
    public void updateOrderETA(String id, float time) {
        orderBook.updateOrderETA(id, time);
    }
}
