import Interfaces.Menu;
import Interfaces.Sellable;

import java.util.ArrayList;
import java.util.HashMap;

public class RegularMenu implements Menu {
    /**
     * A RegularMenu class
     *
     * This class is meant to represent a food truck's menu (a collection of everything that a
     * food truck sells)
     *
     * items is a hashmap of all the things on this menu
     */
    HashMap<String, Sellable> items;

    /**
     * Constructor method - creates an empty hashmap items.
     */
    public RegularMenu(){
        this.items = new HashMap<>();
    }

    /** A method that returns the sellable item id to be the key for the sellable item
     */
    public String generateKey(Sellable item){
        return item.getId();
    }

    /**
     * A method that adds an item to items
     *
     * @param item the item to be added to items
     * @return the key that corresponds to the item that was added to the hashmap (or null if
     * the item was already in items
     */
    @Override
    public String addItem(Sellable item) {
        if(items.containsValue(item)){
            return null;
        }
        String key = generateKey(item);
        items.put(key, item);
        return key;
    }

    /**
     * A function that returns a sellable item based on the given key.
     *
     * @param id the key that corresponds to a sellable item in items
     * @return the sellable item in items that corresponds with the key id
     */
    @Override
    public Sellable getItem(String id) {
        return items.get(id);
    }

    /**
     * A function that removes an item from items
     *
     * @param id the key that corresponds to the item in items that is to be removed
     * @return whether that item was successfully removed
     */
    @Override
    public boolean removeItem(String id) {
        Sellable removedItem = items.remove(id);
        return removedItem != null;
    }

    /**
     * A function that returns the price of a given item in items
     *
     * @param id key of the item in items
     * @return price of item as a float
     */
    @Override
    public float getItemPrice(String id) {
        return items.get(id).getPrice();
    }

    /**
     * A function that sets the price of a given item in items to the given price
     *
     * @param id key of the item in items
     * @param price price of the item
     * @return whether the price of the item was successfully set
     */
    @Override
    public boolean setItemPrice(String id, float price) {
        if(items.containsKey(id)){
            items.get(id).setPrice(price);
            return true;
        }
        return false;
    }

    /**
     * A function that returns whether an item in items is available
     * @param id key of the item in items
     * @return whether the item is available (or false if id is not a key in items)
     */
    @Override
    public boolean getItemAvailability(String id) {
        if(items.containsKey(id)){
            return items.get(id).isAvailable();
        }
        return false;
    }

    /**
     * A function that sets the availability of an item in items
     * @param id key of the item in items
     * @param available whether the item is available
     * @return whether the availability was set successfully (or false if the item is not in items)
     */
    @Override
    public boolean setItemAvailability(String id, boolean available) {
        if(items.containsKey(id)){
            items.get(id).setAvailability(available);
            return true;
        }
        return false;
    }

    /**
     * A function that sets the description of an item in items
     * @param id key of item in items
     * @param newDesc description of the item
     * @return whether the description was changed successfully (or false if the item is not in items)
     */
    @Override
    public boolean setItemDescription(String id, String newDesc) {
        if(items.containsKey(id)){
            items.get(id).setDescription(newDesc);
            return true;
        }
        return false;
    }

    /**
     * A function that returns the description of an item in items
     *
     * @param id key of the item in items
     * @return description of item as a string (or null if item is not in items)
     */
    @Override
    public String getItemDescription(String id) {
        if(items.containsKey(id)){
           return items.get(id).getDescription();
        }
        return null;
    }

    /**
     * A function that returns the name of an item in items
     * @param id key of item in items
     * @return name of item (or null if item is not in items)
     */
    @Override
    public String getItemName(String id) {
        if(items.containsKey(id)){
            return items.get(id).getName();
        }
        return null;
    }

    /**
     * A function that sets the name of an item in items
     * @param id key of item in items
     * @param newName name of item
     * @return whether the name was updated successfully (or false if item is not in items)
     */
    @Override
    public boolean setItemName(String id, String newName) {
        if(items.containsKey(id)){
            items.get(id).setName(newName);
            return true;
        }
        return false;
    }

    /**
     * A function that returns the discount of an item in items
     * @param id key of item in items
     * @return discount of item as a float or null if item is not in items
     */
    @Override
    public Float getDiscount(String id) {
        if(items.containsKey(id)){
            return items.get(id).getDiscount();
        }
        return null;
    }

    /**
     * A function that changes the discount of an item in items to a given discount
     * @param id key of item in items
     * @param discount discount of item
     * @return whether the discount was successfuly added to an item (or false if the item is not in items)
     */
    @Override
    public boolean setDiscount(String id, float discount) {
        if(items.containsKey(id)){
            items.get(id).setDiscount(discount);
            return true;
        }
        return false;
    }

    /**
     * A function that returns a list of all the items in items
     * @return arraylist of items in items
     */
    @Override
    public ArrayList<Sellable> getAllItems() {
        return (ArrayList<Sellable>) items.values();
    }


    /**
     * A function that returns all the available items in items
     *
     * @return list of available items in items
     */
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

    /**
     * A function that checks whether a key is in the hashmap items
     * @param id key of item
     * @return return whether key for an item is in items
     */
    @Override
    public boolean hasItem(String id) {
        return items.containsKey(id);
    }
}
