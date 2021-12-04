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
     * The Foods.
     */
    protected List<Food> foods;

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

    public Menu(){
        this.foods = new ArrayList<>();
        this.addons = new ArrayList<>();
    }

    /**
     * Gets addons.
     *
     * @return the addons
     */
    public List<Addon> getAddons() {
        return addons;
    }

    /**
     * Sets addons.
     *
     * @param addons the addons
     */
    public void setAddons(List<Addon> addons) {
        this.addons = addons;
    }

    /**
     * Gets foods.
     *
     * @return the foods
     */
    public List<Food> getFoods() {
        return foods;
    }

    /**
     * Sets foods.
     *
     * @param foods the foods
     */
    public void setFoods(List<Food> foods) {
        this.foods = foods;
    }

    public void addAddon(Addon addon) {
        this.addons.add(addon);
    }

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

    public boolean deleteAddon(Addon addon) {
        return this.addons.remove(addon);
    }

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


    public void addFood(Food food){
        this.foods.add(food);
    }

    public boolean deleteFood(Food food){
        return this.foods.remove(food);
    }

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

    // While this seems unnecessary, it returns a pointer to the exact food instance
    public Addon getAddon(Addon addon){
        for(Addon iterAddon: this.addons){
            if(iterAddon.equals(addon)){
                return iterAddon;
            }
        }
        return null;
    }

    // While this seems unnecessary, it returns a pointer to the exact addon instance
    public Food getAddon(Food food){
        for(Food iterFood: this.foods){
            if(iterFood.equals(food)){
                return iterFood;
            }
        }
        return null;
    }

    public void setSingletonAvailability(Singleton singleton, boolean availability){
        for(Food food: this.foods){
            for(Singleton singletonIter: food.getComponents()){
                if(singletonIter.equals(singleton)){
                    singletonIter.setAvailability(availability);
                }
            }
        }
    }

    public List<Addon> getAvailableAddons(){
        List<Addon> availAddons = new ArrayList<>();
        for(Addon addon: this.addons){
            if(addon.isAvailable()){
                availAddons.add(addon);
            }
        }
        return availAddons;
    }

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
