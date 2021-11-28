package UseCases.Menu;


public interface editMenuItemInputBoundary {
    boolean editMenuItem(String shopId, String foodId, float price, boolean availability);
}
