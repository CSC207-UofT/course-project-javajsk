package entities;

/**
 * The type Shop.
 */
public class Shop {
    /**
     * The Menu.
     */
    protected Menu menu;
    /**
     * The Order book.
     */
    protected OrderBook orderBook;
    /**
     * The Location.
     */
    protected String location;
    /**
     * The Name.
     */
    protected String name;

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

    /**
     * The Is open.
     */
    protected boolean isOpen;

}
