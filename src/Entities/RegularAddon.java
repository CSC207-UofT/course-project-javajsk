package Entities;

import Entities.Interfaces.IAddon;

public class RegularAddon implements IAddon {

    String name;
    String description;
    float price;
    Integer[] types;

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public String getDescription() {
        return this.description;
    }

    @Override
    public float getPrice() {
        return this.price;
    }

    @Override
    public Integer[] getTypes() {
        return this.types;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public void setPrice(float price) {
        this.price = price;
    }

    @Override
    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public void setTypes(Integer[] types) {
        this.types = types;
    }
}
