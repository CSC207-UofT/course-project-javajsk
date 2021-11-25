package Entities.Interfaces;

import java.util.HashMap;

public interface ISelection {

    float getPrice();

    int size();

    HashMap<IAddon, Integer> get(int index);

    String getID();

}
