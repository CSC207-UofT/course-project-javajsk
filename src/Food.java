import Interfaces.Sellable;

public class Food implements Sellable {
    boolean isAvailable;
    String id;
    float price;
    String name;
    String description;
    float discount;

    public Food(String id, String name, float price, String description, boolean available, float discount){
        this.id = id;
        this.name = name;
        this.price = price;
        this.description = description;
        this.isAvailable = available;
        this.discount = discount;

    }
    @Override
    public String getId() {
        return this.id;
    }

    @Override
    public float getPrice() {
        return this.price * this.discount;
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
    public String getDescription() {
        return this.description;
    }

    @Override
    public void setDescription(String newDesc) {
        this.description = newDesc;
    }

    @Override
    public boolean isAvailable() {
        return isAvailable;
    }

    @Override
    public float getDiscount() {
        return this.discount;
    }

    @Override
    public void setDiscount(float discount) {
        this.discount = discount;
    }

    @Override
    public void setAvailability(boolean available) {
        this.isAvailable = available;
    }
}
