package Entities;

import java.util.List;

public class Vendor {
    String id;
    String password;
    String name;
    List<FoodTruck> foodtrucks;

    public Vendor(String id, String password, String name, List<FoodTruck> foodtrucks){
        this.id = id;
        this.password = password;
        this.name = name;
        this.foodtrucks = foodtrucks;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public List<FoodTruck> getFoodtrucks() {
        return foodtrucks;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setFoodtrucks(List<FoodTruck> foodtrucks) {
        this.foodtrucks = foodtrucks;
    }

    public void updateVendor(Vendor new_vendor){
        this.name = new_vendor.getName();
        this.password = new_vendor.getPassword();
        this.foodtrucks = new_vendor.getFoodtrucks();
    }
}
