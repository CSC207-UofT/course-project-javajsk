package Entities.Interfaces;

import java.util.List;

public interface ISingleton {
    public float getPrice();

    public String getName();

    public String getDescription();

    // List can be array (Please change as per your decisions.)
    public List<IAddon> getAllowedAddonTypes();
}
