package Entities.Interfaces;

import Entities.Regular.RegularSingleton;

import java.util.List;

public interface ISingleton {
    public float getPrice();

    public String getName();

    public String getDescription();

    // List can be array (Please change as per your decisions.)
    public List<IAddon> getAllowedAddonTypes();

    public void setPrice(float price);

    public void setName(String name);

    public void setDescription(String description);

    public void setAllowedAddonTypes(List<IAddon> allowedAddonTypes);

    void setSingleton(RegularSingleton singleton);
}
