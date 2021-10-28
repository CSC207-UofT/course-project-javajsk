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
