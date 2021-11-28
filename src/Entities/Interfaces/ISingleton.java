package Entities.Interfaces;

import Entities.Regular.RegularSingleton;

import java.util.List;

public interface ISingleton {
    float getPrice();

    String getName();

    String getDescription();

    ISelection getDefaultSelection();

    // List can be array (Please change as per your decisions.)
    List<IAddon> getAllowedAddonTypes();

    void setPrice(float price);

    void setName(String name);

    void setDescription(String description);

    void setAllowedAddonTypes(List<IAddon> allowedAddonTypes);

    void setSingleton(RegularSingleton singleton);

    String getID();
}
