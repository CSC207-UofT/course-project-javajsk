package Entities.Interfaces;

import java.util.List;

public interface IFood {
    public String getName();

    public String getDescription();

    public float getPrice();

    public List<ISingleton> getComponents();

    public void setName(String name);

    public void setDescription(String description);

    public void setPrice(float price);

    public void setComponents(List<ISingleton> components);
}
