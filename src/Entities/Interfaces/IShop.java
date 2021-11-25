package Entities.Interfaces;

import Entities.Menu;

import java.util.List;

public interface IShop {

    void setAddonAvailability(IAddon add, Boolean avail);

    boolean isAddonAvailable(IAddon add);

    boolean isAddonListAvailable(List<IAddon> addons);

    boolean isValidAddons(List<ISelection> order);

    public Menu getMenu();

    public IOrderbook getOrderBook();

    public String getName();

    // Locations will be strings for now. Might implement map feature later if we have time.
    public String getLocation();

    public void setMenu(Menu menu);

    public void setOrderBook(IOrderbook orderBook);

    public void setName(String name);

    public void setLocation(String location);

    String getID();
}
