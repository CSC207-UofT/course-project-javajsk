package Entities.Interfaces;

import Entities.Menu;

import java.util.List;

public interface IShop {

    void setAddonAvailability(IAddon add, Boolean avail);

    boolean isAddonAvailable(IAddon add);

    boolean isAddonListAvailable(List<IAddon> addons);

    boolean isValidAddons(List<ISelection> order);

    Menu getMenu();

    IOrderbook getOrderBook();

    String getName();

    // Locations will be strings for now. Might implement map feature later if we have time.
    String getLocation();

    void setMenu(Menu menu);

    void setOrderBook(IOrderbook orderBook);

    void setName(String name);

    void setLocation(String location);

    String getID();
}
