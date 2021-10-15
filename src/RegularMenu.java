import Interfaces.Menu;
import Interfaces.Sellable;

import java.util.ArrayList;
import java.util.HashMap;

public class RegularMenu implements Menu {
    HashMap<String, Sellable> items = new HashMap<>();



    public String generateKey(Sellable item){
        return "";
    }

    @Override
    public String addItem(Sellable item) {
        if(items.containsValue(item)){
            return null;
        }
        String key = generateKey(item);
        items.put(key, item);
        return key;
    }

    @Override
    public Sellable getItem(String id) {
        return items.get(id);
    }

    @Override
    public boolean removeItem(String id) {
        Sellable removedItem = items.remove(id);
        return removedItem != null;
    }

    @Override
    public float getItemPrice(String id) {
        return items.get(id).getPrice();
    }

    @Override
    public boolean setItemPrice(String id, float price) {
        if(items.containsKey(id)){
            items.get(id).setPrice(price);
            return true;
        }
        return false;
    }

    @Override
    public boolean getItemAvailability(String id) {
        if(items.containsKey(id)){
            return items.get(id).isAvailable();
        }
        return false;
    }

    @Override
    public boolean setItemAvailability(String id, boolean available) {
        if(items.containsKey(id)){
            items.get(id).setAvailability(available);
            return true;
        }
        return false;
    }

    @Override
    public boolean setItemDescription(String id, String newDesc) {
        if(items.containsKey(id)){
            items.get(id).setDescription(newDesc);
            return true;
        }
        return false;
    }

    @Override
    public String getItemDescription(String id) {
        if(items.containsKey(id)){
           return items.get(id).getDescription();
        }
        return null;
    }

    @Override
    public String getItemName(String id) {
        if(items.containsKey(id)){
            return items.get(id).getName();
        }
        return null;
    }

    @Override
    public Float getDiscount(String id) {
        if(items.containsKey(id)){
            return items.get(id).getDiscount();
        }
        return null;
    }

    @Override
    public boolean setDiscount(String id, float discount) {
        if(items.containsKey(id)){
            items.get(id).setDiscount(discount);
            return true;
        }
        return false;
    }

    @Override
    public boolean setItemName(String id, String newName) {
        if(items.containsKey(id)){
            items.get(id).setName(newName);
            return true;
        }
        return false;
    }

    @Override
    public ArrayList<Sellable> getAllItems() {
        return (ArrayList<Sellable>) items.values();
    }

    @Override
    public ArrayList<Sellable> getAvailableItems() {
        ArrayList<Sellable> availableItems = new ArrayList<>();
        for(Sellable item: items.values()){
            if(item.isAvailable()){
                availableItems.add(item);
            }
        }
        return availableItems;
    }

    @Override
    public boolean hasItem(String id) {
        return items.containsKey(id);
    }
}
