package entities;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * The Shop entity class
 */
public class Shop {

    /**
     * The Id.
     */
    public String id;
    /**
     * The Name.
     */
    protected String name;
    /**
     * The Location.
     */
    protected String location;
    /**
     * The Is open.
     */
    protected boolean isOpen;
    /**
     * The Menu.
     */
    protected Menu menu;
    /**
     * The Order book.
     */
    protected OrderBook orderBook;


    /**
     * Instantiates a new Shop.
     *
     * @param id        the id
     * @param name      the name
     * @param location  the location
     * @param isOpen    the is open
     * @param menu      the menu
     * @param orderBook the order book
     */
    public Shop(String id, String name, String location, boolean isOpen, Menu menu, OrderBook orderBook) {
        this.id = id;
        this.menu = menu;
        this.orderBook = orderBook;
        this.location = location;
        this.name = name;
        this.isOpen = isOpen;
    }

    /**
     * Instantiates a new Shop.
     *
     * @param name     the name
     * @param location the location
     */
    public Shop(String name, String location) {
        this.id = "N/A";
        this.name = name;
        this.location = location;
        this.menu = new Menu();
        this.orderBook = new OrderBook();
        this.isOpen = false;
    }

    /**
     * Gets id.
     *
     * @return the id
     */
    public String getId() {
        return id;
    }

    /**
     * Sets id.
     *
     * @param id the id
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Gets menu.
     *
     * @return the menu
     */
    public Menu getMenu() {
        return this.menu;
    }

    /**
     * Sets menu.
     *
     * @param menu the menu
     */
    public void setMenu(Menu menu) {
        this.menu = menu;
    }

    /**
     * Gets order book.
     *
     * @return the order book
     */
    public OrderBook getOrderBook() {
        return this.orderBook;
    }

    /**
     * Sets order book.
     *
     * @param orderBook the order book
     */
    public void setOrderBook(OrderBook orderBook) {
        this.orderBook = orderBook;
    }

    /**
     * Gets location.
     *
     * @return the location
     */
    public String getLocation() {
        return this.location;
    }

    /**
     * Sets location.
     *
     * @param location the location
     */
    public void setLocation(String location) {
        this.location = location;
    }

    /**
     * Gets name.
     *
     * @return the name
     */
    public String getName() {
        return this.name;
    }

    /**
     * Sets name.
     *
     * @param name the name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Is open boolean.
     *
     * @return the boolean
     */
    public boolean isOpen() {
        return isOpen;
    }

    /**
     * Sets open.
     *
     * @param open the open
     */
    public void setOpen(boolean open) {
        isOpen = open;
    }

    @Override
    public String toString() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("id", this.id);
        jsonObject.put("name", this.name);
        jsonObject.put("location", this.location);
        jsonObject.put("menu", loadJSONfromMenu(this.menu));
        jsonObject.put("isOpen", this.isOpen);
        return jsonObject.toString();
    }

    /**
     * Load jso nfrom menu json object.
     *
     * @param menu the menu
     * @return the json object
     */
    public static JSONObject loadJSONfromMenu(Menu menu) {
        JSONObject jsonObject = new JSONObject();
        JSONArray foods = new JSONArray();
        JSONArray addons = new JSONArray();
        for (Food food : menu.getFoods()) {
            foods.put(food.getId());
        }
        for (Addon addon : menu.getAddons()) {
            addons.put(addon.getId());
        }
        jsonObject.put("foods", foods);
        jsonObject.put("addons", addons);
        return jsonObject;
    }

}
