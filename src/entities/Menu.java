package entities;
import java.util.List;

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

    public List<Addon> getAddons() {
        return addons;
    }

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
}
