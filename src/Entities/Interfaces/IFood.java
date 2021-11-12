package Entities.Interfaces;

import java.util.List;

public interface IFood {
    public String getName();

    public String getDescription();

    public float getPrice();

    public List<ISingleton> getComponents();
}
