package UseCases.Menu;

import Entities.Menu;

public interface addMenuItemInputBoundary {
    boolean addMenuItem(Menu menu, String foodId, float price, boolean availability);
}
