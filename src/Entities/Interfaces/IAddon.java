package Entities.Interfaces;

/**
 * The Addon Interface
 *
 * This is an abstract interface that allows for the name, description, price, and add-on type to be
 * accessed using getter methods.
 */
public interface IAddon {
    /**
     * A method that returns the name of the Addon
     * @return name of addon
     */
    public String getName();

    /**
     * A method that returns the description of the Addon
     * @return description of Addon
     */
    public String getDescription();

    /**
     * A method that returns the price of the Addon
     * @return price of Addon
     */
    public float getPrice();


    /* I am assuming that type will be something like "Pizza topping" or "Ice cream toppings", but this
     can be modified to be something like an integer that represents what type of topping it is.
     e.g. we can allocate 1 to be anything that can be on a pizza or 2 to be anything that can be added
     to a hot dog.

     I think the latter makes sense also because it allows us to be more fuzzy with the types e.g. 1 can represent
     all sorts of savory toppings or something.

     Here its an array because an ingredient may fall under multiple types. (Please change if you see fit.)

     TODO: What is the soloution?
    */

    /**
     * A method that returns an arraylist of the types the addon is, where each type/category is
     * represented by an integer
     * @return arraylist of addon types
     */
    public Integer[] getTypes();

    public void setName(String name);

    public void setPrice(float price);

    public void setDescription(String description);

    public void setTypes(Integer[] types);
}
