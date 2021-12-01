package entities;
import java.util.List;
import java.util.Objects;

/**
 * The type Menu.
 */
public class Menu {
    /**
     * The Foods.
     */
    protected List<Food> foods;
    /**
     * The Addons.
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

    public boolean updateSingleton(String singletonId, Singleton singleton) {
        for(Food food: this.foods) {
            for(Singleton s: food.getComponents()) {
                if(s.getId().equals(singletonId)) {
                    s.replace(singleton);
                    return true;
                }
            }
        }
        return false;
    }
}
