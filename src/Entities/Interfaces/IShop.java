package Entities.Interfaces;

import Entities.Menu;

public interface IShop {
    public Menu getMenu();

    public IOrderbook getOrderBook();

    public String getName();

    // TODO: Please look into what datatype we will use for locations
    // This may change over time.
    public String getLocation();

    public boolean isFoodAvailable(IFood food);
}
