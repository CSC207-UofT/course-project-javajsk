package Entities.Interfaces;

import Entities.Menu;

public interface IShop {
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
