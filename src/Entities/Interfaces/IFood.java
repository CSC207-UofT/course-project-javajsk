package Entities.Interfaces;

import java.util.HashMap;
import java.util.List;

public interface IFood {
    public String getName();

    public String getDescription();

    public float getPrice();

    public List<ISingleton> getComponents();

    public boolean isValidAddons(List<HashMap<IAddon, Integer>> addons);

    public List<List<IAddon>> getAllowedAddons();
}
