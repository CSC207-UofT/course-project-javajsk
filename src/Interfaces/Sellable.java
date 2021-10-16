package Interfaces;

/** The Sellable Interface
 *
 * This is an abstract interface that allows different types of menu items to all be on a food truck's menu and
 * allows modification of price, item name, description, discounts, and availability through getter and
 * setter methods.
 *
 **/
public interface Sellable {

    String getId();

    float getPrice();

    void setPrice(float price);

    String getName();

    void setName(String newName);

    String getDescription();

    void setDescription(String newDesc);

    boolean isAvailable();

    float getDiscount();

    void setDiscount(float discount);

    void setAvailability(boolean available);

}
