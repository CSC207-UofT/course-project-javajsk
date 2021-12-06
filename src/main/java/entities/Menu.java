package entities;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Menu entity class.
 */
public class Menu{
    /**
     * The list of foods
     */
    protected List<Food> foods;

    /**
     * The list of addons
     */
    protected List<Addon> addons;

    /**
     * Instantiates a new Menu.
     *
     * @param foods  the foods
     * @param addons the addons
     */
    public Menu(List<Food> foods, List<Addon> addons) {
        this.foods = foods;
        this.addons = addons;
    }

    /**
     * Instantiates a new empty menu
     */
    public Menu(){
        this.foods = new ArrayList<>();
        this.addons = new ArrayList<>();
    }

    /**
     * Gets menu addons.
     *
     * @return the addons
     */
    public List<Addon> getAddons() {
        return addons;
    }

    /**
     * Sets menu addons.
     *
     * @param addons the addons
     */
    public void setAddons(List<Addon> addons) {
        this.addons = addons;
    }

    /**
     * Gets menu foods.
     *
     * @return the foods
     */
    public List<Food> getFoods() {
        return foods;
    }

    /**
     * Sets menu foods.
     *
     * @param foods the foods
     */
    public void setFoods(List<Food> foods) {
        this.foods = foods;
    }

    /**
     * Method for adding an addon to the menu
     * @param addon the new addon entity
     */
    public void addAddon(Addon addon) {
        this.addons.add(addon);
    }

    /**
     * Method for updating an addon in the menu
     * @param addonId the id of the addon being modified
     * @param addon the new addon entity
     * @return whether the update was successful
     */
    public boolean updateAddon(String addonId, Addon addon) {
        int index = -1;
        for (int i = 0; i < this.addons.size(); i++) {
            if (this.addons.get(i).getId().equals(addonId)) {
                this.addons.remove(this.addons.get(i));
                index = i;
            }
        }
        if (index == -1) {
            // in this case the addon has not been found in the list.
            return false;
        }
        this.addons.add(index, addon);
        return true;
    }

    /**
     * Method for removing an addon from the menu
     * @param addon the addon to remove
     */
    public void deleteAddon(Addon addon) {
        this.addons.remove(addon);
    }

    /**
     * Method for updating a singleton of a food item in the menu
     * @param singletonId the singleton id
     * @param singleton the new singleton entity
     * @return whether the update was successful
     */
    public boolean updateSingleton(String singletonId, Singleton singleton) {
        for (Food food : this.foods) {
            for (Singleton s : food.getComponents()) {
                if (s.getId().equals(singletonId)) {
                    s.replace(singleton);
                    return true;
                }
            }
        }
        return false;
    }


    /**
     * Method for adding a new food to the menu
     * @param food the new food entity
     */
    public void addFood(Food food){
        this.foods.add(food);
    }

    /**
     * Method for removing a food item from the menu
     * @param food the food entity to renove
     */
    public void deleteFood(Food food){
        this.foods.remove(food);
    }

    /**
     * Method for updating a food item in the menu
     * @param foodId the food id
     * @param food the updated food entity
     * @return whether the update was successful
     */
    public boolean updateFood(String foodId, Food food){
        int index = -1;
        for (int i = 0; i < this.foods.size(); i++) {
            if (this.foods.get(i).getId().equals(foodId)) {
                this.foods.remove(this.foods.get(i));
                index = i;
            }
        }
        if (index == -1) {
            // in this case the addon has not been found in the list.
            return false;
        }
        this.foods.add(index, food);
        return true;
    }

    /**
     * Method for getting a pointer to the exact addon instance
     * @param addon an addon entity
     * @return the addon from the menu's list of addons
     */
    public Addon getAddon(Addon addon){
        for(Addon iterAddon: this.addons){
            if(iterAddon.equals(addon)){
                return iterAddon;
            }
        }
        return null;
    }

    /**
     * Method for getting a pointer to the exact food instance
     * @param food a food entity
     * @return the food entity from menu's list of foods
     */
    public Food getFood(Food food){
        for(Food iterFood: this.foods){
            if(iterFood.equals(food)){
                return iterFood;
            }
        }
        return null;
    }

    /**
     * Method for setting the availability of a singleton of a food item
     * @param singleton the singleton
     * @param availability the availability
     */
    public void setSingletonAvailability(Singleton singleton, boolean availability){
        for(Food food: this.foods){
            for(Singleton singletonIter: food.getComponents()){
                if(singletonIter.equals(singleton)){
                    singletonIter.setAvailability(availability);
                }
            }
        }
    }

    /**
     * Method for getting the available addons
     * @return the list of available addon entities
     */
    public List<Addon> getAvailableAddons(){
        List<Addon> availAddons = new ArrayList<>();
        for(Addon addon: this.addons){
            if(addon.getAvailability()){
                availAddons.add(addon);
            }
        }
        return availAddons;
    }

    /**
     * Method for getting the available foods
     * @return the list of available food entities
     */
    public List<Food> getAvailableFoods(){
        List<Food> availFoods = new ArrayList<>();
        for(Food food: this.foods){
            if(food.isAvailable()){
                availFoods.add(food);
            }
        }
        return availFoods;
    }

    @Override
    public String toString(){
        JSONObject jsonObject = new JSONObject();
        JSONArray foods = new JSONArray();
        JSONArray addons = new JSONArray();
        for(Food food: this.foods){
            foods.put(food.getId());
        }
        for(Addon addon: this.addons){
            addons.put(addon.getId());
        }
        jsonObject.put("foods", foods);
        jsonObject.put("addons", addons);
        return jsonObject.toString();
    }
}
