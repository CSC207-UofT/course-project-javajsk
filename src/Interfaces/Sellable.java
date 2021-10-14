package Interfaces;

public interface Sellable {

    String getId();

    float getPrice();

    void setPrice(double price);

    String getName();

    void setName(String newName);

    String getDescription();

    void setDescription(String newDesc);

    boolean isAvailable();

    float getDiscount();

    void setDiscount(float discount);

    void setAvailability(boolean available);

}
