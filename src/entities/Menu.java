package entities;
import org.json.JSONObject;

import java.util.List;

/**
 * Menu entity class.
 */
public class Menu implements JSONable{

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

    public void addAddon(Addon addon){
        this.addons.add(addon);
    }

    public boolean updateAddon(String addonId, Addon addon){
        int index = -1;
        for(int i =0; i < this.addons.size(); i++){
            if(this.addons.get(i).getId().equals(addonId)){
                this.addons.remove(this.addons.get(i));
                index = i;
            }
        }
        if(index == -1){
            // in this case the addon has not been found in the list.
            return false;
        }
        this.addons.add(index, addon);
        return true;
    }

    public boolean deleteAddon(Addon addon){
        return this.addons.remove(addon);
    }

    @Override
    public JSONObject jsonify() {
        JSONObject menuData = new JSONObject();
        // TODO: SEE how this works out into the JSON.
        menuData.put("id", this.foods);
        menuData.put("name", this.addons);

        return menuData;
    public void addFood(Food food){
        this.foods.add(food);
    }

    public boolean deleteFood(Food food){
        return this.foods.remove(food);
    }

    public boolean updateFood(String foodId, Food food){
        int index = -1;
        for(int i =0; i < this.foods.size(); i++){
            if(this.foods.get(i).getId().equals(foodId)){
                this.foods.remove(this.foods.get(i));
                index = i;
            }
        }
        if(index == -1){
            // in this case the addon has not been found in the list.
            return false;
        }
        this.foods.add(index, food);
        return true;
    }
}
