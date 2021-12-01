package entities;

import org.json.JSONObject;

import java.awt.*;

/**
 * The type Shop.
 */
public class Shop implements JSONable{

    public String id;
    protected String name;
    protected String location;
    protected boolean isOpen;
    protected Menu menu;
    protected OrderBook orderBook;


    /**
     * Instantiates a new Shop.
     *
     * @param menu      the menu
     * @param orderBook the order book
     * @param location  the location
     * @param name      the name
     * @param isOpen    the is open
     */
    public Shop(String id, String name, String location, boolean isOpen, Menu menu, OrderBook orderBook) {
        this.id = id;
        this.menu = menu;
        this.orderBook = orderBook;
        this.location = location;
        this.name = name;
        this.isOpen = isOpen;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    /**
     * Gets menu.
     *
     * @return the menu
     */
    public Menu getMenu() {
        return menu;
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
        return orderBook;
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
        return location;
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
        return name;
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
    public JSONObject jsonify() {
        JSONObject shopJson = new JSONObject();
        shopJson.put("id", this.id);
        shopJson.put("menu", this.menu.jsonify());
        shopJson.put("location", location);
        shopJson.put("name", this.name);
        shopJson.put("isOpen", this.isOpen);
        return shopJson;
    }
}
