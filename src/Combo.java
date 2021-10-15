import Interfaces.Sellable;

import java.util.ArrayList;
import java.util.List;

public class Combo implements Sellable {
    float price;
    String name;
    String id;
    ArrayList<Food> MainItems;
    ArrayList<Food> SideItems;
    ArrayList<Food> DrinkItems;
    float Discount;

    public Combo(ArrayList<Food> mainitem, ArrayList<Food> sideitem, ArrayList<Food> drinkitem,
                 String ID, String name, float discount){
        this.MainItems = mainitem;
        this.SideItems = sideitem;
        this.DrinkItems = drinkitem;
        this.name = name;
        this.Discount = discount;
        float mainsprice = totalPrice(mainitem);
        float sidesprice = totalPrice(sideitem);
        float drinksprice = totalPrice(drinkitem);
        this.price = (mainsprice + sidesprice + drinksprice) * this.Discount;
        this.id = ID;
    }
    public float totalPrice(ArrayList<Food> items){
        float accumulator = 0;
        for (Food item : items){
            accumulator = accumulator + item.getPrice();
        }
        return accumulator;
    }

    @Override
    public String getId() {
        return this.id;
    }

    @Override
    public float getPrice() {
        return this.price;
    }

    @Override
    public void setPrice(float price) {
        this.price = price;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public void setName(String newName) {
        this.name = newName;
    }

    @Override
    public boolean isAvailable() {
        return totalAvailability(this.MainItems) &&
                totalAvailability(this.SideItems) &&
                totalAvailability(this.DrinkItems);
    }

    public boolean totalAvailability(ArrayList<Food> items) {
        for (Food item : items){
            if (!item.isAvailable()){
                return false;
            }
        }
        return true;
    }

    public List<Food> getItems() {
        List<Food> items = new ArrayList<>();
        items.addAll(this.MainItems);
        items.addAll(this.SideItems);
        items.addAll(this.DrinkItems);

        return items;
    }

}
