package Entities.Interfaces;

import java.util.List;

public interface ISelection {

    float getPrice();

    int size();

    Integer get(IAddon addon);

    List<IAddon> getUsedAddons();

}
